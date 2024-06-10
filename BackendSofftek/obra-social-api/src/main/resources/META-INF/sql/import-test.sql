-- Inserta la ubicación del especialista
INSERT INTO locations (id,street, locality, province, country)
VALUES (1,'Avenida Corrientes 456', 'Buenos Aires', 'Ciudad Autónoma de Buenos Aires', 'Argentina');

-- Inserta el especialista
INSERT INTO Specialist (id,firstName, lastName, dni, email, password, role, speciality, location_id)
VALUES (1,'Nombre', 'Apellido', '12345678', 'especialista@example.com', 'hashedPassword', 'USER', 'DERMATOLOGY',
        (SELECT id FROM locations ORDER BY id DESC LIMIT 1));