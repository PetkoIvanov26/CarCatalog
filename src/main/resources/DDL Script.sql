create schema carCatalog

create table if not exists carcatalog.brands(
	id SERIAL,
	name varchar(255) UNIQUE,
	constraint brands_pkey PRIMARY KEY (id)
);
create table if not exists carcatalog.transmissions(
	id SERIAL,
	name varchar(255) UNIQUE,
	constraint transmissions_pkey PRIMARY KEY (id)
);
create table if not exists carcatalog.fuel_types(
	id SERIAL,
	name varchar(255) UNIQUE,
	constraint fuel_types_pkey PRIMARY KEY (id)
);
create table if not exists carcatalog.models(
	id SERIAL,
	brand_id integer,
	name varchar(255) UNIQUE,
	constraint models_pkey PRIMARY KEY (id),
	constraint fk_models_brand_id FOREIGN KEY (brand_id)
	REFERENCES carcatalog.brands (id) 
	ON UPDATE CASCADE 
	ON DELETE CASCADE
);
create table if not exists carcatalog.cars(
	id SERIAL,
	vin_number varchar(255) UNIQUE ,
	model_id integer ,
	price double precision,
	reg_date date,
	transmission_id integer,
	fuel_type_id integer,
	constraint cars_pkey PRIMARY KEY (id),
	
	constraint fk_cars_model_id FOREIGN KEY (model_id)
	REFERENCES carcatalog.models(id)
	ON UPDATE CASCADE
	ON DELETE CASCADE,
	
	constraint fk_cars_transmission_id FOREIGN KEY (transmission_id)
	REFERENCES carcatalog.transmissions(id)
	ON UPDATE CASCADE
	ON DELETE CASCADE,
	
	constraint fk_cars_fuel_type_id FOREIGN KEY (fuel_type_id)
	REFERENCES carcatalog.fuel_types(id)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);