insert into `user_role`(`role_name`) values('ROLE_USER');
insert into `user_role`(`role_name`) values ('ROLE_ADMIN');


insert into  `sys_user` (`username`, `email`,`password`,`first_name`,`last_name`,`active`) values('jerryguowei','jerryguowei@gmail.com','$2y$10$06xXY/0DPVGrWtLSV66V9.6LE0nEYJCW8bBUc3P1LB9Z9wC/ZRYYu','WEI','GUO', 1);
insert into  `sys_user` (`username`, `email`, `password`,`first_name`,`last_name`,`active`) values('davidgao','davidgao@gmail.com','$2y$10$06xXY/0DPVGrWtLSV66V9.6LE0nEYJCW8bBUc3P1LB9Z9wC/ZRYYu','David','Gao', 1);

insert into `sys_user_user_role` (`role_id`,`user_id`) values(1,1);
insert into `sys_user_user_role` (`role_id`,`user_id`) values(2,1);
insert into `sys_user_user_role` (`role_id`,`user_id`) values(1,2);

insert into `auth_client` (`client_id`,`client_name`,`client_secret`,`authorized_grant_types`,`scopes`,`resource_ids`,`access_token_validity`,`refresh_token_validity`,`additional_information`,`web_server_redirect_uri`)
values('front-end','Front End','$2y$10$06xXY/0DPVGrWtLSV66V9.6LE0nEYJCW8bBUc3P1LB9Z9wC/ZRYYu', 'refresh_token,password,client_credentials', 'front', null, 3600, 72000, '','');

insert into `auth_client` (`client_id`,`client_name`,`client_secret`,`authorized_grant_types`,`scopes`,`resource_ids`,`access_token_validity`,`refresh_token_validity`,`additional_information`,`web_server_redirect_uri`)
values('user-service','Manage User','$2y$10$06xXY/0DPVGrWtLSV66V9.6LE0nEYJCW8bBUc3P1LB9Z9wC/ZRYYu', 'client_credentials', 'server', null, 3600, 72000, '','');

insert into `client_role`(`role_name`) values ('ROLE_CLIENT');

insert into `auth_client_client_role` (`role_id`,`client_id`) values (1,1);
