const { Association } = require("sequelize");
const db = require("../models");
const Partido = db.partido;
const Jugador = db.jugador;
const Asistencia = db.asistencia;
const Op = db.Sequelize.Op;
const sequelize = db.sequelize;

// Traer todos los goles de un jugador
exports.getAsistenciasJugador  = (req, res) => {
    Asistencia.findAll({
      attributes: [
        "id_jugador",
        [sequelize.fn("COUNT", sequelize.col("*")), "total"],
      ],
        where: {
          id_jugador: req.params.id_jugador
        }
      })
      .then(data => {
        res.send(data);
      })
      .catch(err => {
        res.status(500).send({
          message:
            err.message || "Some error occurred while retrieving tutorials."
        });
      });
  };

// Traer cantidad de asistencias ordenadas por jugador
exports.getEstadisticasAsistencias = (req, res) => {
  Asistencia.findAll({
    attributes: [
      "id_jugador",
      [sequelize.fn("COUNT", sequelize.col("*")), "total"],
    ],
    group: "id_jugador",
    order: [["total", "DESC"]],
    limit: 10
  })
    .then((data) => {
      res.send(data);
    })
    .catch((err) => {
      res.status(500).send({
        message: err.message || "Some error occurred while retrieving asists.",
      });
    });
};

  // Subir asistencia
exports.crearAsistencia = async (req, res) => {
  var hayAsistencia = false;
  var mensaje = "";
  // Comprobar que el jugador que mete el gol está en uno de los equipos del partido
  // MEtodo traer partido
  var partidoGol = await Partido.findByPk(req.body.id_partido);

  // Método traer jugador y comparar
  var jugadorGol = await Jugador.findByPk(req.body.id_jugador);

  
  if(partidoGol.id_equipo_local == jugadorGol.id_equipo || partidoGol.id_equipo_visitante == jugadorGol.id_equipo ) {
      hayAsistencia = true;
      mensaje = "El jugador no pertenece a ningun equipo";

  }

  if(jugadorGol.posicion == "Entrenador"){
    hayAsistencia = false;
      mensaje = "No puede asistir un entrenador";
  }
  // crear un gol
  const asistencia  = new Asistencia({
      minuto: req.body.minuto,
      id_jugador : req.body.id_jugador,
      id_partido : req.body.id_partido
  });
    
    // Comprobar que el jugador que asiste está en uno de los equipos del partido
    // MEtodo
    
  if(hayAsistencia){
    asistencia
      .save(asistencia)
      .then(data =>{
          res.send(data)
      })
      .catch(err => {
          res.status(500).send({
              message: err.message || "Error ocurrido durante la creacion"
          });
      });
  } else {
      res.status(400).send(mensaje);
  }
};
