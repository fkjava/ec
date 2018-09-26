create table id_user(
	id int primary key auto_increment,
    name varchar(255), 
    login_name varchar(255),
    password varchar(255),
    email varchar(255), 
    active_code varchar(36)
);