INSERT INTO "user" (full_Name, email, password, phone_number)
VALUES ('Juan Palomo', 'juanpa@gmail.com', '12345', 654233521)
     , ('Francisco Pérez', 'fperez@gmail.com', 'blabla', 612446666)
     , ('José Manuel García', 'jmgarcia@terra.es', 'manue', 608721126)
     , ('Carles Vidal', 'vidal_c@gmail.com', 'admin', 654233521)
     , ('Nil Carbonell', 'neil@gmail.com', 'root', 654233521)
;

INSERT INTO "digital_session" (description, link, location, user_id)
VALUES ('Match Barca - PSG 2024-25', 'http://www.barca.com', 'Barcelona', 1)
     , ('Match Madrid - Atleti 2024-25', 'http://www.madrid.com', 'Madrid', 2)
     , ('Match Arsenal - Chelsea 2024-25', 'http://www.arsenal.com', 'London', 3)
;

INSERT INTO "digital_item" (description, lat, link, lon, status, digital_session_id)
VALUES ('First half', 41.3879, 'http://www.barca.com/first_half', 2.1697, 'AVAILABLE', 1)
     , ('Second half', 41.3879, 'http://www.barca.com/second_half', 2.1697, 'AVAILABLE', 1)
     , ('First half', 40.4168, 'http://www.madrid.com/first_half', -3.7038, 'NOT_AVAILABLE', 2)
     , ('Second half', 40.4168, 'http://www.madrid.com/second_half', -3.7038, 'NOT_AVAILABLE', 2)
     , ('First half', 51.5074, 'http://www.arsenal.com/first_half', -0.1278, 'REVIEW_PENDING', 3)
     , ('Second half', 51.5074, 'http://www.arsenal.com/second_half', -0.1278, 'REVIEW_PENDING', 3)
;

INSERT INTO "alert" ("from", product_id, "to", user_id)
VALUES ('2025-01-01', 1, '2025-12-31', 1)
     , ('2025-01-01', 2, '2025-12-31', 2)
     , ('2025-01-01', 3, '2025-12-31', 3)
     , ('2025-01-01', 4, '2025-12-31', 4)
     , ('2025-01-01', 5, '2025-12-31', 5)
;
