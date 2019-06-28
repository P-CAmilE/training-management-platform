create table company_info
(
	com_name varchar(20),
    com_account varchar(20) primary key,
    com_password varchar(20),
    com_email varchar(20)
);

create table teacher_info
(
	tea_name varchar(20),
    tea_account varchar(20) primary key,
    tea_password varchar(20),
    tea_email varchar(20)
);

create table student_info
(
	stu_name varchar(20),
    stu_account varchar(20) primary key,
    tea_password varchar(20),
    tea_email varchar(20)
);



    
    