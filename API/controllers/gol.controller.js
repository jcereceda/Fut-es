const db = require("../models");
const Partido = db.partido;
const Jugador = db.jugador;
const Gol = db.gol;
const Op = db.Sequelize.Op;
const sequelize = db.sequelize;

//Traer goles agrupados por jugador descendentemente
exports.getEstadisticasGoles = (req, res) => {
  Gol.findAll({
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
        message: err.message || "Some error occurred while retrieving goles.",
      });
    });
};

// Traer todos los goles de un jugador
exports.getGolesJugador = (req, res) => {
  Gol.findOne({
    attributes: [
      "id_jugador",
      [sequelize.fn("COUNT", sequelize.col("*")), "total"],
    ],
    where: {
      id_jugador: req.params.id_jugador,
    }
  })
    .then((data) => {
      res.send(data);
    })
    .catch((err) => {
      res.status(500).send({
        message: err.message || "Some error occurred while retrieving goles."
      });
    });
};

exports.getGolesPartido = (req, res) => {
  Gol.findAll({
    where: {
      id_partido: req.params.id_partido,
    },
  })
    .then((data) => {
      res.send(data);
    })
    .catch((err) => {
      res.status(500).send({
        message: err.message || "Some error occurred while retrieving goles.",
      });
    });
};

// Subir gol
exports.crearGol = async (req, res) => {
  var hayGol = false;
  var mensaje = "";
  // Comprobar que el jugador que mete el gol está en uno de los equipos del partido
  // MEtodo traer partido
  var partidoGol = await Partido.findByPk(req.body.id_partido);

  // Método traer jugador y comparar
  var jugadorGol = await Jugador.findByPk(req.body.id_jugador);

  if (
    partidoGol.id_equipo_local == jugadorGol.id_equipo ||
    partidoGol.id_equipo_visitante == jugadorGol.id_equipo
  ) {
    hayGol = true;
    mensaje = "El jugador no pertenece a ningun equipo";
  }

  if (jugadorGol.posicion == "Entrenador") {
    hayGol = false;
    mensaje = "No puede meter gol un entrenador";
  }

  // crear un gol
  const gol = new Gol({
    minuto: req.body.minuto,
    id_jugador: req.body.id_jugador,
    id_partido: req.body.id_partido,
  });

  // Guardar gol en la BD
  if (hayGol) {
    gol
      .save(gol)
      .then((data) => {
        res.send(data);
      })
      .catch((err) => {
        res.status(500).send({
          message: err.message || "Error ocurrido durante la creacion",
        });
      });
  } else {
    res.status(400).send(mensaje);
  }
};
