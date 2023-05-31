const db = require("../models");
const Equipo = db.equipo;
const Op = db.Sequelize.Op;



// Retrieve all teams from the database.
exports.findAllEquipos = (req, res) => {

  Equipo.findAll()
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving equipos."
      });
    });
};

// Find a single Tutorial with an id
exports.findOneEquipo = (req, res) => {
    const id = req.params.id;

    Equipo.findByPk(id)
      .then(data => {
        if (data) {
          res.send(data);
        } else {
          res.status(404).send({
            message: `Cannot find team with id=${id}.`
          });
        }
      })
      .catch(err => {
        res.status(500).send({
          message: "Error retrieving equipo with id=" + id
        });
      });
};







// Update a Tutorial by the id in the request
exports.update = (req, res) => {
  
};

// Delete a Tutorial with the specified id in the request
exports.delete = (req, res) => {
  
};

// Delete all Tutorials from the database.
exports.deleteAll = (req, res) => {
  
};

// Find all published Tutorials
exports.findAllPublished = (req, res) => {
  
};

// Create and Save a new equipo
/*
exports.create = (req, res) => {
  // Validate request
  if (!req.body.nombre) {
    res.status(400).send({
      message: "Content can not be empty!"
    });
    return;
  }

  // Crear un equipo
  const equipo = {
    nombre: req.body.Nombre,
    estadio: req.body.estadio,
    presi: req.body.presi,
    descripcion: req.body.descripcion,
    escudo: req.body.escudo,
    nombre_abrev: req.body.nombre_abrev
  };

  // Save Tutorial in the database
  Equipo.create(equipo)
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while creating the Tutorial."
      });
    });
};
*/