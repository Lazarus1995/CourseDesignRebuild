create database fanchao;

use fanchao;

create table student(
	sid int primary key,
	sname varchar(20),
	ssex varchar(20),
	sage int,
	sdept varchar(20)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table user(
	sid int,
	password varchar(20),
	primary key(sid),
	foreign key(sid) references student(sid)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table course(
	cid int primary key,
	cname varchar(20)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table teacher(
	tid int primary key,
	tname varchar(20),
	tsex varchar(20),
	tage int,
	tdept varchar(20),
	tpassword varchar(20)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table info(
	sid int,
	cid int,
	tid int,
	primary key(sid,cid,tid),
	foreign key(sid) references student(sid),
	foreign key(cid) references course(cid),
	foreign key(tid) references teacher(tid)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table admin(
	username varchar(20),
	password varchar(20),
	primary key(username)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



insert into student values(1322450530,'范超','男',21,'信息技术学院');
insert into student values(1322450531,'张三','女',20,'信息技术学院');
insert into student values(1322450532,'李四','男',21,'信息技术学院');


insert into user values(1322450530,'1322450530');
insert into user values(1322450531,'1322450531');
insert into user values(1322450532,'1322450532');


insert into course values('1','Java');
insert into course values('2','C');
insert into course values('3','Python');
insert into course values('4','HTML');
insert into course values('5','Javascript');


insert into teacher values(1000000001,'张老师','男',20,'信息技术学院','1000000001');
insert into teacher values(1000000002,'李老师','女',21,'信息技术学院','1000000002');



insert into info values(1322450530,1,1000000001);
insert into info values(1322450530,2,1000000002);

insert into info values(1322450531,3,1000000001);
insert into info values(1322450531,4,1000000002);

insert into info values(1322450532,5,1000000001);
insert into info values(1322450532,1,1000000002);
insert into info values(1322450532,2,1000000001);
insert into info values(1322450532,3,1000000002);




insert into admin values('admin','admin');

