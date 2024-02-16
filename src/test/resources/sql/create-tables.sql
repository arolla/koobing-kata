create table hostels
(
    hostel_id int primary key,
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
    hostel_id   int references hostels (hostel_id)
);
create index fk_bedrooms_hostels on bedrooms (hostel_id);

create table amenities
(
    hostel_id int references hostels (hostel_id),
    amenity   varchar(255)
);
create index fk_amenities_hostels on amenities (hostel_id);

create table bookings
(
    booking_id  uuid primary key,
    guest_email varchar(255) not null,
    room_id     uuid         not null,
    start_date  date         not null,
    end_date    date         not null
);

