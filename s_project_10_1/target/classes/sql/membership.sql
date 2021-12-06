create table membership(
	id varchar2(20) primary key,
	pwd varchar2(100),
	addr varchar2(300)
);
insert into membership values('AA','aa','호주');
insert into membership values('BB','bb','유럽');
insert into membership values('CC','cc','미국');
commit;