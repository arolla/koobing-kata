-- 2 hostels in Angers
INSERT INTO hostels (hostel_id, name, street, zipcode, city, rating)
VALUES (10, 'Urban Retreat Hostel', '5 Rue du Château', '49000', 'Angers', 4.0),
       (20, 'Charmant Château', '14 Rue du Château', '49000', 'Angers', 4.8);

-- 4 hostels in Nantes
INSERT INTO hostels (hostel_id, name, street, zipcode, city, rating)
VALUES (40, 'Downtown Digs', '8 Boulevard Voltaire', '44000', 'Nantes', 1.8),
       (50, 'Mountain View Manor', '30 Rue de la Montagne', '44000', 'Nantes', 3.9),
       (60, 'City Lights Lodge', '22 Rue Urbaine', '44000', 'Nantes', 4.2),
       (70, 'Harbor Hostel', '3 Quai des Marins', '44000', 'Nantes', 1.0);


-- Bedrooms for 'Urban Retreat Hostel' in Angers
INSERT INTO bedrooms (room_id, room_number, price, hostel_id)
VALUES ('a9c0e4a5-37bf-42cd-8bc2-d78542e5f7f3', 10, 80, 10),
       ('6d8eab54-79c6-4e7a-8f3c-2bb42d7a54f9', 11, 85, 10);

-- Bedrooms for 'Charmant Château' in Angers
INSERT INTO bedrooms (room_id, room_number, price, hostel_id)
VALUES ('bf7ac693-37e6-4d6e-9c23-1f48bc09d3f7', 15, 105, 20),
       ('8c1f063d-d2c2-4d3c-8c71-d38c6b8ea9b1', 16, 110, 20),
       ('e8f6df1e-610d-4bb8-bb77-6924e82d2b6e', 17, 115, 20),
       ('9f48bb36-3f23-4b09-8f62-9fc639e4b3e2', 18, 120, 20),
       ('4a8a5f4c-2f07-4713-ba6d-4f9960f800a1', 19, 125, 20);

-- Bedrooms for 'Downtown Digs' in Nantes
INSERT INTO bedrooms (room_id, room_number, price, hostel_id)
VALUES ('c43fd875-4a0c-4f67-93cc-1d5e631f0f8d', 10, 80, 40),
       ('62397482-1d45-4d22-bbbe-9e5e631c835f', 11, 85, 40),
       ('f57b2d23-786b-4a6d-9c19-d1c6f532c9a2', 12, 90, 40),
       ('b12e8cfe-92bf-4a77-93d4-0c5751a18fb3', 13, 95, 40),
       ('84d4a6a2-31b2-4c5a-814c-3f03269b050f', 14, 100, 40);

-- Bedrooms for 'Mountain View Manor' in Nantes
INSERT INTO bedrooms (room_id, room_number, price, hostel_id)
VALUES ('c5785e42-6513-4a0a-9d9e-7db6d9d4f72f', 15, 105, 50),
       ('a8ef9323-9a10-4bb8-bb09-176b8e4d59f9', 16, 110, 50),
       ('e4f9f1dc-9b6a-439d-8a92-91a0d1508f36', 17, 115, 50),
       ('1e68e4ae-6e3d-4a02-821a-1766f4c7a69d', 18, 120, 50),
       ('bc69b95f-bf0e-47a3-aa02-5cc6510b4952', 19, 125, 50);

-- Bedrooms for 'City Lights Lodge' in Nantes
INSERT INTO bedrooms (room_id, room_number, price, hostel_id)
VALUES ('d78f9cfb-c8b9-4f2b-86c2-7e8a0b72f3d4', 20, 130, 60),
       ('3b9bb152-49c5-4c5c-8e3b-97f57293c0d9', 21, 135, 60),
       ('a1b9e0cd-72fe-4f28-8bd1-05dbbb4e5c81', 22, 140, 60),
       ('5f4c7e98-37ab-4d75-a3f1-ef0341577f01', 23, 145, 60),
       ('c5a5e52f-8108-4a4f-9c1a-3e38f12d8f4d', 24, 150, 60);

-- Bedrooms for 'Harbor Hostel' in Nantes
INSERT INTO bedrooms (room_id, room_number, price, hostel_id)
VALUES ('8e1b3d56-932c-4a59-af88-62b03e58d2fc', 10, 80, 70),
       ('de0d7efb-ec14-4bd5-9859-dc4ebaa06c91', 11, 85, 70),
       ('317a8c4a-b1b7-4fe8-a399-0f7ef711ad2f', 12, 90, 70),
       ('67e77d3b-f7e5-4d08-947b-0f01de3e1a53', 13, 95, 70),
       ('da6ff61c-9b0a-4914-9c2b-9a7991b8c1b8', 14, 100, 70);

-- Amenities for 'Urban Retreat Hostel' in Angers
INSERT INTO amenities (hostel_id, amenity)
VALUES (10, 'Free Wi-Fi'),
       (10, 'Breakfast Buffet'),
       (10, 'Common Lounge Area');

-- Amenities for 'Charmant Château' in Angers
INSERT INTO amenities (hostel_id, amenity)
VALUES (20, 'Free Wi-Fi'),
       (20, 'Library'),
       (20, 'Outdoor Garden');

-- Amenities for 'Downtown Digs' in Nantes
INSERT INTO amenities (hostel_id, amenity)
VALUES (40, 'Free Wi-Fi'),
       (40, 'Bike Rental'),
       (40, '24-Hour Reception');

-- Amenities for 'Mountain View Manor' in Nantes
INSERT INTO amenities (hostel_id, amenity)
VALUES (50, 'Free Wi-Fi'),
       (50, 'Mountain View Balcony'),
       (50, 'Private Parking');

-- Amenities for 'City Lights Lodge' in Nantes
INSERT INTO amenities (hostel_id, amenity)
VALUES (60, 'Free Wi-Fi'),
       (60, 'City View Lounge'),
       (60, 'Laundry Services');

-- Amenities for 'Harbor Hostel' in Nantes
INSERT INTO amenities (hostel_id, amenity)
VALUES (70, 'Free Wi-Fi'),
       (70, 'Harborfront Terrace'),
       (70, 'Boat Excursions');

-- Booking for 'Urban Retreat Hostel' in Angers
INSERT INTO bookings (booking_id, guest_email, room_id, start_date, end_date)
VALUES ('6b0a39f4-4bc2-4c84-9d7a-5e7f0b4c23aa', 'john.doe123@example.com', 'a9c0e4a5-37bf-42cd-8bc2-d78542e5f7f3',
        '2024-02-01', '2024-06-30'),
       ('8c1c7a20-5908-4f45-a4eb-0217e162b160', 'chris.brown1234@yahoo.com', '6d8eab54-79c6-4e7a-8f3c-2bb42d7a54f9',
        '2024-02-01', '2024-06-30');

-- Booking for 'Charmant Château' in Angers
INSERT INTO bookings (booking_id, guest_email, room_id, start_date, end_date)
VALUES ('a3f8e937-8202-4a0d-8419-3e67e7a3c8e1', 'alice.smith456@gmail.com', '8c1f063d-d2c2-4d3c-8c71-d38c6b8ea9b1',
        '2024-02-01', '2024-06-30');

-- Booking for 'Downtown Digs' in Nantes
INSERT INTO bookings (booking_id, guest_email, room_id, start_date, end_date)
VALUES ('1cf3b1d2-eeab-4a85-a8cb-8b7d084e8918', 'sam_jones789@hotmail.com', 'f57b2d23-786b-4a6d-9c19-d1c6f532c9a2',
        '2024-02-01', '2024-06-30');

-- Booking for 'Mountain View Manor' in Nantes
INSERT INTO bookings (booking_id, guest_email, room_id, start_date, end_date)
VALUES ('c034e0d4-2a46-4edc-813b-158cb9f2d150', 'emily.johnson234@yahoo.com', 'c5785e42-6513-4a0a-9d9e-7db6d9d4f72f',
        '2024-02-01', '2024-06-30');

-- Booking for 'City Lights Lodge' in Nantes
INSERT INTO bookings (booking_id, guest_email, room_id, start_date, end_date)
VALUES ('9b9275a9-3b5b-4a5f-a9b7-26fbc332457a', 'michael.clark789@hotmail.com', 'd78f9cfb-c8b9-4f2b-86c2-7e8a0b72f3d4',
        '2024-02-01', '2024-06-30');
