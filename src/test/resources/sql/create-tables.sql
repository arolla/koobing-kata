create table hotels
(
    hotel_id int primary key,
    name      varchar(255),
    street    varchar(255),
    zipcode   varchar(15),
    city      varchar(255),
    rating    float
);

create table bedrooms
(
    room_id     uuid primary key,
    room_number int not null,
    price       int not null,
    hotel_id int references hotels (hotel_id)
);
create index fk_bedrooms_hotels on bedrooms (hotel_id);

create table amenities
(
    hotel_id int references hotels (hotel_id),
    amenity   varchar(255)
);
create index fk_amenities_hotels on amenities (hotel_id);

create table bookings
(
    booking_id  uuid primary key,
    guest_email varchar(255) not null,
    room_id     uuid         not null,
    start_date  date         not null,
    end_date    date         not null
);

