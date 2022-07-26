create table member
(
    id bigint generated by default as identity,
    name varchar(255),
    loginId varchar(255),
    loginPwd varchar(255),
    role int,
    primary key (id)
);

create table post
(
    id bigint generated by default as identity,
    userId bigint,
    title varchar(255),
    views bigint,
    recommended bigint,
    primary key (id),
    FOREIGN key(userId) references member(id) ON UPDATE CASCADE
);

