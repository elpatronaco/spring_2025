INSERT INTO "category" (name, description, parent_id)
VALUES ('Cámaras', 'Cámaras', null)
     , ('Cámaras de Fotos', 'Cámaras de Fotos', 1)
     , ('Cámaras de Video', 'Cámaras de Video', 1)
     , ('Iluminación', 'Todo tipo de accesorios de iluminación', null)
     , ('Focos', 'Focos direccionales, omnidireccionales, etc.', 4)
     , ('Difusores', 'Difusores para focos', 4)
;

INSERT INTO "product" (name, description, daily_price, brand, model, category_id, status)
VALUES ('Canon 500D', 'Cámara de fotos Canon 500D', 100, 'Canon', '500D', 2, 'ACTIVE')
     , ('Canon EOS R8', 'Cámara de fotos Canon EOS R8', 200, 'Canon', 'EOS R8', 2, 'ACTIVE')
     , ('Canon EOS R5 C', 'Cámara de video Canon EOS R5 C', 250, 'Canon', 'EOS R5 C', 3, 'INACTIVE')
     , ('Foco Phillips 120L', 'Foco LED Phillips de luz blanca 120W. Sin difusor', 80, 'Phillips', '120L', 5, 'ACTIVE')
     , ('Foco Phillips 220L', 'Foco LED Phillips de luz blanca 220W. Sin difusor', 120, 'Phillips', '220L', 5, 'INACTIVE')
;

INSERT INTO "item" (serial_number, status, product_id)
VALUES ('1234526789', 'OPERATIONAL', 1)
     , ('9872344321', 'OPERATIONAL', 2)
     , ('1234546741', 'NON_OPERATIONAL', 3)
     , ('9874154321', 'OPERATIONAL', 4)
     , ('1234416789', 'NON_OPERATIONAL', 5)
;
CREATE SEQUENCE IF NOT EXISTS category_id_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS product_id_seq START WITH 1 INCREMENT BY 1;