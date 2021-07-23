insert into plan (id, created_at, installments, modalities, fees)
values (1, now(), '{0,1,2,3,4,5,6,7,8,9,10,11,12}', '{2,30,60,90,120,150,180,210,240,270,300,330,360}', '{0.5,2.1,2.1,2.1,2.1,2.1,2.1,2.7,2.7,2.7,2.7,2.7,2.7}');

insert into client (id, name, plan_id) values (1, 'ViniShow', 1);
