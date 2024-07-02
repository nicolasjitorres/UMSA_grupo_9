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
(2, 'Marcos', 'Sanchez', '41872372', 'GENERALMEDICINE', 'USER', 'Marcos.sanchez@example.com', 2),
(3, 'Lucas', 'Rodriguez', '21826722', 'CARDIOLOGY', 'USER', 'lucas.rodriguez@example.com', 3),
(4, 'Javier', 'Dominguez', '31898622', 'DERMATOLOGY', 'USER', 'javier.dominguez@example.com', 4),
(5, 'Lucia', 'Mansilla', '23752242', 'NEUROLOGY', 'USER', 'lucia.mansilla@example.com', 5),
(6, 'Sofia', 'Esquivel', '31452729', 'GENERALMEDICINE', 'USER', 'sofia.esquivel@example.com', 6),
(7, 'Ana', 'Fernandez', '31928621', 'DERMATOLOGY', 'USER', 'ana.fernandez@example.com', 7),
(8, 'Paula', 'Romero', '23422422', 'ENDOCRINOLOGY', 'USER', 'paula.romero@example.com', 8),
(9, 'Diego', 'Gomez', '27822723', 'UROLOGY', 'USER', 'diego.gomez@example.com', 9),
(10, 'Martin', 'Diaz', '32822521', 'DERMATOLOGY', 'USER', 'martin.diaz@example.com', 10);

INSERT INTO schedules (id, start_time, end_time, day_of_week, specialist_id)
VALUES
-- Especialista 1
(1, '08:00:00', '17:00:00', 1, 1),
(2, '08:00:00', '17:00:00', 2, 1),
(3, '08:00:00', '17:00:00', 3, 1),
(4, '08:00:00', '17:00:00', 4, 1),
(5, '08:00:00', '17:00:00', 5, 1),

-- Especialista 2
(6, '08:00:00', '17:00:00', 1, 2),
(7, '08:00:00', '17:00:00', 2, 2),
(8, '08:00:00', '17:00:00', 3, 2),
(9, '08:00:00', '17:00:00', 4, 2),
(10, '08:00:00', '17:00:00', 5, 2),

-- Especialista 3
(11, '08:00:00', '17:00:00', 1, 3),
(12, '08:00:00', '17:00:00', 2, 3),
(13, '08:00:00', '17:00:00', 3, 3),
(14, '08:00:00', '17:00:00', 4, 3),
(15, '08:00:00', '17:00:00', 5, 3),

-- Especialista 4
(16, '08:00:00', '17:00:00', 1, 4),
(17, '08:00:00', '17:00:00', 2, 4),
(18, '08:00:00', '17:00:00', 3, 4),
(19, '08:00:00', '17:00:00', 4, 4),
(20, '08:00:00', '17:00:00', 5, 4),

-- Especialista 5
(21, '08:00:00', '17:00:00', 1, 5),
(22, '08:00:00', '17:00:00', 2, 5),
(23, '08:00:00', '17:00:00', 3, 5),
(24, '08:00:00', '17:00:00', 4, 5),
(25, '08:00:00', '17:00:00', 5, 5),

-- Especialista 6
(26, '08:00:00', '17:00:00', 1, 6),
(27, '08:00:00', '17:00:00', 2, 6),
(28, '08:00:00', '17:00:00', 3, 6),
(29, '08:00:00', '17:00:00', 4, 6),
(30, '08:00:00', '17:00:00', 5, 6),

-- Especialista 7
(31, '08:00:00', '17:00:00', 1, 7),
(32, '08:00:00', '17:00:00', 2, 7),
(33, '08:00:00', '17:00:00', 3, 7),
(34, '08:00:00', '17:00:00', 4, 7),
(35, '08:00:00', '17:00:00', 5, 7),

-- Especialista 8
(36, '08:00:00', '17:00:00', 1, 8),
(37, '08:00:00', '17:00:00', 2, 8),
(38, '08:00:00', '17:00:00', 3, 8),
(39, '08:00:00', '17:00:00', 4, 8),
(40, '08:00:00', '17:00:00', 5, 8),

-- Especialista 9
(41, '08:00:00', '17:00:00', 1, 9),
(42, '08:00:00', '17:00:00', 2, 9),
(43, '08:00:00', '17:00:00', 3, 9),
(44, '08:00:00', '17:00:00', 4, 9),
(45, '08:00:00', '17:00:00', 5, 9),

-- Especialista 10
(46, '08:00:00', '17:00:00', 1, 10),
(47, '08:00:00', '17:00:00', 2, 10),
(48, '08:00:00', '17:00:00', 3, 10),
(49, '08:00:00', '17:00:00', 4, 10),
(50, '08:00:00', '17:00:00', 5, 10);

INSERT INTO shift (id, description, date, time, specialist_id, affiliated_id)
VALUES
(1, 'Consulta médica de rutina', '2024-07-02', '08:30:00', 1, 1),
(2, 'Consulta médica de rutina', '2024-07-02', '09:30:00', 1, 2),
(3, 'Consulta médica de rutina', '2024-07-02', '10:30:00', 1, 3),
(4, 'Consulta médica de rutina', '2024-07-02', '08:30:00', 1, 4),
(5, 'Consulta médica de rutina', '2024-07-02', '08:30:00', 2, 5),
(6, 'Consulta médica de rutina', '2024-07-02', '08:30:00', 3, 6),
(7, 'Consulta médica de rutina', '2024-07-02', '08:30:00', 5, 7),
(8, 'Consulta de seguimiento', '2024-07-03', '09:00:00', 2, 3),
(9, 'Consulta de diagnóstico', '2024-07-03', '10:00:00', 3, 4),
(10, 'Consulta de control', '2024-07-04', '11:00:00', 4, 5),
(11, 'Consulta de rutina', '2024-07-04', '12:00:00', 5, 6),
(12, 'Consulta especializada', '2024-07-05', '13:00:00', 6, 7),
(13, 'Examen médico general', '2024-07-05', '14:00:00', 7, 8),
(14, 'Consulta urgente', '2024-07-06', '15:00:00', 8, 9),
(15, 'Consulta pediátrica', '2024-07-06', '16:00:00', 9, 10),
(16, 'Consulta ginecológica', '2024-07-07', '17:00:00', 10, 1);


INSERT INTO prescription (id, description, shift_id)
VALUES
(1, 'Receta para tratamiento dermatológico', 1),
(2, 'Receta para control ', 2),
(3, 'Receta para seguimiento de enfermedad crónica', 3),
(4, 'Receta para tratamiento', 4),
(5, 'Receta para análisis de laboratorio', 5);

