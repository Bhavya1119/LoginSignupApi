create database if not exists `login_page`;
use login_page;
drop table if exists `user`;
create table user(
`id` int not null auto_increment,
`name` varchar(45) default null,
`role` enum('ADMIN','DEFAULT'),
`surprise` varchar(45) default null,
`token` varchar(255),
`session` long,
primary key(id))
engine =Innodb auto_increment=1 default charset=latin1;

