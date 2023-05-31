const { noticia, partido } = require("../models/index.js");
const { authJwt } = require("../middleware");

module.exports = (app) => {
  app.use(function (req, res, next) {
    res.header(
      "Access-Control-Allow-Headers",
      "x-access-token, Origin, Content-Type, Accept"
    );
    next();
  });

  const equipoController = require("../controllers/equipo.controller.js");
  const jugadorController = require("../controllers/jugador.controller.js");
  const golController = require("../controllers/gol.controller.js");
  const asistController = require("../controllers/asistencia.controller.js");
  const tarjetaController = require("../controllers/tarjeta.controller.js");
  const partidoController = require("../controllers/partido.controller.js");
  const arbitroController = require("../controllers/arbitro.controller.js");
  const usuarioController = require("../controllers/usuario.controller.js");
  const noticiaController = require("../controllers/noticia.controller.js");
  const clasificacionController = require("../controllers/clasificacion.controller.js");
  const comentarioController = require("../controllers/comentario.controller.js");
  const ligaController = require("../controllers/liga.controller.js");

  var router = require("express").Router();

  // Devolver todos los equipos
  router.get("/equipos/", equipoController.findAllEquipos);

  // Devolver un equipo por id
  router.get("/equipos/:id", equipoController.findOneEquipo);

  // Devolver todos los jugadores
  router.get("/jugadores/", jugadorController.findAllJugadores);

  // Devolver un solo jugador
  router.get("/jugador/:id/", jugadorController.findOneJugador);

  // DEvolver todos los jugadores con gol
  router.get("/jugadores/gol/", jugadorController.findAllJugadoresGol);

  // Devolver todos los jugadores con asistencias
  router.get("/jugadores/asistencia/", jugadorController.findAllJugadoresAsistencia);

  // Devolver todos los jugadores con tarjetas
  router.get("/jugadores/tarjeta/", jugadorController.findAllJugadoresTarjetas);

  // Devolver jugadores por equipo
  router.get("/jugadores/:id_equipo/", jugadorController.findAllJugadoresByTeam);
  
    
  // Subir un jugador
  router.post("/jugadores/", jugadorController.createJugador);


  // INCIDENCIAS

  // Devolver goles agrupados por jugador descendentemente
  router.get("/estadisticas/goles", golController.getEstadisticasGoles);

  // Devolver cantidad de goles de un jugador
  router.get(
    "/incidencias/goles/jugador/:id_jugador",
    golController.getGolesJugador
  );

  // Devolver goles de un partido
  router.get(
    "/incidencias/goles/partido/:id_partido",
    golController.getGolesPartido
  );

  // Guardar un gol
  router.post(
    "/incidencias/goles/",
    [authJwt.verifyToken, authJwt.isCreador],
    golController.crearGol
  );

  // Devolver asistencias de un jugador
  router.get(
    "/incidencias/asistencias/jugador/:id_jugador",
    asistController.getAsistenciasJugador
  );

  // Devolver asistencias agrupadas por jugador descendentemente
  router.get("/estadisticas/asistencias", asistController.getEstadisticasAsistencias);

  // Guardar asistencias
  router.post(
    "/incidencias/asistencias/",
    [authJwt.verifyToken, authJwt.isCreador],
    asistController.crearAsistencia
  );

  // Devolver tarjetas agrupadas por jugador descendentemente
  router.get("/estadisticas/tarjetas/amarilla/", tarjetaController.getAmarillas);
  router.get("/estadisticas/tarjetas/rojas/", tarjetaController.getRojas);


  // Devolver tarjetas de un jugador
  router.get(
    "/incidencias/rojas/jugador/:id_jugador",
    tarjetaController.getRojasJugador
  );
  router.get(
    "/incidencias/amarillas/jugador/:id_jugador",
    tarjetaController.getAmarillasJugador
  );

  // Devolver tarjetas de un partido
  router.get(
    "/incidencias/tarjetas/rojas/partido/:id_partido",
    tarjetaController.getRojasPartido
  );
  router.get(
    "/incidencias/tarjetas/amarillas/partido/:id_partido",
    tarjetaController.getAmarillasPartido
  );

  // Guardar tarjeta
  router.post(
    "/incidencias/tarjetas/",
    [authJwt.verifyToken, authJwt.isCreador],
    tarjetaController.createTarjeta
  );

  // PARTIDOS
  // Recuperar todos los partidos
  router.get("/partidos/", partidoController.getPartidos);

  // Recuperar un solo partido
  router.get("/partidos/:id", partidoController.getOnePartido);

  // Recuperar partidos de un equipo
  router.get("/partidos/equipo/:id_equipo", partidoController.getPatidosEquipo); 


  // Crear un partido
  router.post(
    "/partidos/",
    [authJwt.verifyToken, authJwt.isCreador],
    partidoController.createPartido
  );

  // Actualizar datos del partido
  router.put(
    "/partidos/:id/golLocal",
    [authJwt.verifyToken, authJwt.isCreador],
    partidoController.golLocal
  );
  router.put(
    "/partidos/:id/golVisitante",
    [authJwt.verifyToken, authJwt.isCreador],
    partidoController.golVisitante
  );
  router.put(
    "/partidos/:id/cornerLocal",
    [authJwt.verifyToken, authJwt.isCreador],
    partidoController.cornerLocal
  );
  router.put(
    "/partidos/:id/cornerVisitante",
    [authJwt.verifyToken, authJwt.isCreador],
    partidoController.cornerVisitante
  );
  router.put(
    "/partidos/:id/paseLocal",
    [authJwt.verifyToken, authJwt.isCreador],
    partidoController.paseLocal
  );
  router.put(
    "/partidos/:id/paseVisitante",
    [authJwt.verifyToken, authJwt.isCreador],
    partidoController.paseVisitante
  );
  router.put(
    "/partidos/:id/tiroLocal",
    [authJwt.verifyToken, authJwt.isCreador],
    partidoController.tiroLocal
  );
  router.put(
    "/partidos/:id/tiroVisitante",
    [authJwt.verifyToken, authJwt.isCreador],
    partidoController.tiroVisitante
  );
  router.put(
    "/partidos/:id/faltaLocal",
    [authJwt.verifyToken, authJwt.isCreador],
    partidoController.faltaLocal
  );
  router.put(
    "/partidos/:id/faltaVisitante",
    [authJwt.verifyToken, authJwt.isCreador],
    partidoController.faltaVisitante
  );
  // Pasando JSON con posesion_local y posesion_visitante
  router.put(
    "/partidos/:id/posesion",
    [authJwt.verifyToken, authJwt.isCreador],
    partidoController.posesion
  );
  // Terminar partido
  router.put(
    "/partidos/:id/terminar",
    [authJwt.verifyToken, authJwt.isCreador],
    partidoController.terminar
  );

  // Arbitro
  router.get("/arbitros/:id/", arbitroController.getOneArbitro);

  // USUARIOS
  // Recuperar todos los usuarios
  router.get("/usuarios/", usuarioController.getUsuarios);

  // Recuperar Un usuario
  router.get(
    "/usuarios/:id",
    authJwt.verifyToken,
    usuarioController.getOneUsuario
  );

  // Crear usuario común desde registro
  router.post("/usuarios/", usuarioController.createUsuario);

  // Crear usuario desde superusuario
  router.post(
    "/usuarios/super/",
    [authJwt.verifyToken, authJwt.isAdmin],
    usuarioController.createUsuarioSuper
  );

  // LOGIN
  router.post("/login", usuarioController.signin);

  // Asignar equipo y jugador favorito al usuario
  router.put(
    "/usuarios/:id/equipo/:id_equipo_fav",
    authJwt.verifyToken,
    usuarioController.asignarEquipoFav
  );
  router.put(
    "/usuarios/:id/jugador/:id_jugador_fav",
    authJwt.verifyToken,
    usuarioController.asignarJugadorFav
  );

  // Actualizar perfil pasando json
  router.put(
    "/usuarios/actualizar",
    authJwt.verifyToken,
    usuarioController.actualizarPerfil
  );
  
  router.put(
    "/usuarios/:id/actualizarpass",
    authJwt.verifyToken,
    usuarioController.cambiarPass
  );

  // NOTICIAS
  router.get("/noticias/", noticiaController.getNoticias);
  router.get("/noticias/:id", noticiaController.getOneNoticia);
  router.post(
    "/noticias/",
    [authJwt.verifyToken, authJwt.isPeriodista],
    noticiaController.createNoticia
  );
  router.delete(
    "/noticias/:id",
    [authJwt.verifyToken, authJwt.isAdmin],
    noticiaController.deleteNoticia
  );

  // COMENTARIOS
  router.get(
    "/comentarios/",
    authJwt.verifyToken,
    comentarioController.getComentarios
  );
  router.get(
    "/comentarios/:id_noticia",
    authJwt.verifyToken,
    comentarioController.getComentariosNoticia
  );
  router.post(
    "/comentarios/",
    authJwt.verifyToken,
    comentarioController.createComentario
  );

  // CLASIFICACION

  router.get("/liga/:id", ligaController.getLiga);

  router.get(
    "/clasificacion/:id_liga",
    clasificacionController.getClasificacion
  );

  router.get(
    "/clasificacion/golescontra/local",
    partidoController.getGolesContraComoLocal
  );
  router.get(
    "/clasificacion/golescontra/visitante",
    partidoController.getGolesContraComoVisitante
  );

  router.get(
    "/clasificacion/golesfavor/local",
    partidoController.getGolesFavorLocal
  );
  router.get(
    "/clasificacion/golesfavor/visitante",
    partidoController.getGolesFavorVisitante
  );

  router.get(
    "/clasificacion/partidosganados/local",
    partidoController.getPartidosGanadosLocal
  );
  router.get(
    "/clasificacion/partidosganados/visitante",
    partidoController.getPartidosGanadosLocal
  );

  router.get(
    "/clasificacion/partidosperdidos/local",
    partidoController.getPartidosPerdidosLocal
  );
  router.get(
    "/clasificacion/partidosperdidos/visitante",
    partidoController.getPartidosPerdidosVisitante
  );
  router.get(
    "/clasificacion/partidosempatados/local",
    partidoController.getPartidosEmpatadosLocal
  );
  router.get(
    "/clasificacion/partidosempatados/visitante",
    partidoController.getPartidosEmpatadosVisitante
  );

  app.use("/api/", router);
};
