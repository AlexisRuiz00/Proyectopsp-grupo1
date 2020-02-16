-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 11-02-2020 a las 23:47:02
-- Versión del servidor: 5.5.24-log
-- Versión de PHP: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `atcs`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administrador`
--

CREATE TABLE IF NOT EXISTS `administrador` (
  `name` varchar(150) NOT NULL,
  `apellidos` varchar(150) NOT NULL,
  `mail` varchar(150) NOT NULL,
  `phone` int(10) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `administrador`
--

INSERT INTO `administrador` (`name`, `apellidos`, `mail`, `phone`, `username`, `password`, `role`) VALUES
('Juan', 'Martinez', 'juanMartinez@hotmail.com', 676755210, 'juan', 'juan', 'IncidenceAdmin');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `incidencia`
--

CREATE TABLE IF NOT EXISTS `incidencia` (
  `id` bigint(50) NOT NULL,
  `incidenceAdmin` varchar(150) NOT NULL,
  `mail` varchar(150) NOT NULL,
  `type` varchar(50) NOT NULL,
  `body` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `incidencia`
--

INSERT INTO `incidencia` (`id`, `incidenceAdmin`, `mail`, `type`, `body`) VALUES
(-485092640, 'juan', 'null', 'new', 'as'),
(-484873319, 'juan', 'fa', 'new', 'Prueba23\n<------------------ 26/27/2020 19:27:17 -------------------->\nProbando respuesta\n<------------------ 26/07/2020 21:07:52 -------------------->\n1\n<------------------ 26/07/2020 21:07:53 -------------------->\n12'),
(-477057025, 'juan', 'fa', 'new', 'Prueba24\n<------------------ 26/35/2020 21:35:12 -------------------->\n1\n<------------------ 26/35/2020 21:35:25 -------------------->\n2\n<------------------ 26/35/2020 21:35:39 -------------------->\n44');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
