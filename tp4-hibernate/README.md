Hibernate:

    create table Machine (
       id integer not null auto_increment,
        dateAchat date,
        ref varchar(255),
        salle_id integer,
        primary key (id)
    ) engine=InnoDB
Hibernate:

    create table salles (
       id integer not null auto_increment,
        code varchar(255),
        primary key (id)
    ) engine=InnoDB
Hibernate:

    alter table Machine 
       add constraint FK786nphvfkoy6qo2959vhnwwya 
       foreign key (salle_id) 
       references salles (id)
juin 17, 2026 10:32:50 PM org.hibernate.engine.transaction.jta.platform.internal.JtaPlatformInitiator initiateService
INFO: HHH000490: Using JtaPlatform implementation: [org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform]
Hibernate:
insert
into
salles
(code)
values
(?)
Hibernate:
insert
into
salles
(code)
values
(?)
Hibernate:
select
salle0_.id as id1_1_0_,
salle0_.code as code2_1_0_,
machines1_.salle_id as salle_id4_0_1_,
machines1_.id as id1_0_1_,
machines1_.id as id1_0_2_,
machines1_.dateAchat as dateacha2_0_2_,
machines1_.ref as ref3_0_2_,
machines1_.salle_id as salle_id4_0_2_
from
salles salle0_
left outer join
Machine machines1_
on salle0_.id=machines1_.salle_id
where
salle0_.id=?
Hibernate:
select
salle0_.id as id1_1_0_,
salle0_.code as code2_1_0_,
machines1_.salle_id as salle_id4_0_1_,
machines1_.id as id1_0_1_,
machines1_.id as id1_0_2_,
machines1_.dateAchat as dateacha2_0_2_,
machines1_.ref as ref3_0_2_,
machines1_.salle_id as salle_id4_0_2_
from
salles salle0_
left outer join
Machine machines1_
on salle0_.id=machines1_.salle_id
where
salle0_.id=?
Hibernate:
insert
into
Machine
(dateAchat, ref, salle_id)
values
(?, ?, ?)
Hibernate:
insert
into
Machine
(dateAchat, ref, salle_id)
values
(?, ?, ?)
Hibernate:
select
salle0_.id as id1_1_,
salle0_.code as code2_1_
from
salles salle0_
Hibernate:
select
machines0_.salle_id as salle_id4_0_0_,
machines0_.id as id1_0_0_,
machines0_.id as id1_0_1_,
machines0_.dateAchat as dateacha2_0_1_,
machines0_.ref as ref3_0_1_,
machines0_.salle_id as salle_id4_0_1_
from
Machine machines0_
where
machines0_.salle_id=?
Hibernate:
select
machines0_.salle_id as salle_id4_0_0_,
machines0_.id as id1_0_0_,
machines0_.id as id1_0_1_,
machines0_.dateAchat as dateacha2_0_1_,
machines0_.ref as ref3_0_1_,
machines0_.salle_id as salle_id4_0_1_
from
Machine machines0_
where
machines0_.salle_id=?
Salle: A1
Machine: M123
Salle: B2
Machine: M124
Machines achetées entre Fri Jan 01 00:00:00 WET 2010 et Wed Jun 17 22:32:50 WEST 2026:
Hibernate:
select
machine0_.id as id1_0_,
machine0_.dateAchat as dateacha2_0_,
machine0_.ref as ref3_0_,
machine0_.salle_id as salle_id4_0_
from
Machine machine0_
where
machine0_.dateAchat between ? and ?
Hibernate:
select
salle0_.id as id1_1_0_,
salle0_.code as code2_1_0_,
machines1_.salle_id as salle_id4_0_1_,
machines1_.id as id1_0_1_,
machines1_.id as id1_0_2_,
machines1_.dateAchat as dateacha2_0_2_,
machines1_.ref as ref3_0_2_,
machines1_.salle_id as salle_id4_0_2_
from
salles salle0_
left outer join
Machine machines1_
on salle0_.id=machines1_.salle_id
where
salle0_.id=?
Hibernate:
select
salle0_.id as id1_1_0_,
salle0_.code as code2_1_0_,
machines1_.salle_id as salle_id4_0_1_,
machines1_.id as id1_0_1_,
machines1_.id as id1_0_2_,
machines1_.dateAchat as dateacha2_0_2_,
machines1_.ref as ref3_0_2_,
machines1_.salle_id as salle_id4_0_2_
from
salles salle0_
left outer join
Machine machines1_
on salle0_.id=machines1_.salle_id
where
salle0_.id=?
M123 achetée le 2026-06-17
M124 achetée le 2026-06-17

Process finished with exit code 0
