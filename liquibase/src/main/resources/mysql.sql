--liquibase formatted sql
CREATE TABLE `t1_user` (
  `t_id` int(20) NOT NULL ,
  `t_name` varchar(50) NOT NULL,
  `t_age` int(10) NOT NULL,
  PRIMARY KEY (`t_id`)
) ;

--changeset liu1:2
INSERT  into t1_user(t_id, t_name, t_age)  VALUES (1,'刘绍煌',26);

--rollback drop table t1_user;




