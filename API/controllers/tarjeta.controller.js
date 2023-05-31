const db = require("../models");
const Partido = db.partido;
const Jugador = db.jugador;
const Tarjeta = db.tarjeta;
const Op = db.Sequelize.Op;
const sequelize = db.sequelize;

// Traer tarjetas de un jugador
exports.getRojasJugador  = (req, res) => {
    Tarjeta.findAll({
      attributes: [
        "id_jugador",
        [sequelize.fn("COUNT", sequelize.col("*")), "total"],
      ],
        where: {
          [Op.and]: [
            { id_jugador: req.params.id_jugador },
            { color: "Roja" }
          ]
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

  //Traer amarillas o rojas agrupados por jugador descendentemente
exports.getAmarillas = (req, res) => {
    Tarjeta.findAll({
      attributes: [
        "id_jugador",
        [sequelize.fn("COUNT", sequelize.col("*")), "total"],
      ],
      where: {color: "amarilla"},
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

exports.getRojas = (req, res) => {
    Tarjeta.findAll({
      attributes: [
        "id_jugador",
        [sequelize.fn("COUNT", sequelize.col("*")), "total"],
      ],
      where: {color: "roja"},
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

exports.getAmarillasJugador  = (req, res) => {
Tarjeta.findAll({
  attributes: [
    "id_jugador",
    [sequelize.fn("COUNT", sequelize.col("*")), "total"],
  ],
    where: {
        [Op.and]: [
        { id_jugador: req.params.id_jugador },
        { color: "Amarilla" }
        ]
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
  

exports.getRojasPartido  = (req, res) => {
Tarjeta.findAll({
    where: {
        [Op.and]: [
            { id_partido: req.params.id_partido },
            { color: "Roja" }
        ]
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

exports.getAmarillasPartido  = (req, res) => {
Tarjeta.findAll({
    where: {
        [Op.and]: [
            { id_partido: req.params.id_partido },
            { color: "Amarilla" }
        ]
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


exports.createTarjeta = async (req, res) => {
    // Validar peticion
    var hayTarjeta = false;
    var mensaje = "";
    // Comprobar que el jugador que mete el gol está en uno de los equipos del partido
    // MEtodo traer partido
    var partidoGol = await Partido.findByPk(req.body.id_partido);

    // Método traer jugador y comparar
    var jugadorGol = await Jugador.findByPk(req.body.id_jugador);

    
    if(partidoGol.id_equipo_local == jugadorGol.id_equipo || partidoGol.id_equipo_visitante == jugadorGol.id_equipo ) {
        hayTarjeta = true;
        mensaje = "El jugador no pertenece a ningun equipo";

    }



    // crear una tarjeta
    const tarjeta  = new Tarjeta({
        minuto: req.body.minuto,
        id_jugador : req.body.id_jugador,
        id_partido : req.body.id_partido,
        color: req.body.color
    });
    
    if(hayTarjeta) {
        tarjeta
        .save(tarjeta)
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
