-- Insertar registros de ejemplo
INSERT INTO affiliate (id, first_name, last_name, dni, role, health_insurance_code, email)
VALUES
(1, 'Joaquin', 'Perez', '1233678', 'USER', '21258', 'JuanPerez@gmail.com'),
(2, 'María', 'González', '9876543', 'ADMIN', '33579', 'MariaGonzalez@gmail.com'),
(3, 'Carlos', 'López', '7654321', 'USER', '12457', 'CarlosLopez@gmail.com'),
(4, 'Laura', 'Martínez', '5678912', 'USER', '88764', 'LauraMartinez@gmail.com'),
(5, 'Andrés', 'Rodríguez', '2345678', 'USER', '97531', 'AndresRodriguez@gmail.com'),
(6, 'Ana', 'Sánchez', '8765432', 'ADMIN', '64238', 'AnaSanchez@gmail.com'),
(7, 'Diego', 'Gómez', '3456789', 'USER', '52371', 'DiegoGomez@gmail.com'),
(8, 'Lucía', 'Fernández', '6543210', 'USER', '13985', 'LuciaFernandez@gmail.com'),
(9, 'Pedro', 'Díaz', '4321098', 'ADMIN', '76423', 'PedroDiaz@gmail.com'),
(10, 'Carolina', 'Ruiz', '7890123', 'USER', '30876', 'CarolinaRuiz@gmail.com');



INSERT INTO locations (id, street, locality, province, country)
VALUES
(1, 'Avenida Corrientes 4222', 'Buenos Aires', 'Ciudad Autónoma de Buenos Aires', 'Argentina');


INSERT INTO specialist (id, first_name, last_name, dni, speciality, role, email, location_id)
VALUES
(1, 'Joaquín', 'Pérez', '12345678', 'DERMATOLOGY', 'USER', 'joaquin.perez@example.com', 1);

