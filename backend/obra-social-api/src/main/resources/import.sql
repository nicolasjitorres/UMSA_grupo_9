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
(1, 'Avenida Corrientes 4222', 'Buenos Aires', 'Ciudad Autónoma de Buenos Aires', 'Argentina'),
(2, 'Rio Negro 2551', 'Buenos Aires', 'Ciudad Autónoma de Buenos Aires', 'Argentina'),
(3, 'Corrientes 8211', 'Buenos Aires', 'Ciudad Autónoma de Buenos Aires', 'Argentina'),
(4, 'Palermo 1021', 'Buenos Aires', 'Ciudad Autónoma de Buenos Aires', 'Argentina'),
(5, 'Colonia 1961', 'Buenos Aires', 'Ciudad Autónoma de Buenos Aires', 'Argentina'),
(6, 'Santa Fe 1502', 'Buenos Aires', 'Ciudad Autónoma de Buenos Aires', 'Argentina'),
(7, 'Senador Moron 1051', 'Buenos Aires', 'Ciudad Autónoma de Buenos Aires', 'Argentina'),
(8, 'Rio colorado 2012', 'Buenos Aires', 'Ciudad Autónoma de Buenos Aires', 'Argentina'),
(9, 'Eva duarte 1921', 'Buenos Aires', 'Ciudad Autónoma de Buenos Aires', 'Argentina'),
(10, 'Color 198', 'Buenos Aires', 'Ciudad Autónoma de Buenos Aires', 'Argentina');


INSERT INTO specialist (id, first_name, last_name, dni, speciality, role, email, location_id)
VALUES
(1, 'Joaquín', 'Pérez', '12345678', 'DERMATOLOGY', 'USER', 'joaquin.perez@example.com', 1),
(2, 'Marcos', 'Sanchez', '41872372', 'DERMATOLOGY', 'USER', 'Marcos.sanchez@example.com', 2),
(3, 'Lucas', 'Rodriguez', '21826722', 'DERMATOLOGY', 'USER', 'lucas.rodriguez@example.com', 3),
(4, 'Javier', 'Dominguez', '31898622', 'DERMATOLOGY', 'USER', 'javier.dominguez@example.com', 4),
(5, 'Lucia', 'Mansilla', '23752242', 'DERMATOLOGY', 'USER', 'lucia.mansilla@example.com', 5),
(6, 'Sofia', 'Esquivel', '31452729', 'DERMATOLOGY', 'USER', 'sofia.esquivel@example.com', 6),
(7, 'Ana', 'Fernandez', '31928621', 'DERMATOLOGY', 'USER', 'ana.fernandez@example.com', 7),
(8, 'Paula', 'Romero', '23422422', 'DERMATOLOGY', 'USER', 'paula.romero@example.com', 8),
(9, 'Diego', 'Gomez', '27822723', 'DERMATOLOGY', 'USER', 'diego.gomez@example.com', 9),
(10, 'Martin', 'Diaz', '32822521', 'DERMATOLOGY', 'USER', 'martin.diaz@example.com', 10);



