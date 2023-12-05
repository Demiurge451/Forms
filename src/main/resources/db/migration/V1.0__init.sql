create table accounts(
	id serial primary key,
	login varchar unique not null,
	password varchar not null,
	number varchar not null,
	email varchar not null
);

create table forms(
	id serial primary key,
	foreword varchar
);

create table questions(
	id serial primary key,
	multiply_selection boolean,
	ord int,
	txt varchar,
	form_id int,
	foreign key (form_id) references forms(id)
);

create table answers(
	id serial primary key,
	ord int,
	txt varchar,
	question_id int,
	foreign key (question_id) references questions(id)
);

create table results(
	id serial primary key,
	answer_id int,
	foreign key (answer_id) references answers(id)
);