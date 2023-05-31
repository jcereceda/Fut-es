const db = require("../models");
const Jugador = db.jugador;
const Op = db.Sequelize.Op;
const sequelize = db.sequelize;

// Traer todos los jugadores de la BD
exports.findAllJugadores = (req, res) => {

    Jugador.findAll()
      .then(data => {
        res.send(data);
      })
      .catch(err => {
        res.status(500).send({
          message:
            err.message || "Some error occurred while retrieving jugadores."
        });
      });
  };

// Jugadores por equipo
exports.findAllJugadoresByTeam = (req, res) => {

  Jugador.findAll(
      { where: {id_equipo: req.params.id_equipo }}
  )
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving jugadores."
      });
    });
};



// Traer un jugador
exports.findOneJugador = (req, res) => {
  const id = req.params.id;

  Jugador.findByPk(id)
    .then((data) => {
      if (data) {
        res.send(data);
      } else {
        res.status(404).send({
          message: `Cannot find player with id=${id}.`
        });
      }
    })
    .catch((err) => {
      res.status(500).send({
        message: err.message || "Some error occurred while retrieving user.",
      });
    });
};

// Traer todos los jugadores con gol 
exports.findAllJugadoresGol = (req, res) => {
  Jugador.findAll({ 
    where: {
      id: {
        [Op.in]: [sequelize.literal("select id_jugador from gol")]
      }
    }
  })
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving jugadores."
      });
    });
};

// Todos los jugadores con asistencias
exports.findAllJugadoresAsistencia = (req, res) => {

  Jugador.findAll({ 
    where: {
      id: {
        [Op.in]: [sequelize.literal("select id_jugador from asistencia")]
      }
    }
  })
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving jugadores."
      });
    });
};


// Todos los jugadores con tarjetas
exports.findAllJugadoresTarjetas = (req, res) => {

  Jugador.findAll({ 
    where: {
      id: {
        [Op.in]: [sequelize.literal("select id_jugador from tarjeta")]
      }
    }
  })
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving jugadores."
      });
    });
};


// Guardar un solo jugador
exports.createJugador = (req, res) => {
    // Validar peticion
    if(!req.body.nombre){
     res.status(400).send({message: "Debe de estar el campo completo"})
     return;
 }
 
 // crear un libro
 const jugador  = new Jugador({
     
     nombre : req.body.nombre,
     apodo: req.body.apodo,
     peso: req.body.peso,
     altura: req.body.altura,
     foto : req.body.foto,
     fondo: req.body.fondo,
     dorsal : req.body.dorsal,
     id_equipo : req.body.id_equipo,
     posicion : req.body.posicion
     
 });
 
 // Guardar libro en la BD
 jugador
     .save(jugador)
     .then(data =>{
         res.send(data)
     })
     .catch(err => {
         res.status(500).send({
             message: err.message || "Error ocurrido durante la creacion"
         });
     });
 };
 