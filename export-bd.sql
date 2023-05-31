-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 31-05-2023 a las 16:46:40
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `pfc`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `arbitro`
--

CREATE TABLE `arbitro` (
  `id` int(11) NOT NULL,
  `nombre_completo` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `arbitro`
--

INSERT INTO `arbitro` (`id`, `nombre_completo`) VALUES
(1, 'De Burgos Bengoetxea'),
(2, 'Carlos del Cerro Grande'),
(3, 'Javier Iglesias Villanueva'),
(4, 'César Soto Grado'),
(5, 'José María Sánchez Martínez'),
(6, 'Jorge Figueroa Vázquez'),
(7, 'Mario Melero López'),
(8, 'Jesús Gil Manzano'),
(9, 'Pablo González Fuertes'),
(10, 'José Luis Munuera Montero'),
(11, 'Alejandro Muñiz Ruiz'),
(12, 'Mateu Lahoz'),
(13, 'Alejandro Hernández Hernández'),
(14, 'Juan Martínez Munuera'),
(15, 'Chema Enríquez Negreira');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asistencia`
--

CREATE TABLE `asistencia` (
  `id` int(11) NOT NULL,
  `minuto` int(11) DEFAULT NULL,
  `id_jugador` int(11) DEFAULT NULL,
  `id_partido` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `asistencia`
--

INSERT INTO `asistencia` (`id`, `minuto`, `id_jugador`, `id_partido`) VALUES
(1, 70, 286, 10),
(2, 70, 12, 10),
(3, 70, 100, 10),
(4, 70, 286, 10),
(5, 0, 207, 106),
(6, 0, 58, 107),
(7, 0, 279, 108),
(8, 0, 365, 109),
(9, 0, 154, 110),
(10, 0, 175, 112),
(11, 0, 78, 114),
(12, 0, 57, 116),
(13, 0, 213, 116),
(14, 0, 346, 117),
(15, 0, 5, 118),
(16, 0, 247, 118),
(17, 0, 202, 119);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clasificacion`
--

CREATE TABLE `clasificacion` (
  `id_liga` int(11) NOT NULL,
  `id_equipo` int(11) NOT NULL,
  `puntos` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `clasificacion`
--

INSERT INTO `clasificacion` (`id_liga`, `id_equipo`, `puntos`) VALUES
(1, 1, 2),
(1, 2, 3),
(1, 3, 1),
(1, 4, 3),
(1, 5, 1),
(1, 6, 0),
(1, 7, 3),
(1, 8, 1),
(1, 9, 5),
(1, 10, 0),
(1, 11, 4),
(1, 12, 1),
(1, 13, 0),
(1, 14, 0),
(1, 15, 3),
(1, 16, 3),
(1, 17, 0),
(1, 18, 1),
(1, 19, 0),
(1, 20, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentario`
--

CREATE TABLE `comentario` (
  `id` int(11) NOT NULL,
  `comentario` varchar(300) DEFAULT NULL,
  `fecha` datetime DEFAULT current_timestamp(),
  `id_usuario` int(11) DEFAULT NULL,
  `id_noticia` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `comentario`
--

INSERT INTO `comentario` (`id`, `comentario`, `fecha`, `id_usuario`, `id_noticia`) VALUES
(4, 'fatal..', '2023-05-03 00:00:00', 15, 1),
(5, 'cristiano perro', '2023-05-03 00:00:00', 29, 6),
(6, 'viva cordoba', '2023-05-03 00:00:00', 29, 1),
(7, 'y viva cantabria', '2023-05-03 00:00:00', 29, 1),
(8, 'aupa el Zaragoza', '2023-05-03 00:00:00', 15, 1),
(9, 'comentario de ejemplo', '2023-05-03 00:00:00', 15, 1),
(10, 'no se qué más poner', '2023-05-03 00:00:00', 15, 1),
(11, 'tercer comentario de ejemplo', '2023-05-03 00:00:00', 15, 1),
(12, 'comentario de ejemplo comentario de ejemplo comentario de ejemplo ', '2023-05-03 00:00:00', 15, 2),
(13, 'comentario de ejemplo', '2023-05-03 00:00:00', 15, 2),
(14, 'que desgracia', '2023-05-09 00:00:00', 15, 6),
(15, 'siempre te recordaremos', '2023-05-24 00:00:00', 31, 6),
(16, 'viva el betis', '2023-05-24 00:00:00', 31, 2),
(17, 'comentario de ejemplo', '2023-05-24 00:00:00', 31, 2),
(18, 'coment', '2023-05-24 00:00:00', 31, 1),
(19, 'aupa ferrol', '2023-05-24 00:00:00', 31, 2),
(20, 'Mu bonico', '2023-05-24 14:08:25', 26, 2),
(21, 'último comentario', '2023-05-24 14:29:18', 31, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipo`
--

CREATE TABLE `equipo` (
  `id` int(11) NOT NULL,
  `nombre` varchar(60) COLLATE latin1_spanish_ci DEFAULT NULL,
  `estadio` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  `presi` varchar(60) COLLATE latin1_spanish_ci DEFAULT NULL,
  `descripcion` text COLLATE latin1_spanish_ci DEFAULT NULL,
  `escudo` varchar(40) COLLATE latin1_spanish_ci DEFAULT NULL,
  `nombre_abrev` varchar(40) COLLATE latin1_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `equipo`
--

INSERT INTO `equipo` (`id`, `nombre`, `estadio`, `presi`, `descripcion`, `escudo`, `nombre_abrev`) VALUES
(1, 'Agrupacion Deportiva Alcorcon', 'Estadio Santo Domingo', 'Ignacio Legido Castiella', 'La Agrupación Deportiva Alcorcón, S. A. D. es un club de fútbol español situado en la ciudad de Alcorcón, en la Comunidad de Madrid. Fue fundado en 1971 y juega en la Primera Federación, tras descender desde la Segunda División de España en la temporada 2021/22', 'escudo_alcorcon.png', 'AD Alcorcon'),
(2, 'Real CLub deportivo de La Coruña', 'Estadio Riazor', 'Antonio Couceiro', 'El Real Club Deportivo de La Coruña, S. A. D., más conocido como Deportivo o simplemente Dépor, es un club de fútbol español de la ciudad de La Coruña, en Galicia, que compite en Primera Federación. Fue fundado el 8 de diciembre de 1906.6? Disputa sus partidos como local en el Estadio de Riazor, que dispone de una capacidad de 32.490 espectadores, siendo el estadio con mayor aforo de Galicia.\n    El Deportivo es uno de los nueve clubes que se han proclamado campeones del Campeonato Nacional de Liga de Primera División, título conquistado en la última temporada del siglo xx, la 1999-2000. Ha logrado además cinco subcampeonatos y ocupa el duodécimo puesto histórico. El club ha ganado en tres ocasiones la Copa del Rey (1912, 1995 y 2002), y se ha proclamado a su vez supercampeón de España en 1995, 2000 y 2002. A nivel continental, ha disputado cinco ediciones de la Copa de la UEFA, una de la Recopa de Europa y cinco de la Liga de Campeones, consecutivamente entre 2000 y 2005, alcanzando las semifinales en 2004 y siendo el sexto club español con más participaciones en la competición y el cuarto con más participaciones consecutivas.', 'escudo_depor.png', 'Deportivo'),
(3, 'Real Madrid Castilla club de futbol', 'Estadio Alfredo Di Stéfano', 'Florentino Perez', 'El Real Madrid Castilla Club de Fútbol, también conocido como Real Madrid Castilla, o simplemente Castilla, es el equipo filial de fútbol del Real Madrid Club de Fútbol. Fue adscrito como club filial oficial el 21 de julio de 1972 bajo el nombre de Castilla Club de Fútbol,1? aunque existía desde 1947 con la antigua denominación de Agrupación Deportiva Plus Ultra bajo la que ya realizaba las funciones de filial En ocasiones se le suele referir erróneamente por su antiguo apelativo de Real Madrid B, debido al carácter de «segundo equipo» de la entidad, y denominación habitual en los equipos filiales de España?', 'escudo_rmadrid.png', 'Castilla'),
(4, 'Cordoba Club de Futbol', 'Estadio Nuevo Arcangel', 'Abdulla Al-Zain', 'El Córdoba Club de Fútbol es un club de fútbol español organizado como sociedad anónima deportiva. Tiene su sede en la ciudad de Córdoba, en la comunidad autónoma de Andalucía, España, y actualmente juega en Primera División RFEF, tercera categoría del fútbol español. Fue fundado el 6 de agosto de 1954, producto de la fusión de las entidades futbolísticas del C. D. San Álvaro y del R. C. D. Córdoba. Disputa sus encuentros como local en el estadio del Nuevo Arcángel.La entidad es identificada por sus colores blanco y verde, lo cual le permite a sus aficionados recibir el apelativo de blanquiverdes.\nEn la actualidad, el club ocupa el puesto 40.º en la clasificación histórica de la LFP y el 13.º de la Segunda División después de haber disputado un total de 9 temporadas en Primera División (la última en la 2014-15) y 35 en la categoría de plata.', 'escudo_cordoba.png', 'Cordoba'),
(5, 'Racing Club de Ferrol', 'Estadio de A Malata', 'José María Criado', 'El Racing Club de Ferrol, S.A.D. es un club de fútbol español de la ciudad de Ferrol, en la provincia de La Coruña, Galicia. El equipo disputa sus partidos como local en el Estadio de A Malata, que tiene capacidad para 12 043 espectadores. El verde es el color tradicional del uniforme del club. Actualmente se desempeña en el Grupo I de la Primera Federación, tercera categoría del fútbol español.\nEl Racing fue fundado (según registros oficiales, pues su existencia no documentada es anterior) el 5 de octubre de 1919 a partir de la unión de varios clubes de la ciudad. A pesar de no haber jugado nunca en Primera División cuenta entre sus mayores logros varios campeonatos regionales gallegos y la disputa de una final de la Copa del Generalísimo en 1939. Es el equipo que más temporadas (34) ha disputado en la Segunda División sin haber logrado nunca ascender a Primera.', 'escudo_ferrol.png', 'Racing Ferrol'),
(6, 'Real Club Celta de Vigo B', 'Estadio de Balaidos', 'Carlos Mouriño', 'El Real Club Celta de Vigo B, conocido simplemente como Celta B, es un equipo de fútbol gallego, filial del Real Club Celta de Vigo. Fue fundado en 1927 como club independiente con el nombre Turista Sport Club. En 1989 se convirtió en filial del Celta, tomando la denominación Celta Turista. En 1995 desapareció como club y se convirtió en equipo dependiente del Celta, quedando plenamente integrado en su estructura.1? Actualmente disputa la Primera Federación, tercera categoría del fútbol español.', 'escudo_celta.png', 'Celta B'),
(7, 'Linares Deportivo', 'Estadio municipal de Linarejos', 'Jesús Medina', 'El Linares Deportivo es un club de fútbol de la ciudad de Linares, en la provincia de Jaén (Andalucía, España). El club se fundó el 4 de agosto de 2009 tras la desaparición de su antecesor, el C. D. Linares, y actualmente milita en el grupo I de la Primera Federación, tercera categoría del fútbol español.', 'escudo_linares.png', 'Linares'),
(8, 'Cultural y Deportiva Leonesa', 'Estadio municipal Reino de Leon', 'Mohammed Khalifa Al Suwaidi', 'La Cultural y Deportiva Leonesa, S. A. D., más conocida como Cultural Leonesa, es un club de fútbol español de la ciudad de León, que juega en la Primera Federación. Disputa sus partidos como local en el Estadio Reino de León con capacidad para 13 451 espectadores. Fue fundada el 5 de Agosto de 1923.', 'escudo_cultural.png', 'Cultural'),
(9, 'Asociacion Deportiva Merida', 'Estadio Romano Jose Fouto', 'Mark Heffernan', 'La Asociación Deportiva Mérida, S.A.D. es un club de fútbol de España de la ciudad de Mérida, capital de Extremadura. En la actualidad compite en la Primera Federación.Fue fundada en 2013 y adquirió mediante subasta judicial los derechos federativos del liquidado Mérida UD ese mismo año, el cuál fue filial del histórico CP Mérida. El 4 de agosto de 2016 se constituye como sociedad anónima deportiva.', 'escudo_merida.png', 'Merida'),
(10, 'Club de Futbol Rayo Majadahonda', 'Estadio Cerro del Espino', 'Enrique Vedia', 'El Club de Fútbol Rayo Majadahonda S.A.D. es un club de fútbol español ubicado en Majadahonda, Comunidad de Madrid. Fue fundado en 1976 y compite en la Primera Federación', 'escudo_rayomajadahonda.png', 'Rayo Majadahonda'),
(11, 'Union Deportiva San Sebastian de los Reyes', 'Estadio Nuevo Matapiñoneras', 'Javier Gómez', 'La Unión Deportiva San Sebastián de los Reyes, también conocida popularmente como Sanse, es un club de fútbol español, situado en la localidad de San Sebastián de los Reyes en la Comunidad de Madrid. Fue fundado en 1971 como Grupo Empresa San Sebastián de los Reyes Educación y Descanso,2? y juega actualmente en la Primera Federación.', 'escudo_ssreyes.png', 'SS Reyes'),
(12, 'Real Balompedica Linense', 'Estadio municipal de la Linea', ' Raffaele Pandalone', 'La Real Balompédica Linense, S. A. D. es un club de fútbol español de la ciudad de La Línea de la Concepción, Cádiz, Andalucía, España. Actualmente milita en la Primera Federación.La entidad fue fundada el 4 de enero de 1912, aunque empezó a competir de manera oficial en el año 1921. Un año después, en 1922, el que fuera secretario del club, Cristóbal Becerra, solicitó el título de «Real», que le fue otorgado ese mismo año por el Rey Alfonso XIII.', 'escudo_linense.png', 'Linense'),
(13, 'Algeciras Club de Futbol', 'Estadio nuevo mirador', 'Nicolás Andión Martínez', 'El Algeciras Club de Fútbol es un club de fútbol de la ciudad de Algeciras (España) que actualmente milita en el grupo I de la Primera Federación, tercera categoría del fútbol español. El club, decano del Campo de Gibraltar, fue fundado en 1909 tal y como viene publicado en el Heraldo de Madrid. Los tempranos orígenes del fútbol en la ciudad de Algeciras están relacionados con la presencia en la localidad de numerosos obreros británicos pertenecientes a la Algeciras and Gibraltar Railway Company que realizaban las obras para la línea de ferrocarril Algeciras-Bobadilla. Desde finales del siglo xix los equipos locales jugaban torneos y partidos amistosos con otros clubes comarcales y gibraltareños.', 'escudo_algeciras.png', 'Algeciras'),
(14, 'San Fernando Club deportivo Isleño', 'Estadio Bahia Sur', 'Louis Kinziger', 'El San Fernando Club Deportivo es un club de fútbol español de la ciudad de San Fernando que milita en el grupo I de la Primera Federación, tercera categoría del fútbol español. El San Fernando Club Deportivo fue fundado en el año 2009, siendo el heredero deportivo del desaparecido Club Deportivo San Fernando, el cual fue fundado en 1940. El San Fernando Club Deportivo Isleño juega sus partidos en el Estadio Iberoamericano 2010, que cuenta con una capacidad para 8.021 espectadores.', 'escudo_sfernando.png', 'San Fernando'),
(15, 'Club deportivo Badajoz', 'Estadio nuevo vivero', 'Luis Díaz-Ambrona', 'El Club Deportivo Badajoz, S. A. D. es un club de fútbol español de la ciudad de Badajoz, capital de la provincia homónima situada en la Comunidad Autónoma de Extremadura (España). Juega en la Primera Federación, tercera categoría del fútbol español. Se fundó en el verano del año 20121? bajo la denominación de Club Deportivo Badajoz 1905 (club con número federativo 16602?), tras la liquidación y desaparición del Club Deportivo Badajoz original El Club Deportivo Badajoz 1905 así mismo heredó la adhesión social y el sentimiento característico de la centenaria entidad y fue una parte de su masa social encabezada por miembros de la gestora «Ambición Blanquinegra» quienes acometieron la fundación del nuevo club (con una equipación, denominación y escudo idéntico, pero añadiendo 1905 en el balón del escudo y sustituyendo los dos leones por uno solo)', 'escudo_badajoz.png', 'Badajoz'),
(16, 'Unionistas de Salamanca Club de Futbol', 'Estadio Municipal Reina Sofía', 'Miguel Ángel Sandoval Herrero', 'Unionistas de Salamanca Club de Fútbol es un club de fútbol español de la ciudad de Salamanca, en la comunidad autónoma de Castilla y León, España. Fue fundado el 26 de agosto de 20132? por varios miembros de la Plataforma de Aficionados Unionistas y otros seguidores y aficionados de la Unión Deportiva Salamanca como homenaje a dicho equipo,3? después de que este desapareciese el 18 de junio anterior. Milita en la Primera Federación, la tercera categoría del fútbol español, jugando sus partidos como local en el Estadio Municipal Reina Sofía.\nEl club está gestionado democráticamente por sus propios aficionados a través de la máxima \'un socio, un voto\',? siguiendo el modelo de otros equipos como el Football Club United of Manchester o el Association Football Club Wimbledon, y se incluye dentro del movimiento de fútbol popular español.', 'escudo_unionistas.png', 'Unionistas'),
(17, 'Club de futbol Fuenlabrada', 'Estadio Fernando Torres', 'Jonathan Praena', 'El Club de Fútbol Fuenlabrada es un club de fútbol ubicado en la ciudad de Fuenlabrada, Comunidad de Madrid (España). Fue fundado en 1975 y juega en la Primera Federación. El actual equipo se fundó en 1975 gracias a la fusión del Club de Fútbol San Esteban, fundado en 1971 y la Agrupación Deportiva Fuenlabrada creada en 1974. ', 'escudo_fuenla.png', 'Fuenlabrada'),
(18, 'Agrupacion Deportiva Ceuta Futbol Club ', 'Estadio Municipal Alfonso Murube', 'Luhay Hamido', 'La Agrupación Deportiva Ceuta Fútbol Club es un club de fútbol español, de la ciudad autónoma de Ceuta. Fue fundado en 1956 con la denominación de Club Atlético de Ceuta por la fusión de la S. D. Ceuta y el C. Atlético de Tetuán. En 1956, tras la independencia de Marruecos, los jugadores y directivos del C. Atlético de Tetuán (club que había sido fundado durante el Protectorado español de Marruecos) se trasladaron a la vecina ciudad de Ceuta, donde se fusionaron con la S.D. Ceuta para fundar el Club Atlético de Ceuta', 'escudo_ceuta.png', 'AD Ceuta'),
(19, 'Talavera Club de Futbol', 'Estadio El Prado', 'Josué Blázquez', 'El Talavera Club de Fútbol fue un equipo de fútbol español, de la ciudad de Talavera de la Reina (Toledo). Fue fundado en 1948 y, a cinco días de comenzar la temporada 2010/2011, debido al nefasto estado económico del club al que le llevó el entonces presidente Tino Muñoz, los socios en asamblea extraordinaria decidieron renunciar a participar en Tercera División y a cualquier competición nacional.\nPosteriormente, fue formado otro club bajo la denominación de Club de Fútbol Talavera de la Reina, avalado por la gran mayoría de los socios del club desaparecido. En la temporada 2015/16 militó en la Segunda División B, dentro del grupo II.', 'escudo_talavera.png', 'Talavera'),
(20, 'Pontevedra Club de Futbol', 'Estadio Municipal de Pasaró', 'Lupe Murillo', 'El Pontevedra Club de Fútbol es un club de fútbol español de la ciudad de Pontevedra, en Galicia. Milita en la Primera Federación, tercera categoría del fútbol nacional. Fue fundado el 16 de octubre de 1941 tras la fusión del Eiriña Football Club y el Alfonso Club de Fútbol, los dos clubes más importantes de la ciudad. Su primer equipo disputa sus partidos como local en el Estadio de Pasarón, con capacidad para 10 500 espectadores.\nEs el tercer equipo gallego con más participaciones en Primera División, con un total de 6 temporadas, 5 de ellas consecutivas, disputadas entre 1963 y 1970. En las últimas décadas militó la mayoría de temporadas en la desaparecida Segunda División B, siendo el equipo con más participaciones en la categoría, con 36, junto con Barakaldo CF y Cultural Leonesa.', 'escudo_pontevedra.png', 'Pontevedra');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gol`
--

CREATE TABLE `gol` (
  `id` int(11) NOT NULL,
  `minuto` int(11) DEFAULT NULL,
  `id_jugador` int(11) DEFAULT NULL,
  `id_partido` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `gol`
--

INSERT INTO `gol` (`id`, `minuto`, `id_jugador`, `id_partido`) VALUES
(1, 15, 20, 1),
(2, 18, 20, 1),
(3, 37, 205, 1),
(4, 37, 287, 10),
(5, 37, 286, 10),
(6, 0, 130, 102),
(7, 0, 140, 102),
(8, 0, 141, 102),
(9, 0, 151, 103),
(10, 0, 152, 103),
(11, 0, 158, 104),
(12, 1, 167, 104),
(13, 0, 295, 105),
(14, 0, 200, 106),
(15, 0, 54, 107),
(16, 0, 272, 108),
(17, 0, 380, 109),
(18, 0, 152, 110),
(19, 0, 177, 112),
(20, 0, 83, 114),
(21, 0, 66, 116),
(22, 0, 215, 116),
(23, 0, 355, 117),
(24, 0, 4, 118),
(25, 0, 249, 118),
(26, 0, 208, 119),
(27, 16, 281, 115);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `jugador`
--

CREATE TABLE `jugador` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `apodo` varchar(30) COLLATE latin1_spanish_ci DEFAULT NULL,
  `peso` int(11) DEFAULT NULL,
  `altura` double DEFAULT NULL,
  `foto` varchar(40) COLLATE latin1_spanish_ci DEFAULT NULL,
  `fondo` varchar(40) COLLATE latin1_spanish_ci DEFAULT NULL,
  `dorsal` int(11) DEFAULT NULL,
  `id_equipo` int(11) DEFAULT NULL,
  `posicion` varchar(40) COLLATE latin1_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `jugador`
--

INSERT INTO `jugador` (`id`, `nombre`, `apodo`, `peso`, `altura`, `foto`, `fondo`, `dorsal`, `id_equipo`, `posicion`) VALUES
(1, 'Jesus Ruiz', 'Jesus', 77, 1.85, 'cara_alcorcon_1.jpg', 'fondo_alcorcon_1.jpg', 1, 1, 'Portero'),
(2, 'Andres Prieto', 'Andres Prieto', 92, 1.94, 'cara_alcorcon_13.jpg', 'fondo_alcorcon_13.jpg', 13, 1, 'Portero'),
(3, 'Javi Castro', 'Castro', 75, 1.83, 'cara_alcorcon_2.jpg', 'fondo_alcorcon_2.jpg', 2, 1, 'Defensa'),
(4, 'David Morrillas', 'Morrillas', 75, 1.86, 'cara_alcorcon_3.jpg', 'fondo_alcorcon_3.jpg', 3, 1, 'Defensa'),
(5, 'Óscar Rivas', 'Óscar Rivas', 80, 1.9, 'cara_alcorcon_4.jpg', 'fondo_alcorcon_4.jpg', 4, 1, 'Defensa'),
(6, 'Jean-Sylvain Babin', 'Babin', 84, 1.82, 'cara_alcorcon_6.jpg', 'fondo_alcorcon_6.jpg', 6, 1, 'Defensa'),
(7, 'Pablo Garcia', 'Pablo Garcia', 68, 1.76, 'cara_alcorcon_18.jpg', 'fondo_alcorcon_18.jpg', 18, 1, 'Defensa'),
(8, 'Iago Lopez', 'Iago Lopez', 70, 1.77, 'cara_alcorcon_20.jpg', 'fondo_alcorcon_20.jpg', 20, 1, 'Defensa'),
(9, 'Javi Jiménez', 'Javi Jiménez', 74, 1.82, 'cara_alcorcon_24.jpg', 'fondo_alcorcon_24.jpg', 24, 1, 'Defensa'),
(10, 'Pedro Mosquera', 'Mosquera', 77, 1.83, 'cara_alcorcon_5.jpg', 'fondo_alcorcon_5.jpg', 5, 1, 'Centrocampista'),
(11, 'Álvaro Bustos', 'Álvaro Bustos', 72, 1.76, 'cara_alcorcon_7.jpg', 'fondo_alcorcon_7.jpg', 7, 1, 'Centrocampista'),
(12, 'Antonio Moyano', 'Moyano', 66, 1.73, 'cara_alcorcon_8.jpg', 'fondo_alcorcon_8.jpg', 8, 1, 'Centrocampista'),
(13, 'Juanma Bravo', 'Juanma Bravo', 80, 1.88, 'cara_alcorcon_10.jpg', 'fondo_alcorcon_10.jpg', 10, 1, 'Centrocampista'),
(14, 'Alex Lopez', 'Alex Lopez', 70, 1.76, 'cara_alcorcon_14.jpg', 'fondo_alcorcon_14.jpg', 14, 1, 'Centrocampista'),
(15, 'Javi Lara', 'Javi Lara', 71, 1.79, 'cara_alcorcon_15.jpg', 'fondo_alcorcon_15.jpg', 15, 1, 'Centrocampista'),
(16, 'Javi Ribelles', 'Ribelles', 78, 1.85, 'cara_alcorcon_16.jpg', 'fondo_alcorcon_16.jpg', 16, 1, 'Centrocampista'),
(17, 'Victor Garcia', 'Victor Garcia', 70, 1.8, 'cara_alcorcon_17.jpg', 'fondo_alcorcon_17.jpg', 17, 1, 'Centrocampista'),
(18, 'Ernesto Gómez', 'Ernesto', 65, 1.7, 'cara_alcorcon_19.jpg', 'fondo_alcorcon_19.jpg', 19, 1, 'Centrocampista'),
(19, 'Christian Borrego', 'Chiki', 71, 1.71, 'cara_alcorcon_9.jpg', 'fondo_alcorcon_9.jpg', 9, 1, 'Delantero'),
(20, 'Alberto González García', 'Berto', 64, 1.73, 'cara_alcorcon_11.jpg', 'fondo_alcorcon_11.jpg', 11, 1, 'Delantero'),
(21, 'Adrián Dalmau', 'Dalmau', 67, 1.77, 'cara_alcorcon_23.jpg', 'fondo_alcorcon_23.jpg', 23, 1, 'Delantero'),
(22, 'Emmanuel Addai', 'Addai', 65, 1.73, 'cara_alcorcon_36.jpg', 'fondo_alcorcon_36.jpg', 36, 1, 'Delantero'),
(23, 'Fran Fernández', 'Fran Fernández', 0, 0, 'cara_alcorcon_0.jpg', 'fondo_alcorcon_0.jpg', 0, 1, 'Entrenador'),
(24, 'Ian Mackay Abad', 'Mackay', 79, 1.81, 'cara_depor_1.jpg', 'fondo_depor_1.jpg', 1, 2, 'Portero'),
(25, 'Pablo Brea Fontáns', 'Pablo Brea', 94, 1.87, 'cara_depor_13.jpg', 'fondo_depor_13.jpg', 13, 2, 'Portero'),
(26, 'Eduardo Daniel Sousa Iglesias', 'Edu Sousa', 80, 1.81, 'cara_depor_24.jpg', 'fondo_depor_24.jpg', 24, 2, 'Portero'),
(27, 'Antonio Jesús Regal Angulo', 'Antoñito', 67, 1.75, 'cara_depor_2.jpg', 'fondo_depor_2.jpg', 2, 2, 'Defensa'),
(28, 'Adrián Lapeña Ruiz', 'Lapeña', 73, 1.81, 'cara_depor_3.jpg', 'fondo_depor_3.jpg', 3, 2, 'Defensa'),
(29, 'José Sánchez Martínez', 'Pepe Sanchez', 63, 1.85, 'cara_depor_5.jpg', 'fondo_depor_5.jpg', 5, 2, 'Defensa'),
(30, 'Orest Zinoviyovych Lebedenko', 'Lebedenko', 63, 1.74, 'cara_depor_12.jpg', 'fondo_depor_12.jpg', 12, 2, 'Defensa'),
(31, 'Raúl García Carnero', 'Raúl Carnero', 74, 1.8, 'cara_depor_15.jpg', 'fondo_depor_15.jpg', 15, 2, 'Defensa'),
(32, 'Pablo Jean-Antoine Martínez', 'Pablo Martinez', 78, 1.8, 'cara_depor_16.jpg', 'fondo_depor_16.jpg', 16, 2, 'Defensa'),
(33, 'Álvaro Pérez Campo', 'Trilli', 0, 1.86, 'cara_depor_18.jpg', 'fondo_depor_18.jpg', 18, 2, 'Defensa'),
(34, 'Jaime Sánchez Muñoz', 'Jaime', 77, 1.85, 'cara_depor_19.jpg', 'fondo_depor_19.jpg', 19, 2, 'Defensa'),
(35, 'Alejandro Bergantiños', 'Alex Bergantiños', 72, 1.77, 'cara_depor_4.jpg', 'fondo_depor_4.jpg', 4, 2, 'Centrocampista'),
(36, 'Isaac Gómez Sánchez', 'Isi Gómez', 62, 1.72, 'cara_depor_6.jpg', 'fondo_depor_6.jpg', 6, 2, 'Centrocampista'),
(37, 'Roberto Olabe del Arco', 'Olabe', 74, 1.78, 'cara_depor_8.jpg', 'fondo_depor_8.jpg', 8, 2, 'Centrocampista'),
(38, 'Mario Soriano Carreño', 'Mario Soriano', 60, 1.63, 'cara_depor_10.jpg', 'fondo_depor_10.jpg', 10, 2, 'Centrocampista'),
(39, 'Rubén Díez Adán', 'Rubén Díez', 62, 1.66, 'cara_depor_21.jpg', 'fondo_depor_21.jpg', 21, 2, 'Centrocampista'),
(40, 'Diego Villares Yáñez', 'Diego Villares', 69, 1.79, 'cara_depor_22.jpg', 'fondo_depor_22.jpg', 22, 2, 'Centrocampista'),
(41, 'Lucas Pérez Martínez', 'Lucas', 82, 1.8, 'cara_depor_7.jpg', 'fondo_depor_7.jpg', 7, 2, 'Delantero'),
(42, 'Javier Enrique Delgado Saverio', 'Saverio', 69, 1.78, 'cara_depor_11.jpg', 'fondo_depor_11.jpg', 11, 2, 'Delantero'),
(43, 'Arturo Juan Rodríguez Pérez-Reverte', 'Arturo', 75, 1.88, 'cara_depor_14.jpg', 'fondo_depor_14.jpg', 14, 2, 'Delantero'),
(44, 'José Luis Zalazar Martínez', 'KuKi Zalazar', 73, 1.73, 'cara_depor_17.jpg', 'fondo_depor_17.jpg', 17, 2, 'Delantero'),
(45, 'Max Svensson', 'Svensson', 77, 1.82, 'cara_depor_20.jpg', 'fondo_depor_20.jpg', 20, 2, 'Delantero'),
(46, 'Yeremay Hernández', 'Yeremay Hernández', 77, 1.82, 'cara_depor_23.jpg', 'fondo_depor_23.jpg', 23, 2, 'Delantero'),
(47, 'Óscar Cano', 'Óscar Cano', 0, 0, 'cara_depor_0.jpg', 'fondo_depor_0.jpg', 0, 2, 'Entrenador'),
(48, 'Luis Lopez', 'Luis Lopez', 84, 1.93, 'cara_madrid_1.jpg', 'fondo_madrid_1.jpg', 1, 3, 'Portero'),
(49, 'Lucas Cañizares', 'Lucas Cañizares', 81, 1.85, 'cara_madrid_13.jpg', 'fondo_madrid_13.jpg', 13, 3, 'Portero'),
(50, 'Mario de Luis', 'Mario de Luis', 80, 1.88, 'cara_madrid_24.jpg', 'fondo_madrid_24.jpg', 24, 3, 'Portero'),
(51, 'Vinicius Tobias', 'Tobias', 66, 1.77, 'cara_madrid_2.jpg', 'fondo_madrid_2.jpg', 2, 3, 'Defensa'),
(52, 'Rafa Marín', 'Rafa Marín', 73, 1.91, 'cara_madrid_3.jpg', 'fondo_madrid_3.jpg', 3, 3, 'Defensa'),
(53, 'Pablo Ramon', 'Pablo Ramon', 86, 1.85, 'cara_madrid_5.jpg', 'fondo_madrid_5.jpg', 5, 3, 'Defensa'),
(54, 'Edgar Pujol', 'Pujol', 63, 1.79, 'cara_madrid_26.jpg', 'fondo_madrid_26.jpg', 26, 3, 'Defensa'),
(55, 'Rafael Obrador', 'Obrador', 63, 1.81, 'cara_madrid_25.jpg', 'fondo_madrid_25.jpg', 25, 3, 'Defensa'),
(56, 'Alex Jiménez', 'Alex Jiménez', 63, 1.71, 'cara_madrid_29.jpg', 'fondo_madrid_29.jpg', 29, 3, 'Defensa'),
(57, 'Nicolás Paz', 'Nico Paz', 62, 1.85, 'cara_madrid_32.jpg', 'fondo_madrid_32.jpg', 32, 3, 'Centrocampista'),
(58, 'Carlos Dotor', 'Dotor', 67, 1.8, 'cara_madrid_8.jpg', 'fondo_madrid_8.jpg', 8, 3, 'Centrocampista'),
(59, 'Theo Zidane', 'Theo', 82, 1.96, 'cara_madrid_7.jpg', 'fondo_madrid_7.jpg', 7, 3, 'Centrocampista'),
(60, 'Javier Villar', 'Javi Villar', 0, 1.87, 'cara_madrid_18.jpg', 'fondo_madrid_18.jpg', 18, 3, 'Centrocampista'),
(61, 'Mario Martín', 'Mario Martín', 62, 1.77, 'cara_madrid_6.jpg', 'fondo_madrid_6.jpg', 6, 3, 'Centrocampista'),
(62, 'Sergio Arribas', 'Arribas', 61, 1.71, 'cara_madrid_10.jpg', 'fondo_madrid_10.jpg', 10, 3, 'Centrocampista'),
(63, 'Pipi Nakai', 'Nakai', 78, 1.82, 'cara_madrid_16.jpg', 'fondo_madrid_16.jpg', 16, 3, 'Centrocampista'),
(64, 'Alvaro Martín', 'Alvaro Martín', 69, 1.77, 'cara_madrid_22.jpg', 'fondo_madrid_22.jpg', 22, 3, 'Delantero'),
(65, 'Peter González', 'González', 69, 1.8, 'cara_madrid_11.jpg', 'fondo_madrid_11.jpg', 11, 3, 'Delantero'),
(66, 'Álvaro Rodríguez', 'Álvaro Rodríguez', 77, 1.93, 'cara_madrid_20.jpg', 'fondo_madrid_20.jpg', 20, 3, 'Delantero'),
(67, 'Iker Bravo', 'Bravo', 77, 1.84, 'cara_madrid_21.jpg', 'fondo_madrid_21.jpg', 21, 3, 'Delantero'),
(68, 'Noel Lopez', 'Noel', 77, 1.81, 'cara_madrid_9.jpg', 'fondo_madrid_9.jpg', 9, 3, 'Delantero'),
(69, 'Raúl Gonzalez Blanco', 'Raúl', 0, 0, 'cara_madrid_0.jpg', 'fondo_madrid_0.jpg', 0, 3, 'Entrenador'),
(70, 'Marvel', 'Marvel', 77, 1.84, 'cara_madrid_19.jpg', 'fondo_madrid_19.jpg', 19, 3, 'Defensa'),
(71, 'Álvaro Carrillo', 'Carrillo', 68, 1.82, 'cara_madrid_4.jpg', 'fondo_madrid_4.jpg', 4, 3, 'Defensa'),
(72, 'Felipe Ramos', 'Ramos', 86, 1.86, 'cara_cordoba_1.jpg', 'fondo_cordoba_1.jpg', 1, 4, 'Portero'),
(73, 'Carlos Marín', 'Marín', 79, 1.87, 'cara_cordoba_13.jpg', 'fondo_cordoba_13.jpg', 13, 4, 'Portero'),
(74, 'Pablo Picón', 'Picón', 80, 1.85, 'cara_cordoba_24.jpg', 'fondo_cordoba_24.jpg', 24, 4, 'Portero'),
(75, 'Jorge Moreno', 'Moreno', 78, 1.84, 'cara_cordoba_5.jpg', 'fondo_cordoba_5.jpg', 5, 4, 'Defensa'),
(76, 'Dragisa Gudelj', 'Gudelj', 82, 1.86, 'cara_cordoba_8.jpg', 'fondo_cordoba_8.jpg', 8, 4, 'Defensa'),
(77, 'Alberto Jiménez', 'Jiménez', 82, 1.86, 'cara_cordoba_12.jpg', 'fondo_cordoba_12.jpg', 12, 4, 'Defensa'),
(78, 'José Manuel Alonso', 'Jose Alonso', 82, 1.86, 'cara_cordoba_14.jpg', 'fondo_cordoba_14.jpg', 14, 4, 'Defensa'),
(79, 'José Calderón', 'Calderón', 60, 1.7, 'cara_cordoba_4.jpg', 'fondo_cordoba_4.jpg', 4, 4, 'Defensa'),
(80, 'Ekaitz Jiménez', 'Ekaitz', 65, 1.69, 'cara_cordoba_3.jpg', 'fondo_cordoba_3.jpg', 3, 4, 'Defensa'),
(81, 'José Ruiz', 'Ruiz', 75, 1.77, 'cara_cordoba_2.jpg', 'fondo_cordoba_2.jpg', 2, 4, 'Defensa'),
(82, 'Carlos Puga', 'Puga', 70, 1.75, 'cara_cordoba_16.jpg', 'fondo_cordoba_16.jpg', 16, 4, 'Defensa'),
(83, 'Antonio Caballero', 'Caballero', 64, 1.75, 'cara_cordoba_15.jpg', 'fondo_cordoba_15.jpg', 15, 4, 'Centrocampista'),
(84, 'Armando Shashoua', 'Armando', 62, 1.77, 'cara_cordoba_6.jpg', 'fondo_cordoba_6.jpg', 6, 4, 'Centrocampista'),
(85, 'Yussi Diarra', 'Diarra', 71, 1.74, 'cara_cordoba_22.jpg', 'fondo_cordoba_22.jpg', 22, 4, 'Centrocampista'),
(86, 'Javi Flores', 'Javi Flores', 64, 1.7, 'cara_cordoba_21.jpg', 'fondo_cordoba_21.jpg', 21, 4, 'Centrocampista'),
(87, 'Marco Camús', 'Camús', 77, 1.84, 'cara_cordoba_17.jpg', 'fondo_cordoba_17.jpg', 17, 4, 'Delantero'),
(88, 'Simo Bouzaidi', 'Simo', 62, 1.71, 'cara_cordoba_18.jpg', 'fondo_cordoba_18.jpg', 18, 4, 'Delantero'),
(89, 'Cristian Carracedo', 'Carracedo', 75, 1.79, 'cara_cordoba_23.jpg', 'fondo_cordoba_23.jpg', 23, 4, 'Delantero'),
(90, 'Juan Villar', 'Juan Villar', 77, 1.81, 'cara_cordoba_9.jpg', 'fondo_cordoba_9.jpg', 9, 4, 'Delantero'),
(91, 'Canario', 'Canario', 69, 1.8, 'cara_cordoba_11.jpg', 'fondo_cordoba_11.jpg', 11, 4, 'Delantero'),
(92, 'Kike Márquez', 'Kike', 72, 1.76, 'cara_cordoba_19.jpg', 'fondo_cordoba_19.jpg', 19, 4, 'Delantero'),
(93, 'Willy Ledesma', 'Willy Ledesma', 77, 1.81, 'cara_cordoba_7.jpg', 'fondo_cordoba_7.jpg', 7, 4, 'Delantero'),
(94, 'Antonio Casas', 'Antonio Casas', 80, 1.83, 'cara_cordoba_20.jpg', 'fondo_cordoba_20.jpg', 20, 4, 'Delantero'),
(95, 'Germán Crespo', 'Germán Crespo', 0, 0, 'cara_cordoba_0.jpg', 'fondo_cordoba_0.jpg', 0, 4, 'Entrenador'),
(96, 'Diego Rivas', 'Diego Rivas', 75, 1.86, 'cara_ferrol_1.jpg', 'fondo_ferrol_1.jpg', 1, 5, 'Portero'),
(97, 'Gianfranco Gazzaniga', 'Gazzaniga', 85, 1.85, 'cara_ferrol_13.jpg', 'fondo_ferrol_13.jpg', 13, 5, 'Portero'),
(98, 'Joan Salvá', 'Joan', 80, 1.85, 'cara_ferrol_24.jpg', 'fondo_ferrol_24.jpg', 24, 5, 'Portero'),
(99, 'Enrique Fornos', 'Quique', 69, 1.81, 'cara_ferrol_5.jpg', 'fondo_ferrol_5.jpg', 5, 5, 'Defensa'),
(100, 'Jon García', 'Jon', 70, 1.77, 'cara_ferrol_4.jpg', 'fondo_ferrol_4.jpg', 4, 5, 'Defensa'),
(101, 'Aitor Pascual', 'Aitor', 74, 1.78, 'cara_ferrol_3.jpg', 'fondo_ferrol_3.jpg', 3, 5, 'Defensa'),
(102, 'Tomás Bourdal', 'Burdal', 75, 1.77, 'cara_ferrol_2.jpg', 'fondo_ferrol_2.jpg', 2, 5, 'Defensa'),
(103, 'Luca Ferrone', 'Luca', 75, 1.82, 'cara_ferrol_12.jpg', 'fondo_ferrol_12.jpg', 12, 5, 'Defensa'),
(104, 'David Castro', 'Castro', 86, 1.92, 'cara_ferrol_15.jpg', 'fondo_ferrol_15.jpg', 15, 5, 'Defensa'),
(105, 'Enol Coto', 'Enol', 64, 1.74, 'cara_ferrol_17.jpg', 'fondo_ferrol_17.jpg', 17, 5, 'Defensa'),
(106, 'Brais Martínez', 'Brais', 62, 1.71, 'cara_ferrol_18.jpg', 'fondo_ferrol_18.jpg', 18, 5, 'Defensa'),
(107, 'Jesús Bernal', 'Jesús', 62, 1.77, 'cara_ferrol_6.jpg', 'fondo_ferrol_6.jpg', 6, 5, 'Centrocampista'),
(108, 'Alex Lopez Sanchez', 'Alex', 64, 1.78, 'cara_ferrol_8.jpg', 'fondo_ferrol_8.jpg', 8, 5, 'Jesús Bernal'),
(109, 'David del Pozo', 'David', 82, 1.78, 'cara_ferrol_14.jpg', 'fondo_ferrol_14.jpg', 14, 5, 'Centrocampista'),
(110, 'Fran Manzanara', 'Fran', 75, 1.83, 'cara_ferrol_16.jpg', 'fondo_ferrol_16.jpg', 16, 5, 'Centrocampista'),
(111, 'Héber Pena', 'Heber', 68, 1.76, 'cara_ferrol_7.jpg', 'fondo_ferrol_7.jpg', 7, 5, 'Delantero'),
(112, 'Manu Justo', 'Manu', 77, 1.74, 'cara_ferrol_9.jpg', 'fondo_ferrol_9.jpg', 9, 5, 'Delantero'),
(113, 'Dani nieto', 'Dani', 77, 1.81, 'cara_ferrol_10.jpg', 'fondo_ferrol_10.jpg', 10, 5, 'Delantero'),
(114, 'Luis Chacón', 'Chacón', 69, 1.8, 'cara_ferrol_11.jpg', 'fondo_ferrol_11.jpg', 11, 5, 'Delantero'),
(115, 'Jorge Padilla', 'Padilla', 66, 1.75, 'cara_ferrol_19.jpg', 'fondo_ferrol_19.jpg', 19, 5, 'Delantero'),
(116, 'Pep Caballé', 'Caballe', 80, 1.83, 'cara_ferrol_20.jpg', 'fondo_ferrol_20.jpg', 20, 5, 'Delantero'),
(117, 'Jaume Jardi', 'Jaume', 64, 1.77, 'cara_ferrol_21.jpg', 'fondo_ferrol_21.jpg', 21, 5, 'Delantero'),
(118, 'Joselu Gomez', 'Joselu', 78, 1.87, 'cara_ferrol_22.jpg', 'fondo_ferrol_22.jpg', 22, 5, 'Delantero'),
(119, 'Carlos Vicente', 'Carlos vicente', 75, 1.79, 'cara_ferrol_23.jpg', 'fondo_ferrol_23.jpg', 23, 5, 'Delantero'),
(120, 'Cristóbal Parralo', 'Cristóbal Parralo', 0, 0, 'cara_ferrol_0.jpg', 'fondo_ferrol_0.jpg', 0, 5, 'Entrenador'),
(121, 'Christian Joel', 'Joel', 80, 1.93, 'cara_celta_24.jpg', 'fondo_celta_24.jpg', 24, 6, 'Portero'),
(122, 'Coke Carrillo', 'Coke', 75, 1.88, 'cara_celta_1.jpg', 'fondo_celta_1.jpg', 1, 6, 'Portero'),
(123, 'Raúl García', 'Raúl García', 85, 1.88, 'cara_celta_13.jpg', 'fondo_celta_13.jpg', 13, 6, 'Portero'),
(124, 'Carlos Domínguez', 'Domínguez', 82, 1.87, 'cara_celta_14.jpg', 'fondo_celta_14.jpg', 14, 6, 'Defensa'),
(125, 'Sergio García', 'Sergio García', 75, 1.86, 'cara_celta_5.jpg', 'fondo_celta_5.jpg', 5, 6, 'Defensa'),
(126, 'Javi Domínguez', 'Domínguez', 70, 1.95, 'cara_celta_4.jpg', 'fondo_celta_4.jpg', 4, 6, 'Defensa'),
(127, 'Iván López', 'Iván López', 80, 1.83, 'cara_celta_20.jpg', 'fondo_celta_20.jpg', 20, 6, 'Defensa'),
(128, 'Fernando Medrano', 'Aitor', 74, 1.72, 'cara_celta_3.jpg', 'fondo_celta_3.jpg', 3, 6, 'Defensa'),
(129, 'Martín Conde', 'Conde', 66, 1.75, 'cara_celta_19.jpg', 'fondo_celta_19.jpg', 19, 6, 'Defensa'),
(130, 'Thomas Carrique', 'Carrique', 76, 1.73, 'cara_celta_2.jpg', 'fondo_celta_2.jpg', 2, 6, 'Defensa'),
(131, 'Enrique Fornos', 'Quique', 69, 1.81, 'cara_celta_6.jpg', 'fondo_celta_6.jpg', 6, 6, 'Defensa'),
(132, 'Javi Rodríguez', 'Javi Rodríguez', 75, 1.79, 'cara_celta_33.jpg', 'fondo_celta_33.jpg', 33, 6, 'Defensa'),
(133, 'Victor San Bartolomé', 'Victor SB', 64, 1.886, 'cara_celta_21.jpg', 'fondo_celta_21.jpg', 21, 6, 'Centrocampista'),
(134, 'Yoel Lago', 'Yoel', 62, 1.77, 'cara_celta_27.jpg', 'fondo_celta_27.jpg', 27, 6, 'Centrocampista'),
(135, 'Martín Calderón', 'Calderón', 86, 1.83, 'cara_celta_16.jpg', 'fondo_celta_16.jpg', 16, 6, 'Centrocampista'),
(136, 'Carlos Beitia', 'Beitia', 68, 1.85, 'cara_celta_6.jpg', 'fondo_celta_6.jpg', 6, 6, 'Centrocampista'),
(137, 'Hugo Sotelo', 'Sotelo', 78, 1.87, 'cara_celta_22.jpg', 'fondo_celta_22.jpg', 22, 6, 'Centrocampista'),
(138, 'Damián Rodriguez', 'Damián', 62, 1.71, 'cara_celta_26.jpg', 'fondo_celta_26.jpg', 26, 6, 'Centrocampista'),
(139, 'Raúl Blanco', 'Raúl Blanco', 64, 1.73, 'cara_celta_17.jpg', 'fondo_celta_17.jpg', 17, 6, 'Centrocampista'),
(140, 'Clemente Montes', 'Montes', 64, 1.78, 'cara_celta_8.jpg', 'fondo_celta_8.jpg', 8, 6, 'Delantero'),
(141, 'Hugo Álvarez', 'Hugo', 64, 1.76, 'cara_celta_23.jpg', 'fondo_celta_23.jpg', 23, 6, 'Delantero'),
(142, 'Iker Losada', 'Losada', 77, 1.75, 'cara_celta_10.jpg', 'fondo_celta_10.jpg', 10, 6, 'Delantero'),
(143, 'Miguel Rodriguez', 'Rodriguez', 76, 1.79, 'cara_celta_7.jpg', 'fondo_celta_7.jpg', 7, 6, 'Delantero'),
(144, 'Pablo Durán', 'Durán', 76, 1.76, 'cara_celta_18.jpg', 'fondo_celta_18.jpg', 18, 6, 'Delantero'),
(145, 'Manu Garrido', 'Garrido', 69, 1.9, 'cara_celta_11.jpg', 'fondo_celta_11.jpg', 11, 6, 'Delantero'),
(146, 'Lautaro de León', 'Lautaro', 77, 1.85, 'cara_celta_9.jpg', 'fondo_celta_9.jpg', 9, 6, 'Delantero'),
(147, ' Claudio Giràldez', ' Claudio Giràldezo', 0, 0, 'cara_celta_0.jpg', 'fondo_celta_0.jpg', 0, 6, 'Entrenador'),
(148, 'Ernestas Juskevicius', 'Juskevicius', 75, 1.88, 'cara_linares_1.jpg', 'fondo_linares_1.jpg', 1, 7, 'Portero'),
(149, 'Samuel Casado', 'Casado', 85, 1.87, 'cara_linares_13.jpg', 'fondo_linares_13.jpg', 13, 7, 'Portero'),
(150, 'José Antonio Caro', 'Caro', 86, 1.83, 'cara_linares_16.jpg', 'fondo_linares_16.jpg', 16, 7, 'Defensa'),
(151, 'Luciano Squadrone', 'Luciano', 76, 1.85, 'cara_linares_2.jpg', 'fondo_linares_2.jpg', 2, 7, 'Defensa'),
(152, 'Antonio Cañete', 'Cañete', 70, 1.97, 'cara_linares_4.jpg', 'fondo_linares_4.jpg', 4, 7, 'Defensa'),
(153, 'Fran Varela', 'Varela', 70, 1.74, 'cara_linares_3.jpg', 'fondo_linares_3.jpg', 3, 7, 'Defensa'),
(154, 'Alfonso Candelas', 'Candelas', 64, 1.7, 'cara_linares_23.jpg', 'fondo_linares_23.jpg', 23, 7, 'Defensa'),
(155, 'Dani Perejón', 'Perejón', 76, 1.76, 'cara_linares_18.jpg', 'fondo_linares_18.jpg', 18, 7, 'Defensa'),
(156, 'Eduaro Viaña', 'Edu Viaña', 80, 1.74, 'cara_linares_20.jpg', 'fondo_linares_20.jpg', 20, 7, 'Defensa'),
(157, 'Aitor Gelardo', 'Aitor', 80, 1.8, 'cara_linares_19.jpg', 'fondo_linares_19.jpg', 19, 7, 'Centrocampista'),
(158, 'Lolo González', 'Lolo', 75, 1.86, 'cara_linares_5.jpg', 'fondo_linares_5.jpg', 5, 7, 'Centrocampista'),
(159, 'Alberto Rodríguez Expósito', 'Rodri', 64, 1.78, 'cara_linares_8.jpg', 'fondo_linares_8.jpg', 8, 7, 'Centrocampista'),
(160, 'Javier Duarte', 'Duarte', 62, 1.79, 'cara_linares_15.jpg', 'fondo_linares_15.jpg', 15, 7, 'Centrocampista'),
(161, 'Francisco Callejón', 'Callejón', 64, 1.8, 'cara_linares_21.jpg', 'fondo_linares_21.jpg', 21, 7, 'Centrocampista'),
(162, 'Álvaro Arnedo', 'Arnedo', 69, 1.78, 'cara_linares_6.jpg', 'fondo_linares_6.jpg', 6, 7, 'Centrocampista'),
(163, 'Fermín Lopez', 'Fermín Lopez', 77, 1.75, 'cara_linares_10.jpg', 'fondo_linares_10.jpg', 10, 7, 'Centrocampista'),
(164, 'Mawi Sánchez', 'Mawi', 77, 1.74, 'cara_linares_9.jpg', 'fondo_linares_9.jpg', 9, 7, 'Delantero'),
(165, 'Ignacio Abeledo', 'Abeledo', 64, 1.82, 'cara_linares_17.jpg', 'fondo_linares_17.jpg', 17, 7, 'Delantero'),
(166, 'Alfonso Fernández', 'Alfonso', 69, 1.79, 'cara_linares_11.jpg', 'fondo_linares_11.jpg', 11, 7, 'Delantero'),
(167, 'Álex Sancris', 'Álex Sancris', 78, 1.81, 'cara_linares_22.jpg', 'fondo_linares_22.jpg', 22, 7, 'Delantero'),
(168, 'Hugo Díaz', 'Hugo Díaz', 76, 1.74, 'cara_linares_7.jpg', 'fondo_linares_7.jpg', 7, 7, 'Delantero'),
(169, 'Samuel Corral', 'Corral', 82, 1.84, 'cara_linares_14.jpg', 'fondo_linares_14.jpg', 14, 7, 'Defensa'),
(170, 'Alberto González', 'Alberto González', 0, 0, 'cara_linares_0.jpg', 'fondo_linares_0.jpg', 0, 7, 'Entrenador'),
(171, 'Pol Ballesté', 'Pol', 75, 1.8, 'cara_cultural_1.jpg', 'fondo_cultural_1.jpg', 1, 8, 'Portero'),
(172, 'Salvi Carrasco', 'Carrasco', 85, 1.92, 'cara_cultural_13.jpg', 'fondo_cultural_13.jpg', 13, 8, 'Portero'),
(173, 'Anton Matthäi', 'Matthäi', 85, 1.96, 'cara_cultural_24.jpg', 'fondo_cultural_24.jpg', 24, 8, 'Portero'),
(174, 'Jon Ander Amelibia', 'Amelibia', 75, 1.8, 'cara_cultural_5.jpg', 'fondo_cultural_5.jpg', 5, 8, 'Defensa'),
(175, 'Pablo Trigueros', 'Trigueros', 70, 1.88, 'cara_cultural_4.jpg', 'fondo_cultural_4.jpg', 4, 8, 'Defensa'),
(176, 'Christian Perez', 'Christian', 70, 1.81, 'cara_cultural_12.jpg', 'fondo_cultural_12.jpg', 12, 8, 'Defensa'),
(177, 'Francisco Cruz', 'Fran Cruz', 64, 1.85, 'cara_cultural_21.jpg', 'fondo_cultural_21.jpg', 21, 8, 'Defensa'),
(178, 'Yoann Aráujo', 'Aráujo', 62, 1.85, 'cara_cultural_15.jpg', 'fondo_cultural_15.jpg', 15, 8, 'Defensa'),
(179, 'Joel López', 'Joel', 70, 1.8, 'cara_cultural_3.jpg', 'fondo_cultural_3.jpg', 3, 8, 'Defensa'),
(180, 'Julen Castañeda', 'Julen', 82, 1.8, 'cara_cultural_14.jpg', 'fondo_cultural_14.jpg', 14, 8, 'Defensa'),
(181, 'Joseba Muguruza', 'Joseba', 78, 1.71, 'cara_cultural_22.jpg', 'fondo_cultural_22.jpg', 22, 8, 'Defensa'),
(182, 'Saúl González', 'Saúl', 76, 1.72, 'cara_cultural_2.jpg', 'fondo_cultural_2.jpg', 2, 8, 'Defensa'),
(183, 'Alex Blesa', 'Blesa', 80, 1.8, 'cara_cultural_20.jpg', 'fondo_cultural_20.jpg', 20, 8, 'Centrocampista'),
(184, 'Kevin Presa', 'Kevin', 69, 1.78, 'cara_cultural_6.jpg', 'fondo_cultural_6.jpg', 6, 8, 'Centrocampista'),
(185, 'Tarsi Aguado', 'Aguado', 64, 1.79, 'cara_cultural_17.jpg', 'fondo_cultural_17.jpg', 17, 8, 'Centrocampista'),
(186, 'Jesús Álvarez', 'Álvarez', 64, 1.8, 'cara_cultural_8.jpg', 'fondo_cultural_8.jpg', 8, 8, 'Centrocampista'),
(187, 'Alberto Solís', 'Solís', 77, 1.78, 'cara_cultural_10.jpg', 'fondo_cultural_10.jpg', 10, 8, 'Centrocampista'),
(188, 'Roberto Alarcón', 'Roberto', 76, 1.78, 'cara_cultural_7.jpg', 'fondo_cultural_7.jpg', 7, 8, 'Delantero'),
(189, 'Andy Kawaya', 'Kawaya', 76, 1.74, 'cara_cultural_18.jpg', 'fondo_cultural_18.jpg', 18, 8, 'Delantero'),
(190, 'Néstor Querol', 'Querol', 69, 1.84, 'cara_cultural_11.jpg', 'fondo_cultural_11.jpg', 11, 8, 'Delantero'),
(191, 'Marco Toscano', 'Toscano', 86, 1.85, 'cara_cultural_16.jpg', 'fondo_cultural_16.jpg', 16, 8, 'Delantero'),
(192, 'Nikolay Obolskiy', 'Obolskiy', 77, 1.86, 'cara_cultural_9.jpg', 'fondo_cultural_9.jpg', 9, 8, 'Delantero'),
(193, 'Claudio Medina', 'Claudio', 80, 1.8, 'cara_cultural_19.jpg', 'fondo_cultural_19.jpg', 19, 8, 'Delantero'),
(194, 'Diego Percan', 'Percan', 64, 1.7, 'cara_cultural_23.jpg', 'fondo_cultural_23.jpg', 23, 8, 'Delantero'),
(195, 'Eduardo Docampo', 'Eduardo Docampo', 0, 0, 'cara_cultural_0.jpg', 'fondo_cultural_0.jpg', 0, 8, 'Entrenador'),
(196, 'Javi Montoya', 'Pol', 75, 1.86, 'cara_merida_1.jpg', 'fondo_cultural_1.jpg', 1, 9, 'Portero'),
(197, 'Javi Montoya', 'Juanpa', 85, 1.92, 'cara_merida_13.jpg', 'fondo_cultural_13.jpg', 13, 9, 'Portero'),
(198, 'Manuel Bonarque', 'Bonarque', 70, 1.82, 'cara_merida_4.jpg', 'fondo_cultural_4.jpg', 4, 9, 'Defensa'),
(199, 'Ignacio González', 'Nacho', 76, 1.85, 'cara_merida_18.jpg', 'fondo_cultural_18.jpg', 18, 9, 'Defensa'),
(200, 'Erik Ruiz', 'Erik', 75, 1.84, 'cara_merida_5.jpg', 'fondo_cultural_5.jpg', 5, 9, 'Defensa'),
(201, 'Álvaro Ramón', 'Álvaro Ramón', 82, 1.8, 'cara_merida_20.jpg', 'fondo_cultural_20.jpg', 20, 9, 'Defensa'),
(202, 'David de la Víbora', 'David', 70, 1.68, 'cara_merida_3.jpg', 'fondo_cultural_3.jpg', 3, 9, 'Defensa'),
(203, 'Felipe Alonso', 'Felipe', 80, 1.75, 'cara_cultural_19.jpg', 'fondo_cultural_19.jpg', 19, 9, 'Defensa'),
(204, 'Diego Parras', 'Parras', 76, 1.78, 'cara_cultural_2.jpg', 'fondo_cultural_2.jpg', 2, 9, 'Defensa'),
(205, 'Alejandro Melendez', 'Melendez', 69, 1.68, 'cara_merida_11.jpg', 'fondo_cultural_11.jpg', 11, 9, 'Centrocampista'),
(206, 'Luis Acosta', 'Acosta', 82, 1.8, 'cara_cultural_14.jpg', 'fondo_cultural_14.jpg', 14, 9, 'Centrocampista'),
(207, 'Mohamed Kamal', 'Kamal', 69, 1.78, 'cara_cultural_6.jpg', 'fondo_cultural_6.jpg', 6, 9, 'Centrocampista'),
(208, 'Dani Lorenzo', 'Lorenzo', 78, 1.76, 'cara_cultural_22.jpg', 'fondo_cultural_22.jpg', 22, 9, 'Centrocampista'),
(209, 'Dani Rodríguez', 'Dani', 64, 1.7, 'cara_cultural_23.jpg', 'fondo_cultural_23.jpg', 23, 9, 'Centrocampista'),
(210, 'Nassourou Ben Hamed', 'Nassourou', 64, 1.7, 'cara_cultural_21.jpg', 'fondo_cultural_21.jpg', 21, 9, 'Centrocampista'),
(211, 'David Larrubia', 'Larrubia', 64, 1.72, 'cara_cultural_8.jpg', 'fondo_cultural_8.jpg', 8, 9, 'Centrocampista'),
(212, 'Daniel Sandoval', 'Sandoval', 76, 1.78, 'cara_cultural_7.jpg', 'fondo_cultural_7.jpg', 7, 9, 'Delantero'),
(213, 'Fran Viñuela', 'Viñuela', 64, 1.83, 'cara_cultural_17.jpg', 'fondo_cultural_17.jpg', 17, 9, 'Delantero'),
(214, 'Akito Mukai', 'Akito Mukau', 86, 1.68, 'cara_cultural_16.jpg', 'fondo_cultural_16.jpg', 16, 9, 'Delantero'),
(215, 'Nando Copete', 'Nando', 77, 1.83, 'cara_cultural_9.jpg', 'fondo_cultural_9.jpg', 9, 9, 'Delantero'),
(216, 'Manuel Coronado Plá', 'Lolo Plá', 77, 1.78, 'cara_cultural_10.jpg', 'fondo_cultural_10.jpg', 10, 9, 'Delantero'),
(217, 'Rafael González Rodríguez', 'Chuma', 70, 1.81, 'cara_cultural_12.jpg', 'fondo_cultural_12.jpg', 12, 9, 'Delantero'),
(218, 'Carlos Cinta', 'Cinta', 62, 1.82, 'cara_cultural_15.jpg', 'fondo_cultural_15.jpg', 15, 9, 'Delantero'),
(219, 'Juanma Barrero', 'Juanma Barrero', 0, 0, 'cara_cultural_0.jpg', 'fondo_cultural_0.jpg', 0, 9, 'Entrenador'),
(220, 'Diego Moreno', 'Diego Moreno', 75, 1.8, 'cara_majadahonda_1.jpg', 'fondo_majadahonda_1.jpg', 1, 10, 'Portero'),
(221, 'Lucho García', 'Juanpa', 85, 1.92, 'cara_majadahonda_13.jpg', 'fondo_majadahonda_13.jpg', 13, 10, 'Portero'),
(222, 'Gorka Giralt', 'Gorka', 85, 1.9, 'cara_majadahonda_24.jpg', 'fondo_majadahonda_24.jpg', 24, 10, 'Portero'),
(223, 'Félix Ofoli', 'Félix', 70, 1.86, 'cara_majadahonda_4.jpg', 'fondo_majadahonda_4.jpg', 4, 10, 'Defensa'),
(224, 'Jorge Casado', 'Jorge', 70, 1.85, 'cara_majadahonda_3.jpg', 'fondo_majadahonda_3.jpg', 3, 10, 'Defensa'),
(225, 'Pelayo Suárez', 'Pelayo', 82, 1.83, 'cara_majadahonda_20.jpg', 'fondo_majadahonda_20.jpg', 20, 10, 'Defensa'),
(226, 'Héctor Hernández', 'Héctor', 69, 1.71, 'cara_majadahonda_6.jpg', 'fondo_majadahonda_6.jpg', 6, 10, 'Defensa'),
(227, 'Daniel Pinillos', 'Pinillos', 62, 1.83, 'cara_majadahonda_15.jpg', 'fondo_majadahonda_15.jpg', 15, 10, 'Defensa'),
(228, 'Josu Ozkoidi', 'Ozkoidi', 86, 1.71, 'cara_majadahonda_16.jpg', 'fondo_majadahonda_16.jpg', 16, 10, 'Defensa'),
(229, 'Rahim Alhassane', 'Rahim', 64, 1.84, 'cara_majadahonda_21.jpg', 'fondo_majadahonda_21.jpg', 21, 10, 'Defensa'),
(230, 'Ivan Rodríguez', 'Ivan', 80, 1.8, 'cara_majadahonda_19.jpg', 'fondo_majadahonda_19.jpg', 19, 10, 'Defensa'),
(231, 'Aitor Aldalur', 'Aldalur', 76, 1.78, 'cara_majadahonda_2.jpg', 'fondo_majadahonda_2.jpg', 2, 10, 'Defensa'),
(232, 'Mario García', 'Mario', 64, 1.72, 'cara_majadahonda_8.jpg', 'fondo_majadahonda_8.jpg', 8, 10, 'Centrocampista'),
(233, 'Sergio Llamas', 'Llamas', 64, 1.78, 'cara_majadahonda_23.jpg', 'fondo_majadahonda_23.jpg', 23, 10, 'Centrocampista'),
(234, 'Manuel de Iriondo', 'Iriondo', 75, 1.79, 'cara_majadahonda_5.jpg', 'fondo_majadahonda_5.jpg', 5, 10, 'Centrocampista'),
(235, 'Javi Ros', 'Javi Ros', 82, 1.73, 'cara_majadahonda_14.jpg', 'fondo_majadahonda_14.jpg', 14, 10, 'Centrocampista'),
(236, 'Iñigo Alayeto', 'Alayeto', 64, 1.8, 'cara_majadahonda_17.jpg', 'fondo_majadahonda_17.jpg', 17, 10, 'Delantero'),
(237, 'Fernando Garcia', 'Nando', 78, 1.8, 'cara_majadahonda_22.jpg', 'fondo_majadahonda_22.jpg', 22, 10, 'Delantero'),
(238, 'Rubén Torres', 'Rubén', 76, 1.74, 'cara_majadahonda_18.jpg', 'fondo_majadahonda_18.jpg', 18, 10, 'Delantero'),
(239, 'Néstor Albiach', 'Néstor', 77, 1.87, 'cara_majadahonda_10.jpg', 'fondo_majadahonda_10.jpg', 10, 10, 'Delantero'),
(240, 'Jeisson Martínez', 'Jeisson', 69, 1.8, 'cara_majadahonda_11.jpg', 'fondo_majadahonda_11.jpg', 11, 10, 'Delantero'),
(241, 'David Rodríguez', 'David', 76, 1.8, 'cara_majadahonda_7.jpg', 'fondo_majadahonda_7.jpg', 7, 10, 'Delantero'),
(242, 'Roman Zozulya', 'Zozulya', 70, 1.76, 'cara_majadahonda_12.jpg', 'fondo_majadahonda_12.jpg', 12, 10, 'Delantero'),
(243, 'Alfredo Santaelena', 'Alfredo Santaelena', 0, 0, 'cara_majadahonda_0.jpg', 'fondo_majadahonda_0.jpg', 0, 10, 'Entrenador'),
(244, 'Pedro López', 'Pedro López', 85, 1.89, 'cara_sanse_1.jpg', 'fondo_sanse_1.jpg', 1, 11, 'Portero'),
(245, 'Ismael de Andrés', 'Isma', 85, 1.92, 'cara_sanse_13.jpg', 'fondo_sanse_13.jpg', 13, 11, 'Portero'),
(246, 'Alejandro Palop', 'Palop', 85, 1.9, 'cara_sanse_25.jpg', 'fondo_sanse_25.jpg', 25, 11, 'Portero'),
(247, 'Charlie Dean I\'Anson', 'Charlie', 75, 1.82, 'cara_sanse_5.jpg', 'fondo_sanse_5.jpg', 5, 11, 'Defensa'),
(248, 'Euse Monzó', 'Monzó', 70, 1.88, 'cara_sanse_3.jpg', 'fondo_sanse_3.jpg', 3, 11, 'Defensa'),
(249, 'Franck Ferry Fomeyem Sob', 'Fomeyem', 70, 1.81, 'cara_sanse_12.jpg', 'fondo_sanse_12.jpg', 12, 11, 'Defensa'),
(250, 'Juan Ramón Gómez-Pimpollo', 'Juanra', 70, 1.85, 'cara_sanse_4.jpg', 'fondo_sanse_4.jpg', 4, 11, 'Defensa'),
(251, 'Sergio Nieto', 'Nieto', 64, 1.73, 'cara_sanse_23.jpg', 'fondo_sanse_23.jpg', 23, 11, 'Defensa'),
(252, 'Rafael Navarro', 'Rafa Navarro', 64, 1.73, 'cara_sanse_2.jpg', 'fondo_sanse_2.jpg', 2, 11, 'Defensa'),
(253, 'Javier Aparicio', 'Aparicio', 78, 1.8, 'cara_sanse_22.jpg', 'fondo_sanse_22.jpg', 22, 11, 'Defensa'),
(254, 'Aly Coulibaly', 'Coulibaly', 82, 1.83, 'cara_sanse_20.jpg', 'fondo_sanse_20.jpg', 20, 11, 'Centrocampista'),
(255, 'Alberto Villapalos', 'VillaPalos', 69, 1.91, 'cara_sanse_6.jpg', 'fondo_sanse_6.jpg', 6, 11, 'Centrocampista'),
(256, 'Rafael de Vicente', 'Rafa', 64, 1.79, 'cara_sanse_17.jpg', 'fondo_sanse_17.jpg', 17, 11, 'Centrocampista'),
(257, 'Javi Mecerreyes', 'Mecerreyes', 86, 1.83, 'cara_sanse_16.jpg', 'fondo_sanse_16.jpg', 16, 11, 'Centrocampista'),
(258, 'Borja Sánchez', 'Borja Sánchez', 64, 1.79, 'cara_sanse_21.jpg', 'fondo_sanse_21.jpg', 21, 11, 'Centrocampista'),
(259, 'Borja Martínez', 'Borja', 69, 1.77, 'cara_sanse_11.jpg', 'fondo_sanse_11.jpg', 11, 11, 'Delantero'),
(260, 'Raúl Hernández', 'Raúl', 76, 1.83, 'cara_sanse_7.jpg', 'fondo_sanse_7.jpg', 7, 11, 'Delantero'),
(261, 'Javier Gómez', 'Gómez', 80, 1.77, 'cara_sanse_19.jpg', 'fondo_sanse_19.jpg', 19, 11, 'Delantero'),
(262, 'Jorge Jiménez', 'Jiménez', 77, 1.87, 'cara_sanse_10.jpg', 'fondo_sanse_10.jpg', 10, 11, 'Delantero'),
(263, 'Pedro Benito', 'Benito', 82, 1.82, 'cara_sanse_14.jpg', 'fondo_sanse_14.jpg', 14, 11, 'Delantero'),
(264, 'Alberto Ródenas', 'Ródenas', 85, 1.87, 'cara_sanse_18.jpg', 'fondo_sanse_18.jpg', 18, 11, 'Delantero'),
(265, 'Adama Keita', 'Keita', 62, 1.83, 'cara_sanse_28.jpg', 'fondo_sanse_28.jpg', 28, 11, 'Delantero'),
(266, 'Miguel Ángel Martínez', 'Lobo', 0, 0, 'cara_sanse_0.jpg', 'fondo_sanse_0.jpg', 0, 11, 'Entrenador'),
(267, 'Alberto Varo', 'Varo', 85, 1.91, 'cara_linense_1.jpg', 'fondo_linense_1.jpg', 1, 12, 'Portero'),
(268, 'Ángel de la Clazada', 'Ángel', 85, 1.91, 'cara_linense_13.jpg', 'fondo_linense_13.jpg', 13, 12, 'Portero'),
(269, 'Jesús Muñoz', 'Muñoz', 70, 1.83, 'cara_linense_4.jpg', 'fondo_linense_4.jpg', 4, 12, 'Defensa'),
(270, 'Francisco Morante', 'Fran Morante', 75, 1.82, 'cara_linense_5.jpg', 'fondo_linense_5.jpg', 5, 12, 'Defensa'),
(271, 'Borja López', 'Borja López', 75, 1.85, 'cara_linense_15.jpg', 'fondo_linense_15.jpg', 15, 12, 'Defensa'),
(272, 'Nicolás Delmonte', 'Delmonte', 82, 1.8, 'cara_linense_20.jpg', 'fondo_linense_20.jpg', 20, 12, 'Defensa'),
(273, 'Papa Bamory Camara', 'Camara', 82, 1.88, 'cara_linense_27.jpg', 'fondo_linense_27.jpg', 27, 12, 'Defensa'),
(274, 'Connor Ruane', 'Connor ', 69, 1.78, 'cara_linense_11.jpg', 'fondo_linense_11.jpg', 11, 12, 'Defensa'),
(275, 'Lorenzo Fernández', 'Loren', 76, 1.83, 'cara_linense_7.jpg', 'fondo_linense_7.jpg', 7, 12, 'Defensa'),
(276, 'Antonio Romero', 'Romero', 86, 1.72, 'cara_linense_16.jpg', 'fondo_linense_16.jpg', 16, 12, 'Centrocampista'),
(277, 'José Masllorens', 'Monzó', 70, 1.83, 'cara_linense_6.jpg', 'fondo_linense_6.jpg', 6, 12, 'Centrocampista'),
(278, 'Alusine Koroma', 'Koroma', 70, 1.69, 'cara_linense_26.jpg', 'fondo_linense_26.jpg', 26, 12, 'Centrocampista'),
(279, 'Yassin Fekir', 'Fekir', 70, 1.7, 'cara_linense_12.jpg', 'fondo_linense_12.jpg', 12, 12, 'Defensa'),
(280, 'Alhassan Koroma', 'Alhassan ', 77, 1.82, 'cara_linense_10.jpg', 'fondo_linense_10.jpg', 10, 12, 'Delantero'),
(281, 'Joel de Pino', 'Joel', 82, 1.7, 'cara_linense_14.jpg', 'fondo_linense_14.jpg', 14, 12, 'Delantero'),
(282, 'Antonio García', 'Toni García', 64, 1.74, 'cara_linense_19.jpg', 'fondo_linense_19.jpg', 19, 12, 'Delantero'),
(283, 'João Pedro', 'João Pedro', 64, 1.7, 'cara_linense_21.jpg', 'fondo_linense_21.jpg', 21, 12, 'Delantero'),
(284, 'Omar Perdomo', 'Omar', 78, 1.78, 'cara_linense_22.jpg', 'fondo_linense_22.jpg', 22, 12, 'Delantero'),
(285, 'Alex Gutierrez', 'Alex Guti', 85, 1.75, 'cara_linense_18.jpg', 'fondo_linense_18.jpg', 18, 12, 'Delantero'),
(286, 'Robert Duncan', 'Bobby', 85, 1.76, 'cara_linense_8.jpg', 'fondo_linense_8.jpg', 8, 12, 'Delantero'),
(287, 'Gerard Oliva', 'Gerard', 85, 1.9, 'cara_linense_17.jpg', 'fondo_linense_17.jpg', 17, 12, 'Delantero'),
(288, 'Antonio Calderón Burgos', 'Antonio Calderón Burgos', 0, 0, 'cara_linense_0.jpg', 'fondo_linense_0.jpg', 0, 12, 'Entrenador'),
(289, 'Pol Tristán', 'Tristan', 85, 1.88, 'cara_algeciras_1.jpg', 'fondo_algeciras_1.jpg', 1, 13, 'Portero'),
(290, 'Juan Flere', 'Flere', 68, 1.82, 'cara_algeciras_13.jpg', 'fondo_algeciras_13.jpg', 13, 13, 'Portero'),
(291, 'Rubén Miño', 'Miño', 86, 1.91, 'cara_algeciras_24.jpg', 'fondo_algeciras_24.jpg', 24, 13, 'Portero'),
(292, 'Alejandro Benitez', 'Benitez', 55, 1.69, 'cara_algeciras_2.jpg', 'fondo_algeciras_2.jpg', 2, 13, 'Defensa'),
(293, 'Admonio Vicente', 'Admonio', 77, 1.78, 'cara_algeciras_3.jpg', 'fondo_algeciras_3.jpg', 3, 13, 'Defensa'),
(294, 'Carlos Albarrán', 'Albarrán', 74, 1.85, 'cara_algeciras_22.jpg', 'fondo_algeciras_22.jpg', 22, 13, 'Defensa'),
(295, '', 'Curro', 70, 1.69, 'cara_algeciras_26.jpg', 'fondo_algeciras_26.jpg', 26, 13, 'Defensa'),
(296, 'Isaac Amoah', 'Amoah', 78, 1.88, 'cara_algeciras_6.jpg', 'fondo_algeciras_6.jpg', 6, 13, 'Defensa'),
(297, 'Jordi Figueras', 'Figueras', 82, 1.85, 'cara_algeciras_21.jpg', 'fondo_algeciras_21.jpg', 21, 13, 'Defensa'),
(298, 'Nicolas Van Rijn', 'Nico', 78, 1.9, 'cara_algeciras_5.jpg', 'fondo_algeciras_5.jpg', 5, 13, 'Defensa'),
(299, 'Tomás Sánchez', 'Tomás', 66, 1.71, 'cara_algeciras_11.jpg', 'fondo_algeciras_11.jpg', 11, 13, 'Defensa'),
(300, 'Borja Fernández', 'Borja Fernández', 71, 1.77, 'cara_algeciras_4.jpg', 'fondo_algeciras_4.jpg', 4, 13, 'Centrocampista'),
(301, 'Iván Turrillo', 'Turrillo', 80, 1.83, 'cara_algeciras_8.jpg', 'fondo_algeciras_8.jpg', 8, 13, 'Centrocampista'),
(302, 'Juan Serrano', 'Serrano', 70, 1.77, 'cara_algeciras_20.jpg', 'fondo_algeciras_20.jpg', 20, 13, 'Centrocampista'),
(303, 'Mario Ortiz', 'Ortiz', 63, 1.75, 'cara_algeciras_19.jpg', 'fondo_algeciras_19.jpg', 19, 13, 'Centrocampista'),
(304, 'José Mena', 'Pepe Mena', 72, 1.75, 'cara_algeciras_23.jpg', 'fondo_algeciras_23.jpg', 23, 13, 'Centrocampista'),
(305, 'Álvaro Romero', 'Romero', 54, 1.67, 'cara_algeciras_10.jpg', 'fondo_algeciras_10.jpg', 10, 13, 'Delantero'),
(306, 'David Martín', 'Martín', 66, 1.74, 'cara_algeciras_18.jpg', 'fondo_algeciras_18.jpg', 18, 13, 'Delantero'),
(307, 'Rubén Fernández Serrano', 'Ferni', 72, 1.87, 'cara_algeciras_7.jpg', 'fondo_algeciras_7.jpg', 7, 13, 'Delantero'),
(308, 'Francisco Gallardo', 'Gallardo', 67, 1.77, 'cara_algeciras_27.jpg', 'fondo_algeciras_27.jpg', 27, 13, 'Delantero'),
(309, 'Iñaki Elejalde', 'Elejalde', 69, 1.74, 'cara_algeciras_16.jpg', 'fondo_algeciras_16.jpg', 16, 13, 'Delantero'),
(310, 'Ousama Siddiki', 'Siddiki', 79, 1.79, 'cara_algeciras_17.jpg', 'fondo_algeciras_17.jpg', 17, 13, 'Delantero'),
(311, '', 'Pimienta', 79, 1.79, 'cara_algeciras_28.jpg', 'fondo_algeciras_28.jpg', 28, 13, 'Delantero'),
(312, 'Rodrigo Sanz', 'Rodrigo Sanz', 67, 1.78, 'cara_algeciras_14.jpg', 'fondo_algeciras_14.jpg', 14, 13, 'Delantero'),
(313, 'David González Gómez', 'Roni', 76, 1.81, 'cara_algeciras_9.jpg', 'fondo_algeciras_9.jpg', 9, 13, 'Delantero'),
(314, 'Iván Ania', 'Iván Ania', 0, 0, 'cara_algeciras_0.jpg', 'fondo_algeciras_0.jpg', 0, 13, 'Entrenador'),
(315, 'José Perales', 'Perales', 85, 1.89, 'cara_sanfernando_1.jpg', 'fondo_sanfernando_1.jpg', 1, 14, 'Portero'),
(316, 'Changyi Li', 'Changyi', 85, 1.86, 'cara_sanfernando_13.jpg', 'fondo_sanfernando_13.jpg', 13, 14, 'Portero'),
(317, 'Sebastián Fassi', 'Fassi', 85, 1.9, 'cara_sanfernando_24.jpg', 'fondo_sanfernando_24.jpg', 24, 14, 'Portero'),
(318, 'Manuel Farrando', 'Farrando', 70, 1.83, 'cara_sanfernando_4.jpg', 'fondo_sanfernando_4.jpg', 4, 14, 'Defensa'),
(319, 'José Carlos Ramírez', 'José Carlos', 64, 1.82, 'cara_sanfernando_23.jpg', 'fondo_sanfernando_23.jpg', 23, 14, 'Defensa'),
(320, 'Sergio Ayala', 'Ayala', 75, 1.84, 'cara_sanfernando_5.jpg', 'fondo_sanfernando_5.jpg', 5, 14, 'Defensa'),
(321, 'Rafael Páez', 'Páez', 85, 1.92, 'cara_sanfernando_22.jpg', 'fondo_sanfernando_22.jpg', 22, 14, 'Defensa'),
(322, 'Luis Ruiz', 'Luis Ruiz', 70, 1.81, 'cara_sanfernando_3.jpg', 'fondo_sanfernando_3.jpg', 3, 14, 'Defensa'),
(323, 'Iñaki Recio', 'Recio', 64, 1.78, 'cara_sanfernando_17.jpg', 'fondo_sanfernando_17.jpg', 17, 14, 'Defensa'),
(324, 'Antonio Martín', 'Martín', 64, 1.78, 'cara_sanfernando_2.jpg', 'fondo_sanfernando_2.jpg', 2, 14, 'Defensa'),
(325, 'José Manuel Cabrera López', 'Lanchi', 85, 1.87, 'cara_sanfernando_18.jpg', 'fondo_sanfernando_18.jpg', 18, 14, 'Defensa'),
(326, 'David Ramos', 'Ramos', 70, 1.75, 'cara_sanfernando_12.jpg', 'fondo_sanfernando_12.jpg', 12, 14, 'Centrocampista'),
(327, 'Jon Ceberio', 'Ceberio', 64, 1.86, 'cara_sanfernando_21.jpg', 'fondo_sanfernando_21.jpg', 21, 14, 'Centrocampista'),
(328, 'Daniel Molina', 'Dani Molina', 78, 1.82, 'cara_sanfernando_6.jpg', 'fondo_sanfernando_6.jpg', 6, 14, 'Centrocampista'),
(329, 'Alfonso Martín', 'Alfonso', 80, 1.82, 'cara_sanfernando_8.jpg', 'fondo_sanfernando_8.jpg', 8, 14, 'Centrocampista'),
(330, 'Carlos Guevara', 'Guevara', 80, 1.82, 'cara_sanfernando_30.jpg', 'fondo_sanfernando_30.jpg', 30, 14, 'Centrocampista'),
(331, 'Javier Fernández Abruñedo', 'Bicho', 82, 1.75, 'cara_sanfernando_20.jpg', 'fondo_sanfernando_20.jpg', 20, 14, 'Centrocampista'),
(332, 'Juan Miguel Callejón', 'Juanmo', 86, 1.8, 'cara_sanfernando_15.jpg', 'fondo_sanfernando_15.jpg', 15, 14, 'Delantero'),
(333, 'Jonathan Biabiany', 'Jonathan', 76, 1.77, 'cara_sanfernando_7.jpg', 'fondo_sanfernando_7.jpg', 7, 14, 'Delantero'),
(334, 'Ilyas Chaira', 'Chaira', 82, 1.82, 'cara_sanfernando_14.jpg', 'fondo_sanfernando_14.jpg', 14, 14, 'Delantero'),
(335, 'Gabriel Martínez', 'Gabri', 82, 1.85, 'cara_sanfernando_16.jpg', 'fondo_sanfernando_16.jpg', 16, 14, 'Delantero'),
(336, 'Daniel Aquino', 'Aquino', 69, 1.78, 'cara_sanfernando_11.jpg', 'fondo_sanfernando_11.jpg', 11, 14, 'Delantero'),
(337, 'Rubén del Campo', 'Rubén', 80, 1.85, 'cara_sanfernando_19.jpg', 'fondo_sanfernando_19.jpg', 19, 14, 'Delantero'),
(338, 'Antonio Gabarre', 'Toni', 78, 1.86, 'cara_sanfernando_9.jpg', 'fondo_sanfernando_9.jpg', 9, 14, 'Delantero'),
(339, 'Pablo Alfaro', 'Pablo Alfaro', 0, 0, 'cara_sanfernando_0.jpg', 'fondo_sanfernando_0.jpg', 0, 14, 'Entrenador'),
(340, 'Kike Rollo', 'Kike Rollo', 76, 1.84, 'cara_badajoz_1.jpg', 'fondo_badajoz_1.jpg', 1, 15, 'Portero'),
(341, 'Miguel Narváez', 'Narváez', 85, 1.86, 'cara_badajoz_13.jpg', 'fondo_badajoz_13.jpg', 13, 15, 'Portero'),
(342, 'Mariano Gómez', 'Gómez', 85, 1.93, 'cara_badajoz_2.jpg', 'fondo_badajoz_2.jpg', 2, 15, 'Defensa'),
(343, 'Carlos Cordero', 'Cordero', 76, 1.81, 'cara_badajoz_3.jpg', 'fondo_badajoz_3.jpg', 3, 15, 'Defensa'),
(344, 'Borja García', 'Borja García', 80, 1.85, 'cara_badajoz_5.jpg', 'fondo_badajoz_5.jpg', 5, 15, 'Defensa'),
(345, 'M. Perez Acuña', 'Acuña', 72, 1.8, 'cara_badajoz_15.jpg', 'fondo_badajoz_15.jpg', 15, 15, 'Defensa'),
(346, 'José Mas', 'Mas', 71, 1.76, 'cara_badajoz_16.jpg', 'fondo_badajoz_16.jpg', 16, 15, 'Defensa'),
(347, 'José Malagón', 'Malagón', 80, 1.85, 'cara_badajoz_19.jpg', 'fondo_badajoz_19.jpg', 19, 15, 'Defensa'),
(348, 'Juanmi García', 'Juanmi', 82, 1.75, 'cara_badajoz_20.jpg', 'fondo_badajoz_20.jpg', 20, 15, 'Defensa'),
(349, 'Gianluca Mancuso', 'Mancuso', 68, 1.79, 'cara_badajoz_4.jpg', 'fondo_badajoz_4.jpg', 4, 15, 'Centrocampista'),
(350, 'Raúl Palma', 'Palma', 76, 1.86, 'cara_badajoz_6.jpg', 'fondo_badajoz_6.jpg', 6, 15, 'Centrocampista'),
(351, 'Jannick Buyla', 'Buyla', 80, 1.82, 'cara_badajoz_8.jpg', 'fondo_badajoz_8.jpg', 8, 15, 'Centrocampista'),
(352, 'Carlos Calderón', 'Calderón', 70, 1.83, 'cara_badajoz_7.jpg', 'fondo_badajoz_7.jpg', 7, 15, 'Delantero'),
(353, 'Francis Ferrón', 'Ferrón', 78, 1.84, 'cara_badajoz_9.jpg', 'fondo_badajoz_9.jpg', 9, 15, 'Delantero'),
(354, 'Jose Luis Zelu', 'Zelu', 78, 1.84, 'cara_badajoz_10.jpg', 'fondo_badajoz_10.jpg', 10, 15, 'Delantero'),
(355, 'Jesús Alfaro', 'Alfaro', 64, 1.7, 'cara_badajoz_11.jpg', 'fondo_badajoz_11.jpg', 11, 15, 'Delantero'),
(356, 'Gorka Santamaría', 'Gorka', 82, 1.82, 'cara_badajoz_14.jpg', 'fondo_badajoz_14.jpg', 14, 15, 'Delantero'),
(357, 'Adilson Mendes', 'Mendes', 64, 1.78, 'cara_badajoz_17.jpg', 'fondo_badajoz_17.jpg', 17, 15, 'Delantero'),
(358, 'David Soto', 'Soto', 65, 1.73, 'cara_badajoz_22.jpg', 'fondo_badajoz_22.jpg', 22, 15, 'Delantero'),
(359, 'José María Salmerón', 'Salmerón', 0, 0, 'cara_badajoz_0.jpg', 'fondo_badajoz_0.jpg', 0, 15, 'Entrenador'),
(360, 'Salva de la Cruz', 'Salva', 80, 1.87, 'cara_unionistas_1.jpg', 'fondo_unionistas_1.jpg', 1, 16, 'Portero'),
(361, 'Alberto Sánchez', 'Alberto Sánchez', 78, 1.8, 'cara_unionistas_13.jpg', 'fondo_unionistas_13.jpg', 13, 16, 'Portero'),
(362, 'Ramiro Mayor', 'Ramiro', 76, 1.85, 'cara_unionistas_4.jpg', 'fondo_unionistas_4.jpg', 4, 16, 'Defensa'),
(363, 'Alfredo Pedraza', 'Pedraza', 71, 1.81, 'cara_unionistas_21.jpg', 'fondo_unionistas_21.jpg', 21, 16, 'Defensa'),
(364, 'Antonio Leal', 'Antonio Leal', 78, 1.86, 'cara_unionistas_18.jpg', 'fondo_unionistas_18.jpg', 18, 16, 'Defensa'),
(365, 'Nabil Aberdin', 'Aberdin', 65, 1.73, 'cara_unionistas_22.jpg', 'fondo_unionistas_22.jpg', 22, 16, 'Defensa'),
(366, 'Jon Rojo', 'Rojo', 76, 1.8, 'cara_unionistas_3.jpg', 'fondo_unionistas_3.jpg', 3, 16, 'Defensa'),
(367, 'Marcos Bravo', 'Bravo', 75, 1.8, 'cara_unionistas_23.jpg', 'fondo_unionistas_23.jpg', 23, 16, 'Defensa'),
(368, 'Fran Rodriguez', 'F. Rodriguez', 60, 1.75, 'cara_unionistas_2.jpg', 'fondo_unionistas_2.jpg', 2, 16, 'Defensa'),
(369, 'Ramón Blázquez', 'Blázquez', 64, 1.7, 'cara_unionistas_17.jpg', 'fondo_unionistas_17.jpg', 17, 16, 'Defensa'),
(370, 'Héctor Nespral', 'Héctor', 80, 1.79, 'cara_unionistas_8.jpg', 'fondo_unionistas_8.jpg', 8, 16, 'Centrocampista'),
(371, 'Raúl Beneit', 'Benéit', 82, 1.75, 'cara_unionistas_20.jpg', 'fondo_unionistas_20.jpg', 20, 16, 'Centrocampista'),
(372, 'Unai Veiga', 'Unai', 83, 1.89, 'cara_unionistas_5.jpg', 'fondo_unionistas_5.jpg', 5, 16, 'Centrocampista'),
(373, 'Óscar Sanz', 'Palma', 76, 1.86, 'cara_unionistas_6.jpg', 'fondo_unionistas_6.jpg', 6, 16, 'Centrocampista'),
(374, 'Neskes', 'Neskes', 76, 1.86, 'cara_unionistas_30.jpg', 'fondo_unionistas_30.jpg', 30, 16, 'Centrocampista'),
(375, 'Borja Díaz', 'Borja Díaz', 74, 1.8, 'cara_unionistas_14.jpg', 'fondo_unionistas_14.jpg', 14, 16, 'Centrocampista'),
(376, 'Juanpa Barros', 'Barros', 69, 1.74, 'cara_unionistas_11.jpg', 'fondo_unionistas_11.jpg', 11, 16, 'Delantero'),
(377, 'Ivan Chapela', 'Chapela', 78, 1.81, 'cara_unionistas_19.jpg', 'fondo_unionistas_19.jpg', 19, 16, 'Delantero'),
(378, 'June-hyuk Ahn', 'June-hyuk', 65, 1.73, 'cara_unionistas_16.jpg', 'fondo_unionistas_16.jpg', 16, 16, 'Delantero'),
(379, 'Christian Santos', 'Christian', 78, 1.84, 'cara_unionistas_9.jpg', 'fondo_unionistas_9.jpg', 9, 16, 'Delantero'),
(380, 'Mario Losada', 'Losada', 68, 1.75, 'cara_unionistas_7.jpg', 'fondo_unionistas_7.jpg', 7, 16, 'Delantero'),
(381, 'Carlos de la Nava', 'CDLN', 81, 1.87, 'cara_unionistas_10.jpg', 'fondo_unionistas_10.jpg', 10, 16, 'Delantero'),
(382, 'Dani Ponz', 'Dani Ponz', 0, 0, 'cara_unionistas_0.jpg', 'fondo_unionistas_0.jpg', 0, 16, 'Entrenador'),
(383, 'Pol Freixanet Viejo', 'Pol', 83, 1.93, 'cara_fuenlabrada_1.jpg', 'fondo_fuenlabrada_1.jpg', 1, 17, 'Portero'),
(384, 'Álex Craninx', 'Craninx', 78, 1.8, 'cara_fuenlabrada_13.jpg', 'fondo_fuenlabrada_13.jpg', 13, 17, 'Portero'),
(385, 'Alejandro Sotillos Miarnau', 'Sotillos', 76, 1.8, 'cara_fuenlabrada_4.jpg', 'fondo_fuenlabrada_4.jpg', 4, 17, 'Defensa'),
(386, 'Alvaro Barbosas', 'Barbosa', 74, 1.8, 'cara_fuenlabrada_14.jpg', 'fondo_fuenlabrada_14.jpg', 14, 17, 'Defensa'),
(387, 'Manuel Lama Maroto', 'Manu', 74, 1.8, 'cara_fuenlabrada_15.jpg', 'fondo_fuenlabrada_15.jpg', 15, 17, 'Defensa'),
(388, 'Mikel Iribas Aliende', 'Iribas', 78, 1.84, 'cara_fuenlabrada_19.jpg', 'fondo_fuenlabrada_19.jpg', 19, 17, 'Defensa'),
(389, 'Sergio Cubero Ezcurra', 'Cubero ', 82, 1.75, 'cara_fuenlabrada_20.jpg', 'fondo_fuenlabrada_20.jpg', 20, 17, 'Centrocampista'),
(390, 'Aleix Coch Lucena', 'Aleix Coch', 65, 1.73, 'cara_fuenlabrada_22.jpg', 'fondo_fuenlabrada_22.jpg', 22, 17, 'Defensa'),
(391, 'Álvaro Garcia', 'Coke', 65, 1.73, 'cara_fuenlabrada_32.jpg', 'fondo_fuenlabrada_32.jpg', 32, 17, 'Defensa'),
(392, 'Diego Aguirre Parra', 'Diego Aguirre', 76, 1.8, 'cara_fuenlabrada_3.jpg', 'fondo_fuenlabrada_3.jpg', 3, 17, 'Centrocampista'),
(393, 'Iban Salvador Edu', 'Iban Salvador', 76, 1.7, 'cara_fuenlabrada_6.jpg', 'fondo_fuenlabrada_6.jpg', 6, 17, 'Centrocampista'),
(394, 'Enzo Zidane', 'Enzo', 68, 1.75, 'cara_fuenlabrada_7.jpg', 'fondo_fuenlabrada_7.jpg', 7, 17, 'Centrocampista'),
(395, 'Cristobal Márquez', 'Cristobal', 77, 1.81, 'cara_fuenlabrada_8.jpg', 'fondo_fuenlabrada_8.jpg', 8, 17, 'Centrocampista'),
(396, 'Álvaro Bravo', 'Bravo', 81, 1.87, 'cara_fuenlabrada_10.jpg', 'fondo_fuenlabrada_10.jpg', 10, 17, 'Centrocampista'),
(397, 'Fernando Ruiz Izaguirre', 'Fer Ruiz', 76, 1.83, 'cara_fuenlabrada_17.jpg', 'fondo_fuenlabrada_17.jpg', 17, 17, 'Centrocampista'),
(398, 'Ramón Bueno', 'Bueno', 78, 1.86, 'cara_fuenlabrada_18.jpg', 'fondo_fuenlabrada_18.jpg', 18, 17, 'Centrocampista'),
(399, 'Stephen Buer', 'Buer', 71, 1.81, 'cara_fuenlabrada_21.jpg', 'fondo_fuenlabrada_21.jpg', 21, 17, 'Centrocampista'),
(400, 'Diego García', 'Diego García', 78, 1.84, 'cara_fuenlabrada_9.jpg', 'fondo_fuenlabrada_9.jpg', 9, 17, 'Delantero'),
(401, 'Santi Jara', 'S.Jara', 69, 1.74, 'cara_fuenlabrada_11.jpg', 'fondo_fuenlabrada_11.jpg', 11, 17, 'Delantero'),
(402, 'David Vilán', 'Vilán', 65, 1.73, 'cara_fuenlabrada_16.jpg', 'fondo_fuenlabrada_16.jpg', 16, 17, 'Delantero'),
(403, 'David Amigo', 'Amigo', 65, 1.73, 'cara_fuenlabrada_27.jpg', 'fondo_fuenlabrada_27.jpg', 27, 17, 'Delantero'),
(404, 'Alfredo', 'Alfredo', 0, 0, 'cara_fuenlabrada_0.jpg', 'fondo_fuenlabrada_0.jpg', 0, 17, 'Entrenador'),
(405, 'Tomás Mejias', 'Mejias', 83, 1.95, 'cara_ceuta_1.jpg', 'fondo_ceuta_1.jpg', 1, 18, 'Portero'),
(406, 'Jesus Romero', 'Jesus', 78, 1.86, 'cara_ceuta_13.jpg', 'fondo_ceuta_13.jpg', 13, 18, 'Portero'),
(407, 'Lolo Cortés', 'Lolo', 78, 1.82, 'cara_ceuta_24.jpg', 'fondo_ceuta_24.jpg', 24, 18, 'Portero'),
(408, 'Robin Lafarge', 'Lafarge', 65, 1.87, 'cara_ceuta_22.jpg', 'fondo_ceuta_22.jpg', 22, 18, 'Defensa'),
(409, 'Albert Caparros', 'Caparros', 76, 1.84, 'cara_ceuta_4.jpg', 'fondo_ceuta_4.jpg', 4, 18, 'Defensa'),
(410, 'Juan Gutierrez', 'Gutierrez', 76, 1.85, 'cara_ceuta_12.jpg', 'fondo_ceuta_12.jpg', 12, 18, 'Defensa'),
(411, 'Fabrizio Danese', 'Danese', 82, 1.87, 'cara_ceuta_20.jpg', 'fondo_ceuta_20.jpg', 20, 18, 'Defensa'),
(412, 'Mauro Cerqueira', 'Mauro', 76, 1.78, 'cara_ceuta_5.jpg', 'fondo_ceuta_5.jpg', 5, 18, 'Defensa'),
(413, 'David Alfonso', 'D.Alfonso', 78, 1.67, 'cara_ceuta_18.jpg', 'fondo_ceuta_18.jpg', 18, 18, 'Defensa'),
(414, 'Alex Macias', 'A.Macias', 78, 1.67, 'cara_ceuta_29.jpg', 'fondo_ceuta_29.jpg', 29, 18, 'Defensa'),
(415, 'Carlos Barreda', 'Carlos', 78, 1.77, 'cara_ceuta_2.jpg', 'fondo_ceuta_2.jpg', 2, 18, 'Defensa'),
(416, 'Alaín García', 'Alaín', 74, 1.75, 'cara_ceuta_15.jpg', 'fondo_ceuta_15.jpg', 15, 18, 'Defensa'),
(417, 'Alberto Reina', 'Reina', 76, 1.68, 'cara_ceuta_6.jpg', 'fondo_ceuta_6.jpg', 6, 18, 'Centrocampista'),
(418, 'Ransford Selasi', 'Ransford', 76, 1.78, 'cara_ceuta_3.jpg', 'fondo_ceuta_3.jpg', 3, 18, 'Centrocampista'),
(419, 'Adri Cuevas', 'Adri Cuevas', 76, 1.87, 'cara_ceuta_17.jpg', 'fondo_ceuta_17.jpg', 17, 18, 'Centrocampista'),
(420, 'Jota Lopez', 'Jota Lopez', 74, 1.8, 'cara_ceuta_14.jpg', 'fondo_ceuta_14.jpg', 14, 18, 'Centocampista'),
(421, 'Julio Iglesias', 'Julio Iglesias', 74, 1.8, 'cara_ceuta_8.jpg', 'fondo_ceuta_8.jpg', 8, 18, 'Centocampista'),
(422, 'Samuel Casais', 'Casais', 71, 1.81, 'cara_ceuta_21.jpg', 'fondo_ceuta_21.jpg', 21, 18, 'Centrocampista'),
(423, 'Ñito Gonzalez', 'Ñito', 71, 1.69, 'cara_ceuta_23.jpg', 'fondo_ceuta_23.jpg', 23, 18, 'Centrocampista'),
(424, 'Aisar Ahmed', 'Ahmed', 68, 1.75, 'cara_ceuta_7.jpg', 'fondo_ceuta_7.jpg', 7, 18, 'Centrocampista'),
(425, 'Luismi Redondo', 'Redondo', 69, 1.74, 'cara_ceuta_11.jpg', 'fondo_ceuta_11.jpg', 11, 18, 'Delantero'),
(426, 'Alberto Gil', 'Alberto Gil', 78, 1.78, 'cara_ceuta_19.jpg', 'fondo_ceuta_19.jpg', 19, 18, 'Delantero'),
(427, 'Rodrigo Rios', 'Rodri Rios', 78, 1.74, 'cara_ceuta_9.jpg', 'fondo_ceuta_9.jpg', 9, 18, 'Delantero'),
(428, 'Pablo Garcia', 'García', 65, 1.73, 'cara_ceuta_16.jpg', 'fondo_ceuta_16.jpg', 16, 18, 'Delantero'),
(429, 'Mohammed Mizzian', 'Moha', 81, 1.85, 'cara_ceuta_10.jpg', 'fondo_ceuta_10.jpg', 10, 18, 'Centrocampista'),
(430, 'José Juan Romero', 'José Juan Romero', 0, 0, 'cara_ceuta_0.jpg', 'fondo_ceuta_0.jpg', 0, 18, 'Entrenador'),
(431, 'Javier Rabanillo', 'Rabanillo', 86, 1.86, 'cara_talavera_1.jpg', 'fondo_talavera_1.jpg', 1, 19, 'Portero'),
(432, 'Biel Rivas', 'Biel Rivas', 78, 1.86, 'cara_talavera_24.jpg', 'fondo_talavera_24.jpg', 24, 19, 'Portero'),
(433, 'Neyder Lozano', 'Neyder', 78, 1.85, 'cara_talavera_18.jpg', 'fondo_talavera_18.jpg', 18, 19, 'Defensa'),
(434, 'Daniel Ramos', 'Dani Ramos', 76, 1.85, 'cara_talavera_4.jpg', 'fondo_talavera_4.jpg', 4, 19, 'Defensa'),
(435, 'Francisco Delorenzi', 'Delorenzi', 76, 1.85, 'cara_talavera_5.jpg', 'fondo_talavera_5.jpg', 5, 19, 'Defensa'),
(436, 'David Morante', 'Morante', 65, 1.87, 'cara_talavera_22.jpg', 'fondo_talavera_22.jpg', 22, 19, 'Defensa'),
(437, 'Miguel Angel Brau', 'Brau', 76, 1.83, 'cara_talavera_3.jpg', 'fondo_talavera_3.jpg', 3, 19, 'Defensa'),
(438, 'Lucas Alcázar', 'Alcázar', 71, 1.82, 'cara_talavera_23.jpg', 'fondo_talavera_23.jpg', 23, 19, 'Defensa'),
(439, 'Carlos Parra', 'Parra', 78, 1.68, 'cara_talavera_2.jpg', 'fondo_talavera_2.jpg', 2, 19, 'Defensa'),
(440, 'Unai Rementería', 'Unai', 76, 1.83, 'cara_talavera_6.jpg', 'fondo_talavera_6.jpg', 6, 19, 'Centrocampista'),
(441, 'Francisco Reguera', 'Fran', 74, 1.75, 'cara_talavera_15.jpg', 'fondo_talavera_15.jpg', 15, 19, 'Centrcampista'),
(442, 'Javier Bueno', 'Javi Bueno', 74, 1.8, 'cara_talavera_14.jpg', 'fondo_talavera_14.jpg', 14, 19, 'Centocampista'),
(443, 'Lass Sangaré', 'Lass', 74, 1.83, 'cara_talavera_8.jpg', 'fondo_talavera_8.jpg', 8, 19, 'Centocampista'),
(444, 'Renan Zanelli', 'Zanelli', 81, 1.8, 'cara_talavera_10.jpg', 'fondo_talavera_10.jpg', 10, 19, 'Centrocampista'),
(445, 'Edu Gallardo', 'Edu', 69, 1.7, 'cara_talavera_11.jpg', 'fondo_talavera_11.jpg', 11, 19, 'Delantero'),
(446, 'Dennis Salanovic', 'Salanovic', 78, 1.79, 'cara_talavera_19.jpg', 'fondo_talavera_19.jpg', 19, 19, 'Delantero'),
(447, 'Alvaro Juan', 'Alvaro Juan', 68, 1.78, 'cara_talavera_7.jpg', 'fondo_talavera_7.jpg', 7, 19, 'Delantero');
INSERT INTO `jugador` (`id`, `nombre`, `apodo`, `peso`, `altura`, `foto`, `fondo`, `dorsal`, `id_equipo`, `posicion`) VALUES
(448, 'Rodrigo Escudero', 'Escudero', 71, 1.81, 'cara_talavera_21.jpg', 'fondo_talavera_21.jpg', 21, 19, 'Delantero'),
(449, 'Sergio Buenacasa', 'Sergio', 78, 1.82, 'cara_talavera_9.jpg', 'fondo_talavera_9.jpg', 9, 19, 'Delantero'),
(450, ' Souleymane Faye', 'Faye', 76, 1.79, 'cara_talavera_17.jpg', 'fondo_talavera_17.jpg', 17, 19, 'Delantero'),
(451, 'Bashiru Mohammed', 'Moha', 82, 1.87, 'cara_talavera_20.jpg', 'fondo_talavera_20.jpg', 20, 19, 'Delantero'),
(452, 'Rubén Gala', 'Rubén Gala', 0, 0, 'cara_talavera_0.jpg', 'fondo_talavera_0.jpg', 0, 19, 'Entrenador'),
(453, 'Álvaro Cortés', 'Cortés', 86, 1.85, 'cara_pontevedra_1.jpg', 'fondo_pontevedra_1.jpg', 1, 20, 'Portero'),
(454, 'Pablo Cacharrón', 'Cacharrón', 86, 1.86, 'cara_pontevedra_13.jpg', 'fondo_pontevedra_13.jpg', 13, 20, 'Portero'),
(455, 'Viktor Nikolov', 'Nikolov', 78, 1.91, 'cara_pontevedra_24.jpg', 'fondo_pontevedra_24.jpg', 24, 20, 'Portero'),
(456, 'Víctor Vázquez', 'V.V.', 76, 1.79, 'cara_pontevedra_4.jpg', 'fondo_pontevedra_4.jpg', 4, 20, 'Defensa'),
(457, 'David Soto', 'David Soto', 76, 1.81, 'cara_pontevedra_6.jpg', 'fondo_pontevedra_6.jpg', 6, 20, 'Defensa'),
(458, 'Luis Martínez', 'Luis M.', 65, 1.89, 'cara_pontevedra_22.jpg', 'fondo_pontevedra_22.jpg', 22, 20, 'Defensa'),
(459, 'Derik Osede', 'Osede', 76, 1.84, 'cara_pontevedra_17.jpg', 'fondo_pontevedra_17.jpg', 17, 20, 'Defensa'),
(460, 'Samu Araujo', 'Araujo', 76, 1.8, 'cara_pontevedra_3.jpg', 'fondo_pontevedra_3.jpg', 3, 20, 'Defensa'),
(461, 'Ángel Bastos', 'Bastos', 78, 1.69, 'cara_pontevedra_2.jpg', 'fondo_pontevedra_2.jpg', 2, 20, 'Defensa'),
(462, 'Diego Seoane', 'Seoane', 71, 1.81, 'cara_pontevedra_21.jpg', 'fondo_pontevedra_21.jpg', 21, 20, 'Defensa'),
(463, 'Miguel Román', 'Román', 76, 1.78, 'cara_pontevedra_5.jpg', 'fondo_pontevedra_5.jpg', 5, 20, 'Centrcampista'),
(464, 'Yelco Pino', 'Yelco', 81, 1.67, 'cara_pontevedra_10.jpg', 'fondo_pontevedra_10.jpg', 10, 20, 'Centrocampista'),
(465, 'Borja Domínguez', 'Domínguez', 82, 1.87, 'cara_pontevedra_20.jpg', 'fondo_pontevedra_20.jpg', 20, 20, 'Centrocampista'),
(466, 'Javi Robles', 'Javi Robles', 71, 1.9, 'cara_pontevedra_23.jpg', 'fondo_pontevedra_23.jpg', 23, 20, 'Centrocampista'),
(467, 'Alex Masogo', 'Masogo', 71, 1.83, 'cara_pontevedra_16.jpg', 'fondo_pontevedra_16.jpg', 16, 20, 'Centrocampista'),
(468, 'Jon Bakero', 'Jon', 78, 1.89, 'cara_pontevedra_19.jpg', 'fondo_pontevedra_19.jpg', 19, 20, 'Centrocampista'),
(469, 'Gonzalo Bueno', 'Bueno', 69, 1.76, 'cara_pontevedra_11.jpg', 'fondo_pontevedra_11.jpg', 11, 20, 'Delantero'),
(470, 'Alex Gonzalez', 'González', 68, 1.78, 'cara_pontevedra_7.jpg', 'fondo_pontevedra_7.jpg', 7, 20, 'Delantero'),
(471, 'Alberto Rubio', 'Beto', 74, 1.74, 'cara_pontevedra_14.jpg', 'fondo_pontevedra_14.jpg', 14, 20, 'Delantero'),
(472, 'Libasse Gueye', 'Gueye', 74, 1.73, 'cara_pontevedra_12.jpg', 'fondo_pontevedra_12.jpg', 12, 20, 'Delantero'),
(473, 'Brais Abelenda', 'Brais', 76, 1.79, 'cara_pontevedra_8.jpg', 'fondo_pontevedra_8.jpg', 8, 20, 'Delantero'),
(474, 'Rufo Sánchez', 'Rufo', 78, 1.82, 'cara_pontevedra_9.jpg', 'fondo_pontevedra_9.jpg', 9, 20, 'Delantero'),
(475, 'Charles', 'Charles', 78, 1.79, 'cara_pontevedra_18.jpg', 'fondo_pontevedra_18.jpg', 18, 20, 'Delantero'),
(476, 'Martín Diz', 'Martín Diz', 74, 1.75, 'cara_pontevedra_15.jpg', 'fondo_pontevedra_15.jpg', 15, 20, 'Centrocampista'),
(477, 'Juan Señor', 'Juan Señor', 0, 0, 'cara_pontevedra_0.jpg', 'fondo_pontevedra_0.jpg', 0, 20, 'Entrenador');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `liga`
--

CREATE TABLE `liga` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `campeon` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `liga`
--

INSERT INTO `liga` (`id`, `nombre`, `campeon`) VALUES
(1, 'Primera RFEF temporada 2022/23', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `noticia`
--

CREATE TABLE `noticia` (
  `id` int(11) NOT NULL,
  `titular` varchar(150) DEFAULT NULL,
  `cuerpo` text DEFAULT NULL,
  `foto` varchar(40) DEFAULT NULL,
  `id_periodista` int(11) DEFAULT NULL,
  `fecha` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `noticia`
--

INSERT INTO `noticia` (`id`, `titular`, `cuerpo`, `foto`, `id_periodista`, `fecha`) VALUES
(1, 'Cordoba en puestos de ascenso', 'El técnico azulillo acusa a la mala racha blanquiverde de la falta de \"eficacia\" y asegura que \"volverá a recuperar sus sensaciones cuando encuentre el gol\". El Córdoba CF regresa a Linarejos con un mal recuerdo. El divorcio del Córdoba CF con el gol a domicilio, un objetivo a recuperar en Linares. .', 'noticia1.jpg', 1, '2022-12-12'),
(2, 'Tension en el derbi gallego de primera RFEF', 'Además, el encuentro se ha convertido en el tercero con más público en toda la historia de la competición, siendo el partido con más asistencia la final del playoff que disputó el conjunto blanquiazul', 'noticia2.jpg', 4, '2022-12-01'),
(6, 'Cristiano se retira', 'A la estrella portuguesa no le gusta perder y entiende que va a perder mucho con su actual equipo. Y a estas alturas de su carrera no está para echar por tierra su prestigio sin éxitos en el último tramo de su trayectoria. Cristiano Ronaldo está dispuesto a renunciar a los 200 millones al año que tiene firmados para retirarse de forma definitiva a sus 38 años y dedicarse por completo a su familia alternando su residencia entre Madeira y Madrid. ¿Regreso al Real Madrid? Cuando llegue el momento, Cristiano Ronaldo contactará con Florentino Pérez para ofrecerle sus servicios como embajador del Real Madrid en el mundo. Nada haría más feliz al fenómeno portugués que seguir vinculado al club que más gloria le ha proporcionado en su carrera y en el que se ha sentido más cómodo.Y Florentino Pérez está esperando el momento para decirle que sí. La marca de Cristiano Ronaldo vinculada al Real Madrid puede ofrecer mucho rendimiento en los próximos años. Tanto desde el punto de vista de imagen institucional como económicamente.', 'cristiano_se_retira.jpg', 3, '2023-04-28');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `partido`
--

CREATE TABLE `partido` (
  `id` int(11) NOT NULL,
  `goles_local` int(11) DEFAULT NULL,
  `goles_visitante` int(11) DEFAULT NULL,
  `posesion_local` int(11) DEFAULT NULL,
  `posesion_visitante` int(11) DEFAULT NULL,
  `pases_local` int(11) DEFAULT NULL,
  `pases_visitante` int(11) DEFAULT NULL,
  `tiros_local` int(11) DEFAULT NULL,
  `tiros_visitante` int(11) DEFAULT NULL,
  `corners_local` int(11) DEFAULT NULL,
  `corners_visitante` int(11) DEFAULT NULL,
  `faltas_local` int(11) DEFAULT NULL,
  `faltas_visitante` int(11) DEFAULT NULL,
  `id_equipo_local` int(11) DEFAULT NULL,
  `id_equipo_visitante` int(11) DEFAULT NULL,
  `id_liga` int(11) DEFAULT NULL,
  `id_creador` int(11) DEFAULT NULL,
  `terminado` tinyint(1) DEFAULT 0,
  `id_arbitro` int(11) DEFAULT NULL,
  `Fecha` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `partido`
--

INSERT INTO `partido` (`id`, `goles_local`, `goles_visitante`, `posesion_local`, `posesion_visitante`, `pases_local`, `pases_visitante`, `tiros_local`, `tiros_visitante`, `corners_local`, `corners_visitante`, `faltas_local`, `faltas_visitante`, `id_equipo_local`, `id_equipo_visitante`, `id_liga`, `id_creador`, `terminado`, `id_arbitro`, `Fecha`) VALUES
(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 18, 1, 1, 1, 8, '2023-05-24'),
(2, 2, 2, 44, 56, 1, 0, 0, 0, 0, 0, 0, 0, 2, 4, 1, 1, 1, 10, '2023-05-24'),
(3, 5, 2, 50, 50, 0, 0, 0, 0, 0, 0, 0, 0, 2, 4, 1, 1, 1, 7, '2023-05-24'),
(4, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 15, 2, 1, 1, 0, 6, '2023-05-24'),
(9, 0, 0, 50, 50, 0, 0, 0, 0, 0, 0, 0, 0, 17, 12, 1, 1, 0, 11, '2023-05-24'),
(10, 0, 0, 50, 50, 0, 0, 0, 0, 0, 0, 0, 0, 17, 12, 1, 1, 0, 8, '2023-05-24'),
(102, 3, 0, 8, 92, 0, 0, 3, 0, 0, 0, 0, 0, 6, 2, 1, 15, 0, 5, '2023-05-24'),
(103, 3, 0, 75, 25, 2, 2, 4, 1, 1, 2, 1, 1, 7, 5, 1, 15, 0, 3, '2023-05-24'),
(104, 2, 1, 42, 58, 1, 1, 3, 2, 0, 1, 0, 0, 7, 8, 1, 15, 1, 13, '2023-05-24'),
(105, 1, 1, 52, 48, 1, 1, 2, 2, 1, 1, 0, 0, 13, 7, 1, 15, 0, 7, '2023-05-24'),
(106, 1, 0, 22, 78, 1, 1, 2, 1, 1, 2, 1, 1, 9, 10, 1, 15, 0, 3, '2023-05-24'),
(107, 1, 0, 28, 72, 0, 1, 2, 1, 0, 0, 0, 0, 3, 13, 1, 15, 0, 13, '2023-05-24'),
(108, 1, 0, 45, 55, 1, 1, 2, 1, 1, 1, 0, 0, 12, 13, 1, 15, 0, 7, '2023-05-24'),
(109, 1, 0, 53, 47, 0, 0, 1, 0, 0, 0, 0, 0, 16, 17, 1, 15, 1, 5, '2023-05-24'),
(110, 1, 0, 33, 67, 0, 0, 1, 0, 0, 0, 0, 0, 7, 10, 1, 15, 0, 1, '2023-05-24'),
(111, 0, 0, 32, 68, 0, 1, 0, 2, 0, 1, 0, 2, 6, 9, 1, 15, 0, 2, '2023-05-24'),
(112, 1, 0, 37, 63, 0, 0, 1, 0, 0, 0, 0, 0, 8, 9, 1, 15, 0, 11, '2023-05-24'),
(113, 0, 0, -1, 101, 0, 0, 0, 0, 0, 0, 0, 0, 17, 15, 1, 15, 0, 9, '2023-05-24'),
(114, 0, 1, 49, 51, 0, 0, 0, 1, 0, 0, 0, 0, 7, 4, 1, 15, 1, 6, '2023-05-24'),
(115, 0, 1, 47, 53, 356, 420, 1, 4, 3, 0, 0, 0, 8, 12, 1, 15, 1, 7, '2023-05-24'),
(116, 1, 1, 45, 55, 0, 0, 1, 1, 0, 0, 0, 0, 3, 9, 1, 15, 1, 11, '2023-05-24'),
(117, 1, 0, 52, 48, 7, 10, 3, 2, 2, 3, 1, 0, 15, 9, 1, 15, 1, 7, '2023-05-24'),
(118, 1, 1, 50, 50, 2, 1, 2, 2, 1, 1, 0, 0, 1, 11, 1, 15, 1, 11, '2023-05-24'),
(119, 0, 1, 47, 53, 0, 0, 0, 1, 0, 0, 0, 0, 5, 9, 1, 15, 1, 9, '2023-05-24');

--
-- Disparadores `partido`
--
DELIMITER $$
CREATE TRIGGER `actualizarcuali` AFTER UPDATE ON `partido` FOR EACH ROW BEGIN
	IF NEW.terminado <> OLD.terminado THEN
			IF OLD.goles_local > OLD.goles_visitante THEN
				UPDATE clasificacion SET puntos = puntos + 3 where id_equipo = OLD.id_equipo_local;
			ELSEIF OLD.goles_local < OLD.goles_visitante THEN
				UPDATE clasificacion SET puntos = puntos + 3 where id_equipo = OLD.id_equipo_visitante;
			ELSE
				UPDATE clasificacion SET puntos = puntos + 1 where id_equipo = OLD.id_equipo_local;
				UPDATE clasificacion SET puntos = puntos + 1 where id_equipo = OLD.id_equipo_visitante;
			END IF;
	END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarjeta`
--

CREATE TABLE `tarjeta` (
  `id` int(11) NOT NULL,
  `minuto` int(11) DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,
  `id_jugador` int(11) DEFAULT NULL,
  `id_partido` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tarjeta`
--

INSERT INTO `tarjeta` (`id`, `minuto`, `color`, `id_jugador`, `id_partido`) VALUES
(1, 25, 'amarilla', 77, 3),
(2, 32, 'amarilla', 77, 3),
(3, 32, 'roja', 77, 3),
(4, 87, 'amarilla', 231, 4),
(5, 0, 'Amarilla', 270, 115),
(6, 0, 'Roja', 273, 115),
(7, 0, 'Amarilla', 195, 115),
(8, 0, 'Amarilla', 347, 117),
(9, 1, 'Roja', 211, 117),
(10, 0, 'Amarilla', 5, 118);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `nombre` varchar(20) COLLATE latin1_spanish_ci DEFAULT NULL,
  `apellidos` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  `foto` varchar(40) COLLATE latin1_spanish_ci DEFAULT NULL,
  `fondo` varchar(40) COLLATE latin1_spanish_ci DEFAULT NULL,
  `rol` varchar(15) COLLATE latin1_spanish_ci DEFAULT NULL,
  `id_equipo_fav` int(11) DEFAULT NULL,
  `id_jugador_fav` int(11) DEFAULT NULL,
  `passwd` varchar(300) COLLATE latin1_spanish_ci NOT NULL,
  `email` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `fechanac` varchar(50) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombre`, `apellidos`, `foto`, `fondo`, `rol`, `id_equipo_fav`, `id_jugador_fav`, `passwd`, `email`, `fechanac`) VALUES
(1, 'javier', 'cereceda', 'foto_usuario_nueva.jpg', 'fondo_user_1', 'creador', NULL, 20, '1234', 'javier@emai.com', ''),
(3, 'ejemplo', 'perez perez', NULL, NULL, 'periodista', NULL, NULL, '$2a$08$SBY5rdhugglQsPif.n6L6.e6lk.JJh57pK8HVuc4gJy9K5QVQpgOi', 'emailejemplo', ''),
(4, 'carlos', 'asdf asdf', NULL, NULL, 'Comun', NULL, NULL, '$2a$08$vqDfeerdIb/C4FfJB4XVheEQYhh9PYYRJYX1VM9U651/kYK9XKHm2', 'email', ''),
(11, 'javier', 'cereceda', NULL, NULL, 'Comun', NULL, NULL, '$2a$08$z5MlqPu2gXL5bIdPJu6CzejNNWgeqjCPBAzAb5Ojr56bI9ZRMOf0i', 'javier@email.com', ''),
(13, 'paco', 'pancetas', NULL, NULL, 'Comun', NULL, NULL, '$2a$08$/OD1Wo/YlCzWBQiX1oIZF.9H3I7YxY267B4tiLYpzg7MUfbbWP.32', 'paco@email.com', ''),
(15, 'iker', 'casillas', 'foto.jpg', NULL, 'creador', 1, 5, '$2a$08$sbZjWvJcXGkOUHSr3yt0T.Td6IO1wcNB3M0p0TUhT8l.JTMzytNIy', 'iker@email.com', '2023-03-09 00:00:00'),
(22, 'carlos', 'asdf asdf', NULL, NULL, 'Comun', NULL, NULL, '$2a$08$cmD4q/lyZkeVOodzO5cfN.i558K.ImGluIg7eWVsfFzCBg5WW5Mj2', 'emailejemplo', '2000-01-01 23:00:00'),
(23, 'carlos', 'asdf asdf', NULL, NULL, 'Comun', NULL, NULL, '$2a$08$vFRhNS7yUfpUvGestX8oNOJbnYhxgF4zTIdkb1Ip0TvlJgR3TmPdW', 'emailejemplso', '2000-01-01 23:00:00'),
(24, 'peoe', 'perez', NULL, NULL, 'Comun', NULL, NULL, '$2a$08$/JzABoZRfOu59TTpD2EyoeV3yBa9Bm9ft3U5AoAr6No8heRe/QnZC', 'pepe@hola.c', '2014-04-08 00:00:00'),
(25, 'pepe', 'perez', NULL, NULL, 'Comun', NULL, NULL, '$2a$08$qU/GrfaTV2BdmWDO0hHuz.k3Xqa/7Rre2/qrozquSO0JLDrXPC58y', 'pepe@hola.com', '2023-04-13 00:00:00'),
(26, 'pepe', 'hola', 'a', NULL, 'Comun', NULL, NULL, '$2a$08$XAes/QBRicy4av5skRDPg.Zz8ZTjP8AeW7MJriJTlGlPsEoHB/mq.', 'pepe@hola.co', '2023-04-18 00:00:00'),
(27, 'jdjs', 'skak', NULL, NULL, 'Comun', NULL, NULL, '$2a$08$LVrygFysn7PrmcAgXnEF2uIMlpPSjhT9yIJPUdoxnh40CucD6Ppdu', 'pepe@jola.com', '2023-04-06 00:00:00'),
(28, 'alfredo', 'mercurio', NULL, NULL, 'Comun', NULL, NULL, '$2a$08$2Q.QEX0d/Es/jLpJfp3dR.7mOR0ITbgjNK.gm3TGk6X3JMGYRoMhu', 'fredo@queen.com', '1945-05-25 00:00:00'),
(29, 'alvaro', 'Arbeloa', 'arbeloa@rmadrid.com.jpg', NULL, 'Comun', 4, NULL, '$2a$08$YrwzBE5hzzSaslsTAH3.POfaT/OlAm2o3oZ2Ky3zG13BeMElDljLq', 'arbeloa@rmadrid.com', '1985-04-16 00:00:00'),
(30, 'peter', 'griffin', 'peterg@futes.com.jpg', NULL, 'Comun', NULL, NULL, '$2a$08$7PDyNFQV8G3.2Do2oPDxWeox5wIhlA5UELooRIatkXeLO0gwAirWG', 'peterg@futes.com', '2023-05-15 00:00:00'),
(31, 'luca', 'modric', 'modric@futes.com.jpg', NULL, 'Comun', NULL, NULL, '$2a$08$m4OAaLPfi.1d8JmWgez.0OHcPX/ii0LgjfyJ4bZ7rfkcUViHhvUgW', 'modric@futes.com', '1987-05-16 00:00:00'),
(32, 'iban', 'alexis', 'ivan@gmail.com.jpg', NULL, 'Comun', NULL, NULL, '$2a$08$IeE8cZdH8.X8H0XHU/orWOKkH5UjpPbspAKU24ipxTljZ60cRaK..', 'ivan@gmail.com', '2003-05-05 00:00:00'),
(33, 'alvaro', 'perez', 'colcao@email.com.jpg', NULL, 'Comun', NULL, NULL, '$2a$08$k8jCEzRZfD/UgmvEh5opDOuFtFGtxNECvwXr3ObtRNhTp4HSe1bZ.', 'colcao@email.com', '2008-05-16 00:00:00'),
(34, 'alvaro', 'perez', 'colacao@email.com.jpg', NULL, 'Comun', NULL, NULL, '$2a$08$0PtPwrHRzZUPECSIuvsg6uQOj1Mzq0uo.KtTy6BZatkdsdnncfw3y', 'colacao@email.com', '2023-05-20 00:00:00');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `arbitro`
--
ALTER TABLE `arbitro`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `asistencia`
--
ALTER TABLE `asistencia`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_jugador` (`id_jugador`),
  ADD KEY `id_partido` (`id_partido`);

--
-- Indices de la tabla `clasificacion`
--
ALTER TABLE `clasificacion`
  ADD PRIMARY KEY (`id_liga`,`id_equipo`),
  ADD KEY `id_equipo` (`id_equipo`);

--
-- Indices de la tabla `comentario`
--
ALTER TABLE `comentario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `id_noticia` (`id_noticia`);

--
-- Indices de la tabla `equipo`
--
ALTER TABLE `equipo`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `gol`
--
ALTER TABLE `gol`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_jugador` (`id_jugador`),
  ADD KEY `id_partido` (`id_partido`);

--
-- Indices de la tabla `jugador`
--
ALTER TABLE `jugador`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_equipo` (`id_equipo`);

--
-- Indices de la tabla `liga`
--
ALTER TABLE `liga`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `noticia`
--
ALTER TABLE `noticia`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_periodista` (`id_periodista`);

--
-- Indices de la tabla `partido`
--
ALTER TABLE `partido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_equipo_local` (`id_equipo_local`),
  ADD KEY `id_equipo_visitante` (`id_equipo_visitante`),
  ADD KEY `id_liga` (`id_liga`),
  ADD KEY `id_creador` (`id_creador`),
  ADD KEY `id_arbitro` (`id_arbitro`);

--
-- Indices de la tabla `tarjeta`
--
ALTER TABLE `tarjeta`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_jugador` (`id_jugador`),
  ADD KEY `id_partido` (`id_partido`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_equipo_fav` (`id_equipo_fav`),
  ADD KEY `id_jugador_fav` (`id_jugador_fav`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `arbitro`
--
ALTER TABLE `arbitro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `asistencia`
--
ALTER TABLE `asistencia`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `comentario`
--
ALTER TABLE `comentario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `equipo`
--
ALTER TABLE `equipo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- AUTO_INCREMENT de la tabla `gol`
--
ALTER TABLE `gol`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT de la tabla `jugador`
--
ALTER TABLE `jugador`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=478;

--
-- AUTO_INCREMENT de la tabla `liga`
--
ALTER TABLE `liga`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `noticia`
--
ALTER TABLE `noticia`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `partido`
--
ALTER TABLE `partido`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=120;

--
-- AUTO_INCREMENT de la tabla `tarjeta`
--
ALTER TABLE `tarjeta`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `asistencia`
--
ALTER TABLE `asistencia`
  ADD CONSTRAINT `asistencia_ibfk_1` FOREIGN KEY (`id_jugador`) REFERENCES `jugador` (`id`),
  ADD CONSTRAINT `asistencia_ibfk_2` FOREIGN KEY (`id_partido`) REFERENCES `partido` (`id`);

--
-- Filtros para la tabla `clasificacion`
--
ALTER TABLE `clasificacion`
  ADD CONSTRAINT `clasificacion_ibfk_1` FOREIGN KEY (`id_liga`) REFERENCES `liga` (`id`),
  ADD CONSTRAINT `clasificacion_ibfk_2` FOREIGN KEY (`id_equipo`) REFERENCES `equipo` (`id`);

--
-- Filtros para la tabla `comentario`
--
ALTER TABLE `comentario`
  ADD CONSTRAINT `comentario_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `comentario_ibfk_2` FOREIGN KEY (`id_noticia`) REFERENCES `noticia` (`id`);

--
-- Filtros para la tabla `gol`
--
ALTER TABLE `gol`
  ADD CONSTRAINT `gol_ibfk_1` FOREIGN KEY (`id_jugador`) REFERENCES `jugador` (`id`),
  ADD CONSTRAINT `gol_ibfk_2` FOREIGN KEY (`id_partido`) REFERENCES `partido` (`id`);

--
-- Filtros para la tabla `jugador`
--
ALTER TABLE `jugador`
  ADD CONSTRAINT `jugador_ibfk_1` FOREIGN KEY (`id_equipo`) REFERENCES `equipo` (`id`);

--
-- Filtros para la tabla `noticia`
--
ALTER TABLE `noticia`
  ADD CONSTRAINT `noticia_ibfk_1` FOREIGN KEY (`id_periodista`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `partido`
--
ALTER TABLE `partido`
  ADD CONSTRAINT `partido_ibfk_1` FOREIGN KEY (`id_equipo_local`) REFERENCES `equipo` (`id`),
  ADD CONSTRAINT `partido_ibfk_2` FOREIGN KEY (`id_equipo_visitante`) REFERENCES `equipo` (`id`),
  ADD CONSTRAINT `partido_ibfk_3` FOREIGN KEY (`id_liga`) REFERENCES `liga` (`id`),
  ADD CONSTRAINT `partido_ibfk_4` FOREIGN KEY (`id_creador`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `partido_ibfk_5` FOREIGN KEY (`id_arbitro`) REFERENCES `arbitro` (`id`);

--
-- Filtros para la tabla `tarjeta`
--
ALTER TABLE `tarjeta`
  ADD CONSTRAINT `tarjeta_ibfk_1` FOREIGN KEY (`id_jugador`) REFERENCES `jugador` (`id`),
  ADD CONSTRAINT `tarjeta_ibfk_2` FOREIGN KEY (`id_partido`) REFERENCES `partido` (`id`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`id_equipo_fav`) REFERENCES `equipo` (`id`),
  ADD CONSTRAINT `usuario_ibfk_2` FOREIGN KEY (`id_jugador_fav`) REFERENCES `jugador` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
