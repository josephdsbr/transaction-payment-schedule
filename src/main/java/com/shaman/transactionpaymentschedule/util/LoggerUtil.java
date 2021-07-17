package com.shaman.transactionpaymentschedule.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shaman.transactionpaymentschedule.exception.ApplicationException;
import com.shaman.transactionpaymentschedule.message.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.boot.actuate.trace.http.HttpTrace;

public class LoggerUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerUtil.class);

    private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static void info(Object entity) {
        try {
            String value = OBJECT_MAPPER.writeValueAsString(entity);
            LOGGER.info(value);
        } catch (Exception exception) {
            throw new ApplicationException(Messages.LOGGER_ERROR);
        }
    }

    public static void error(Object entity) {
        try {
            String value = OBJECT_MAPPER.writeValueAsString(entity);
            LOGGER.error(value);
        } catch (Exception exception) {
            throw new ApplicationException(Messages.LOGGER_ERROR);
        }
    }

    public static void info (HttpTrace trace) {
        Marker marker = MarkerFactory.getMarker("http-trace");
        try {
            String value = OBJECT_MAPPER.writeValueAsString(trace);
            LOGGER.info(marker, value);
        } catch (Exception exception) {
            throw new ApplicationException(Messages.LOGGER_ERROR);
        }
    }
}