package com.shaman.transactionpaymentschedule.service;

import com.shaman.transactionpaymentschedule.entity.DomainBaseEntity;
import com.shaman.transactionpaymentschedule.exception.ApplicationException;
import com.shaman.transactionpaymentschedule.message.Messages;
import com.shaman.transactionpaymentschedule.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class BaseService<T extends DomainBaseEntity<T>, R extends BaseRepository<T>> {
    @Autowired
    protected R repository;

    protected abstract String getEntityName();
    protected abstract Optional<T> findEntityByProperty(T entity);

    public List<T> findAll() {
        return repository.findAllByRemovedAtIsNull();
    }

    public T findByUid(UUID uuid) {
        return repository.findByUuidAndRemovedAtIsNull(uuid).orElseThrow(() -> new ApplicationException(
                String.format(Messages.ENTITY_NOT_FOUND, getEntityName())
        ));
    }

    public Optional<T> findById(Long id) {
        return repository.findByIdAndRemovedAtIsNull(id);
    }

    @Transactional
    public T insert(T entity) {
        findEntityByProperty(entity).ifPresent(
                managedEntity -> {
                    throw new ApplicationException(
                            String.format(Messages.ENTITY_ALREADY_EXISTS, getEntityName())
                    );
                }
        );

        return repository.save(entity);
    }

    @Transactional
    public T update(T entity) throws ApplicationException {
        if ( entity.getUuid() == null) {
            throw new ApplicationException(
                    Messages.ENTITY_UUID_SHOULD_BE_NOT_NULL
            );
        }

        var persistedEntity = repository.findByUuidAndRemovedAtIsNull(entity.getUuid())
                .orElseThrow(() -> new ApplicationException(
                        String.format(Messages.ENTITY_NOT_FOUND, getEntityName())
                ));

        persistedEntity.setUpdatableFields(entity);
        repository.save(persistedEntity);
        return persistedEntity;
    }

    @Transactional
    public void remove(UUID uuid) {
        var persistedEntity = repository.findByUuidAndRemovedAtIsNull(uuid)
                .orElseThrow(
                        () -> new ApplicationException(
                                Messages.ENTITY_CANNOT_BE_DELETED_BECAUSE_IT_DOESNT_EXIST_OR_IT_WAS_ALREADY_DELETE
                        )
                );

        persistedEntity.setRemovedAt(LocalDateTime.now());
        repository.save(persistedEntity);
    }
}
