create table city(
    city_id uuid primary key,
    name varchar(100) not null,
    country_code varchar(2) not null,
    latitude decimal(9, 6) not null,
    longitude decimal(9, 6) not null,
    created_at TIMESTAMP not null,
    constraint uk_city_name_country unique (name, country_code)
);
create table measurement(
    measurement_id uuid primary key,
    temperature double precision not null,
    apparent_temperature double precision not null,
    weather_code varchar(50) not null,
    rain_in_mm double precision not null,
    snowfall double precision not null,
    wind_speed_10m double precision not null,
    wind_direction_10m double precision not null,
    surface_pressure double precision not null,
    pressure_at_sea_level double precision not null,
    relative_humidity_2m integer not null,
    cloud_cover integer not null,
    is_day boolean not null,
    measured_at timestamp not null,
    city_id uuid not null,
    foreign key (city_id) references city(city_id)
);