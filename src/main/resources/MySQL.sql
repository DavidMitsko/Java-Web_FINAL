create table user (id int auto_increment, login nvarchar(20) not null unique, password nvarchar(20) not null, role nvarchar(6), status nvarchar(10), averageRating float, primary key (id));

create table movie (id int auto_increment, name nvarchar(50) not null unique, averageRating float, countOfRatings int, primary key(id));

create table review (id int auto_increment, userID int, movieID int, review nvarchar(1000), foreign key (userID) references user (id), foreign key (movieID) references movie (id), primary key (id));

create table rating (id int auto_increment, userID int, movieID int, rating float, foreign key (userID) references user (id), foreign key (movieID) references movie (id), primary key (id));

create table recount (id int auto_increment, userID int, movieID int, direct int, primary key(id), foreign key (userID) references user (id), foreign key (movieID) references movie (id));