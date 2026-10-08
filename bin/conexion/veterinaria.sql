-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 08-10-2026 a las 20:36:36
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `veterinaria`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `consulta`
--

CREATE TABLE `consulta` (
  `id_consulta` int(11) NOT NULL,
  `procedimiento` varchar(45) NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `id_mascota` int(11) NOT NULL,
  `id_veterinario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `consulta`
--

INSERT INTO `consulta` (`id_consulta`, `procedimiento`, `fecha`, `hora`, `id_mascota`, `id_veterinario`) VALUES
(1, 'Consulta general y vacunación', '2026-03-01', '10:30:00', 1, 1),
(2, 'Control de peso y desparasitación', '2026-03-02', '11:15:00', 2, 2),
(3, 'Revisión dermatológica', '2026-03-03', '09:00:00', 3, 3),
(4, 'Limpieza de oídos', '2026-03-04', '15:45:00', 4, 4),
(5, 'Radiografía de control', '2026-03-05', '12:00:00', 5, 5),
(6, 'Oftalmología veterinaria', '2026-03-06', '16:20:00', 6, 6),
(7, 'Control post-operatorio', '2026-03-07', '10:00:00', 7, 7),
(8, 'Chequeo cardiológico', '2026-03-08', '11:30:00', 8, 8),
(9, 'Consulta por alergia cutánea', '2026-03-09', '14:15:00', 9, 9),
(10, 'Evaluación odontológica', '2026-03-10', '09:45:00', 10, 10),
(11, 'Vacunación anual', '2026-03-11', '13:00:00', 11, 11),
(12, 'Asesoría nutricional', '2026-03-12', '16:00:00', 12, 12),
(13, 'Ecografía abdominal', '2026-03-13', '10:10:00', 13, 13),
(14, 'Atención de urgencia por traumatismo', '2026-03-14', '11:50:00', 14, 14),
(15, 'Cirugía menor', '2026-03-15', '15:30:00', 15, 15),
(16, 'Control de rutina', '2026-03-16', '09:20:00', 16, 16),
(17, 'Examen neurológico', '2026-03-17', '12:40:00', 17, 17),
(18, 'Sesión de fisioterapia', '2026-03-18', '14:00:00', 18, 18),
(19, 'Tratamiento por infección respiratoria', '2026-03-19', '10:50:00', 19, 19),
(20, 'Control endocrinológico', '2026-03-20', '16:10:00', 20, 20);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dueño`
--

CREATE TABLE `dueño` (
  `id_dueño` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `cedula` int(11) NOT NULL,
  `telefono` int(11) NOT NULL,
  `email` varchar(45) NOT NULL,
  `direccion` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `dueño`
--

INSERT INTO `dueño` (`id_dueño`, `nombre`, `cedula`, `telefono`, `email`, `direccion`) VALUES
(1, 'Juan Pérez', 4521458, 99123456, 'juan.perez@example.com', ''),
(2, 'María Rodríguez', 3892341, 98654321, 'maria.rodriguez@example.com', ''),
(3, 'Carlos Gómez', 5123456, 91112233, 'carlos.gomez@example.com', ''),
(4, 'Ana', 4123890, 92334455, 'ana.lopez@example.com', ''),
(5, 'Pedro Martínez', 2987654, 99556677, 'pedro.martinez@example.com', ''),
(6, 'Laura Fernández', 4789123, 98778899, 'laura.fernandez@example.com', ''),
(7, 'Jorge Díaz', 3567890, 91223344, 'jorge.diaz@example.com', ''),
(8, 'Carmen Sánchez', 5234567, 92445566, 'carmen.sanchez@example.com', ''),
(9, 'Miguel Torres', 4312345, 99998877, 'miguel.torres@example.com', ''),
(10, 'Sofía Ramírez', 3987123, 98112233, 'sofia.ramirez@example.com', ''),
(11, 'Diego Morales', 4654321, 91334455, 'diego.morales@example.com', ''),
(12, 'Valentina Castro', 3789012, 92556677, 'valentina.castro@example.com', ''),
(13, 'Mateo Herrera', 5012348, 99223344, 'mateo.herrera@example.com', ''),
(14, 'Lucía Rojas', 4432109, 98445566, 'lucia.rojas@example.com', ''),
(15, 'Andrés Silva', 3654321, 91667788, 'andres.silva@example.com', ''),
(16, 'Camila Sosa', 4890123, 92889900, 'camila.sosa@example.com', ''),
(17, 'Gabriel Benítez', 3412345, 99334455, 'gabriel.benitez@example.com', ''),
(18, 'Paula Navarro', 5198765, 98556677, 'paula.navarro@example.com', ''),
(19, 'Ignacio Acosta', 4231425, 91778899, 'ignacio.acosta@example.com', ''),
(20, 'Florencia Méndez', 3912345, 92001122, 'florencia.mendez@example.com', ''),
(21, 'jghfjgfjhf', 456456464, 6464654, 'fhdhsshs', 'fhfghfh'),
(22, 'fdasfdafas', 424234234, 131312313, 'wefefffaa', 'sgagsdgag');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mascota`
--

CREATE TABLE `mascota` (
  `id_mascota` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `edad` int(11) NOT NULL,
  `especie` varchar(45) NOT NULL,
  `raza` varchar(45) NOT NULL,
  `genero` varchar(45) NOT NULL,
  `peso` int(11) NOT NULL,
  `id_dueño` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `mascota`
--

INSERT INTO `mascota` (`id_mascota`, `nombre`, `edad`, `especie`, `raza`, `genero`, `peso`, `id_dueño`) VALUES
(1, 'Max', 3, 'Perro', 'Labrador', 'Macho', 26, 1),
(2, 'Luna', 2, 'Gato', 'Siamés', 'Hembra', 4, 2),
(3, 'Rocky', 5, 'Perro', 'Bulldog', 'Macho', 18, 3),
(4, 'Mila', 1, 'Gato', 'Persa', 'Hembra', 4, 4),
(5, 'Simba', 4, 'Perro', 'Golden Retriever', 'Macho', 30, 5),
(6, 'Nala', 3, 'Gato', 'Mestizo', 'Hembra', 4, 6),
(7, 'Thor', 6, 'Perro', 'Pastor Alemán', 'Macho', 35, 7),
(8, 'Kira', 2, 'Perro', 'Beagle', 'Hembra', 11, 8),
(10, 'Lola', 4, 'Gato', 'Angora', 'Hembra', 5, 10),
(11, 'Zeus', 7, 'Perro', 'Pitbull', 'Macho', 28, 1),
(12, 'Mia', 2, 'Gato', 'Maine Coon', 'Hembra', 6, 2),
(13, 'Bruno', 5, 'Perro', 'Boxer', 'Macho', 32, 3),
(15, 'Toby', 4, 'Perro', 'Poodle', 'Macho', 8, 5),
(16, 'Lina', 1, 'Ave', 'Canario', 'Hembra', 0, 6),
(17, 'Pancho', 8, 'Perro', 'Schnauzer', 'Macho', 9, 7),
(18, 'Princesa', 6, 'Gato', 'Mestizo', 'Hembra', 5, 8),
(19, 'Jack', 3, 'Perro', 'Cocker Spaniel', 'Macho', 12, 9),
(20, 'Daisy', 2, 'Perro', 'Chihuahua', 'Hembra', 3, 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vacuna`
--

CREATE TABLE `vacuna` (
  `id_vacuna` int(11) NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `vencimiento` date NOT NULL,
  `id_mascota` int(11) NOT NULL,
  `id_consulta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `vacuna`
--

INSERT INTO `vacuna` (`id_vacuna`, `tipo`, `fecha`, `hora`, `vencimiento`, `id_mascota`, `id_consulta`) VALUES
(1, 'Rabia', '2026-03-01', '10:30:00', '2027-03-01', 1, 1),
(2, 'Séxtuple', '2026-03-02', '11:15:00', '2027-03-02', 2, 2),
(3, 'Triple Felina', '2026-03-03', '09:00:00', '2027-03-03', 3, 3),
(4, 'Antirrábica', '2026-03-04', '15:45:00', '2027-03-04', 4, 4),
(5, 'Parvovirus', '2026-03-05', '12:00:00', '2027-03-05', 5, 5),
(6, 'Leucemia Felina', '2026-03-06', '16:20:00', '2027-03-06', 6, 6),
(7, 'Tos de las Perreras', '2026-03-07', '10:00:00', '2027-03-07', 7, 7),
(8, 'Quíntuple', '2026-03-08', '11:30:00', '2027-03-08', 8, 8),
(9, 'Rabia', '2026-03-09', '14:15:00', '2027-03-09', 9, 9),
(10, 'Triple Felina', '2026-03-10', '09:45:00', '2027-03-10', 10, 10),
(11, 'Séxtuple', '2026-03-11', '13:00:00', '2027-03-11', 11, 11),
(12, 'Antirrábica', '2026-03-12', '16:00:00', '2027-03-12', 12, 12),
(13, 'Parvovirus', '2026-03-13', '10:10:00', '2027-03-13', 13, 13),
(14, 'Leucemia Felina', '2026-03-14', '11:50:00', '2027-03-14', 14, 14),
(15, 'Tos de las Perreras', '2026-03-15', '15:30:00', '2027-03-15', 15, 15),
(16, 'Quíntuple', '2026-03-16', '09:20:00', '2027-03-16', 16, 16),
(17, 'Rabia', '2026-03-17', '12:40:00', '2027-03-17', 17, 17),
(18, 'Triple Felina', '2026-03-18', '14:00:00', '2027-03-18', 18, 18),
(19, 'Séxtuple', '2026-03-19', '10:50:00', '2027-03-19', 19, 19),
(20, 'Antirrábica', '2026-03-20', '16:10:00', '2027-03-20', 20, 20);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vacunas`
--

CREATE TABLE `vacunas` (
  `id` int(11) NOT NULL,
  `tipo` varchar(100) NOT NULL,
  `fecha` varchar(30) DEFAULT NULL,
  `hora` varchar(20) DEFAULT NULL,
  `vencimiento` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `veterinario`
--

CREATE TABLE `veterinario` (
  `id_veterinario` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `especialidad` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `veterinario`
--

INSERT INTO `veterinario` (`id_veterinario`, `nombre`, `password`, `especialidad`) VALUES
(1, 'Dr. Carlos Mendoza', 'pass1234', 'Cirugía General'),
(2, 'Dra. María Gómez', 'secureKey99', 'Dermatología'),
(3, 'Dr. Alejandro Ruiz', 'vet2026#', 'Cardiología'),
(4, 'Dra. Sofía Torres', 'sofiaT_2025', 'Medicina Felina'),
(5, 'Dr. Javier Vargas', 'javierV77', 'Traumatología y Ortopedia'),
(6, 'Dra. Lucía Fernández', 'luciaF_vet', 'Oftalmología'),
(7, 'Dr. Ricardo Morales', 'ricardoM_9', 'Neurología'),
(8, 'Dra. Elena Castro', 'elenaPass!', 'Oncología'),
(9, 'Dr. Fernando Silva', 'fernandoS_vet', 'Animales Exóticos'),
(10, 'Dra. Gabriela Ríos', 'gabyRios2026', 'Etología Clínica'),
(11, 'Dr. Diego Alonso', 'diegoA_pass', 'Medicina Preventiva'),
(12, 'Dra. Valentina Ortiz', 'valenOrtiz#', 'Nutrición Animal'),
(13, 'Dr. Mateo Herrera', 'mateoH_vet', 'Diagnóstico por Imagen'),
(14, 'Dra. Camila Rojas', 'camilaR_21', 'Urgencias y Cuidados Intensivos'),
(15, 'Dr. Andrés Castro', 'andresC_pass', 'Cirugía de Tejidos Blandos'),
(16, 'Dra. Paula Navarro', 'paulaN_vet', 'Odontología Veterinaria'),
(17, 'Dr. Ignacio Benítez', 'nachoB2026', 'Medicina Interna'),
(18, 'Dra. Florencia Sosa', 'florSosa!', 'Fisioterapia y Rehabilitación'),
(19, 'Dr. Esteban Romero', 'estebanR_vet', 'Infectología'),
(20, 'Dra. Martina Paredes', 'martinaP99', 'Endocrinología');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `veterinarios`
--

CREATE TABLE `veterinarios` (
  `id_veterinario` int(11) NOT NULL,
  `nombre` varchar(150) NOT NULL,
  `password` varchar(255) NOT NULL,
  `especialidad` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `consulta`
--
ALTER TABLE `consulta`
  ADD PRIMARY KEY (`id_consulta`);

--
-- Indices de la tabla `dueño`
--
ALTER TABLE `dueño`
  ADD PRIMARY KEY (`id_dueño`);

--
-- Indices de la tabla `mascota`
--
ALTER TABLE `mascota`
  ADD PRIMARY KEY (`id_mascota`);

--
-- Indices de la tabla `vacuna`
--
ALTER TABLE `vacuna`
  ADD PRIMARY KEY (`id_vacuna`);

--
-- Indices de la tabla `vacunas`
--
ALTER TABLE `vacunas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `veterinario`
--
ALTER TABLE `veterinario`
  ADD PRIMARY KEY (`id_veterinario`);

--
-- Indices de la tabla `veterinarios`
--
ALTER TABLE `veterinarios`
  ADD PRIMARY KEY (`id_veterinario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `consulta`
--
ALTER TABLE `consulta`
  MODIFY `id_consulta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `dueño`
--
ALTER TABLE `dueño`
  MODIFY `id_dueño` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT de la tabla `mascota`
--
ALTER TABLE `mascota`
  MODIFY `id_mascota` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `vacuna`
--
ALTER TABLE `vacuna`
  MODIFY `id_vacuna` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `vacunas`
--
ALTER TABLE `vacunas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `veterinario`
--
ALTER TABLE `veterinario`
  MODIFY `id_veterinario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `veterinarios`
--
ALTER TABLE `veterinarios`
  MODIFY `id_veterinario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
