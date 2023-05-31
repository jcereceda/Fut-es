const db = require("../models");
const Usuario = db.usuario;
const Op = db.Sequelize.Op;

var jwt = require("jsonwebtoken");
var bcrypt = require("bcryptjs");
var config = require("../config/auth.config");

exports.getUsuarios = (req, res) => {
  Usuario.findAll()
    .then((data) => {
      res.send(data);
    })
    .catch((err) => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving usuarios.",
      });
    });
};

exports.getOneUsuario = (req, res) => {
  const id = req.params.id;

  Usuario.findByPk(id)
    .then((data) => {
      if (data) {
        res.send(data);
      } else {
        res.status(404).send({
          message: `Cannot find user with id=${id}.`,
        });
      }
    })
    .catch((err) => {
      res.status(500).send({
        message: err.message || "Some error occurred while retrieving user.",
      });
    });
};

// Crear un usuario desde superadmin
exports.createUsuarioSuper = (req, res) => {
  // Validar peticion
  if (
    !req.body.email ||
    req.body.apellidos ||
    req.body.nombre ||
    req.body.fechaNac ||
    req.body.passwd
  ) {
    res.status(400).send({ message: "Debe de estar el campo completo" });
    return;
  }

  // Validar email en uso

  // crear un usuario
  const usuario = new Usuario({
    email: req.body.email,
    nombre: req.body.nombre,
    apellidos: req.body.apellidos,
    rol: req.body.rol,
    fechaNac: req.body.fechaNac,
    foto: req.body.foto,
    fondo: req.body.fondo,
    passwd: bcrypt.hashSync(req.body.passwd, 8),
  });

  // Guardar usuario en la BD
  usuario
    .save(usuario)
    .then((data) => {
      res.send(data);
    })
    .catch((err) => {
      res.status(500).send({
        message: err.message || "Error ocurrido durante la creacion",
      });
    });
};

exports.asignarFoto = (req, res) => {
  Usuario.update({ foto: req.body.foto }, { where: { id: req.params.id } })
    .then((data) => {
      res.send(data);
    })
    .catch((err) => {
      res.status(500).send({
        message: err.message || "Error ocurrido durante la actualizacion",
      });
    });
};

// Crear un usuario desde registro
exports.createUsuario = async (req, res) => {
  // Se permite crear
  var permitido = false;
  var mensaje = "";
  // Validar peticion
  if (
    !req.body.email ||
    !req.body.apellidos ||
    !req.body.nombre ||
    !req.body.fechaNac ||
    !req.body.passwd
  ) {
    permitido = true;
    mensaje = "Algún campo vacío";
    //return;
  }

  // validar email en uso
  var userExist = await Usuario.findOne({
    where: {
      email: req.body.email,
    },
  });

  if (userExist) {
    permitido = true;
    mensaje = "Email en uso";
  }

  if (!permitido) {
    // crear un usuario
    const usuario = new Usuario({
      email: req.body.email,
      nombre: req.body.nombre,
      apellidos: req.body.apellidos,
      foto: req.body.email + ".jpg",
      fechaNac: req.body.fechaNac,
      rol: "Comun",
      passwd: bcrypt.hashSync(req.body.passwd, 8),
    });

    // Guardar usuario en la BD
    usuario
      .save(usuario)
      .then((data) => {
        res.status(200).send(data);
      })
      .catch((err) => {
        res.status(500).send({
          message: err.message || "Error ocurrido durante la creacion",
        });
      });
  } else {
    res.status(400).send({ message: mensaje });
  }
};

// Logearse
exports.signin = (req, res) => {
  Usuario.findOne({
    where: {
      email: req.body.email,
    },
  })
    .then((user) => {
      if (!user) {
        return res.status(404).send({ message: "User Not found." });
      }

      var passwordIsValid = bcrypt.compareSync(req.body.passwd, user.passwd);

      if (!passwordIsValid) {
        return res.status(401).send({
          accessToken: null,
          message: "Invalid Password!",
        });
      }

      var token = jwt.sign({ id: user.id }, config.secret, {
        expiresIn: 604800000,
      });

      res.status(200).send({
        id: user.id,
        nombre: user.nombre,
        apellidos: user.apellidos,
        email: user.email,
        rol: user.rol,
        foto: user.foto,
        id_equipo_fav: user.id_equipo_fav,
        id_jugador_fav: user.id_jugador_fav,
        accessToken: token,
      });
    })
    .catch((err) => {
      res.status(500).send({ message: err.message });
    });
};

exports.cambiarPass = (req, res) => {
  Usuario.update({ passwd: req.body.passwd }, { where: { id: req.params.id } })
    .then((data) => {
      res.send(data);
    })
    .catch((err) => {
      res.status(500).send({
        message: err.message || "Error ocurrido durante la actualizacion",
      });
    });
};

exports.asignarFoto = (req, res) => {
  Usuario.update({ foto: req.body.foto }, { where: { id: req.params.id } })
    .then((data) => {
      res.send(data);
    })
    .catch((err) => {
      res.status(500).send({
        message: err.message || "Error ocurrido durante la actualizacion",
      });
    });
};

exports.asignarFondo = (req, res) => {
  Usuario.update({ foto: req.body.fondo }, { where: { id: req.params.id } })
    .then((data) => {
      res.send(data);
    })
    .catch((err) => {
      res.status(500).send({
        message: err.message || "Error ocurrido durante la actualizacion",
      });
    });
};

exports.asignarEquipoFav = (req, res) => {
  Usuario.update(
    { id_equipo_fav: req.params.id_equipo_fav },
    { where: { id: req.params.id } }
  )
    .then((data) => {
      res.send(data);
    })
    .catch((err) => {
      res.status(500).send({
        message: err.message || "Error ocurrido durante la actualizacion",
      });
    });
};

exports.asignarJugadorFav = (req, res) => {
  Usuario.update(
    { id_jugador_fav: req.params.id_jugador_fav },
    { where: { id: req.params.id } }
  )
    .then((data) => {
      res.send(data);
    })
    .catch((err) => {
      res.status(500).send({
        message: err.message || "Error ocurrido durante la actualizacion",
      });
    });
};

exports.actualizarPerfil = async (req, res) => {
  await Usuario.update(
    {
      email: req.body.email,
      nombre: req.body.nombre,
      apellidos: req.body.apellidos,
      id_equipo_fav: req.body.id_equipo_fav,
      id_jugador_fav: req.body.id_jugador_fav,
    },
    { where: { id: req.body.id }, returning: true }
  ).catch((err) => {
    res.status(500).send({
      message: err.message || "Error ocurrido durante la actualizacion",
    });
  });

 await Usuario.findByPk(req.body.id)
    .then((user) => {
      res.status(200).send({
        id: user.id,
        nombre: user.nombre,
        apellidos: user.apellidos,
        email: user.email,
        rol: user.rol,
        foto: user.foto,
        id_equipo_fav: user.id_equipo_fav,
        id_jugador_fav: user.id_jugador_fav
      });
    })
    .catch((err) => {
      res.status(500).send({
        message: err.message || "Some error occurred while retrieving user.",
      });
    });
};
