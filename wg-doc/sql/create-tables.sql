DROP database if exists `wg-db`;
create database `wg-db`;
USE `wg-db`;

CREATE TABLE `sys_user` (
`id`  int not null auto_increment,
`username` varchar(256) not null,
`password` varchar(256) not null, 
`email` varchar(256) not null,
`first_name` varchar(256) not null,
`last_name` varchar(256) not null,
`create_date` timestamp DEFAULT CURRENT_TIMESTAMP,
`update_date` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
`active` tinyint default 1,
CONSTRAINT primary key(`id`),
CONSTRAINT unique(`username`),
CONSTRAINT unique(`email`)
) engine=InnoDB;

create TABLE `user_role` (
	`role_id` int not null auto_increment,
    `role_name` varchar(128) not null unique,
    `create_date` timestamp DEFAULT CURRENT_TIMESTAMP,
    `update_date` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT primary key (`role_id`)    
) engine= InnoDB;

create table `sys_user_user_role` (
	`role_id` int not null,
    `user_id` int not null,
    `create_date` timestamp DEFAULT CURRENT_TIMESTAMP,
    `update_date` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT primary key (`role_id`,`user_id`),
    CONSTRAINT foreign key (`role_id`) references `user_role`(`role_id`) ON DELETE CASCADE,
    CONSTRAINT foreign key(`user_id`) references `sys_user`(`id`) ON DELETE CASCADE
) engine= InnoDB;


create table `auth_client` (
`id` int not null auto_increment primary key,
`client_id` varchar(256) not null unique,
`client_name` varchar(128) default '', ## description
`client_secret` varchar(256) not null,
`authorized_grant_types` varchar(256) not null,  #authorization_code,password,refresh_token,client_credentials
`scopes` varchar(256),
`resource_ids` varchar(256),
`access_token_validity` int,
`refresh_token_validity` int,
`additional_information` varchar(4096),
`web_server_redirect_uri` varchar(256),
`auto_approve` tinyint default 1,
`create_date` timestamp DEFAULT CURRENT_TIMESTAMP,
`update_date` timestamp DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP
) engine = InnoDB;


create TABLE `client_role` (
	`role_id` int not null auto_increment,
    `role_name` varchar(128) not null unique,
    `create_date` timestamp DEFAULT CURRENT_TIMESTAMP,
    `update_date` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT primary key (`role_id`)    
) engine= InnoDB;

create table `auth_client_client_role` (
	`role_id` int not null,
    `client_id` int not null,
    `create_date` timestamp DEFAULT CURRENT_TIMESTAMP,
    `update_date` timestamp DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP,
    constraint primary key(`role_id`,`client_id`),
    constraint foreign key(`role_id`) references `client_role`(`role_id`) ON DELETE CASCADE,
    constraint foreign key(`client_id`) references `auth_client`(`id`) ON DELETE CASCADE
) engine = InnoDB;
