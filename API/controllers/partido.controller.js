const db = require("../models");
const Partido = db.partido;
const Op = db.Sequelize.Op;
const sequelize = db.sequelize;
const moment = require('moment');
exports.getPartidos = (req, res) => {
  Partido.findAll({
    order: [["fecha", "DESC"]],
  }).then((data) => {
      res.send(data);
    })
    .catch((err) => {
      res.status(500).send({
        message: err.message || "Some error occurred while retrieving partdos.",
      });
    });
};

exports.getOnePartido = (req, res) => {
  const id = req.params.id;

  Partido.findByPk(id)
    .then((data) => {
      if (data) {
        res.send(data);
      } else {
        res.status(404).send({
          message: `Cannot find partido with id=${id}.`,
        });
      }
    })
    .catch((err) => {
      res.status(500).send({
        message: err.message || "Some error occurred while retrieving partdos.",
      });
    });
};

exports.createPartido = async (req, res) => {
  var seJuega = true;
  var message = "";

  const partidos = await Partido.findAll().catch((err) => {
    res.status(500);
    message = "error recuperando partidos";
  });

  // Validar que no sea el mismo equipo
  if (req.body.id_equipo_local != req.body.id_equipo_visitante) {
    // Validar que el partido no se haya jugado
    partidos.forEach((element) => {
      if (
        element.id_equipo_local == req.body.id_equipo_local &&
        element.id_equipo_visitante == req.body.id_equipo_visitante
      ) {
        res.status(400);
        message = "Este partido ya se jugo";
        seJuega = false;
        return;
      }
    });
  } else {
    seJuega = false;
    res.status(400);
    message = "No puede jugar el mismo equipo en ambos lados";
  }

  var numArbitro = Math.floor(Math.random() * (14 - 1) + 1);

  // crear un gol
  const partido = new Partido({
    goles_local: 0,
    goles_visitante: 0,
    posesion_local: 50,
    posesion_visitante: 50,
    pases_local: 0,
    pases_visitante: 0,
    tiros_local: 0,
    tiros_visitante: 0,
    corners_local: 0,
    corners_visitante: 0,
    faltas_local: 0,
    faltas_visitante: 0,
    id_equipo_local: req.body.id_equipo_local,
    id_equipo_visitante: req.body.id_equipo_visitante,
    id_liga: 1,
    id_creador: req.body.id_creador,
    terminado: 0,
    fecha: sequelize.fn("NOW"),
    id_arbitro: numArbitro,
  });

  // Guardar partido en la BD
  if (seJuega) {
    partido
      .save(partido)
      .then((data) => {
        res.status(200).send({

        
          id:data.id,
          goles_local: data.goles_local,
          goles_visitante: data.goles_visitante,
          posesion_local: data.posesion_local,
          posesion_visitante: data.posesion_visitante,
          pases_local: data.pases_local,
          pases_visitante: data.pases_visitante,
          tiros_local: data.tiros_local,
          tiros_visitante: data.tiros_visitante,
          corners_local: data.corners_local,
          corners_visitante: data.corners_visitante,
          faltas_local: data.faltas_local,
          faltaVisitante: data.faltaVisitante,
          id_equipo_local: data.id_equipo_local,
          id_equipo_visitante: data.id_equipo_visitante,
          id_liga: data.id_liga,
          id_creador: data.id_creador,
          terminado: data.terminado,
          id_arbitro: data.id_arbitro,
          fecha: moment().format('YYYY-MM-DD')
        });
      })
      .catch((err) => {
        res.status(500).send({
          message: err.message || "Error ocurrido durante la creacion",
        });
      });
  } else {
    res.status(400).send({ message: message });
    return;
  }
};

exports.golLocal = (req, res) => {
  Partido.increment({ goles_local: +1 }, { where: { id: req.params.id } })
    .then((data) => {
      res.send(data);
    })
    .catch((err) => {
      res.status(500).send({
        message: err.message || "Error ocurrido durante la actualizacion",
      });
    });
};

exports.golVisitante = (req, res) => {
  Partido.increment({ goles_visitante: +1 }, { where: { id: req.params.id } })
    .then((data) => {
      res.send(data);
    })
    .catch((err) => {
      res.status(500).send({
        message: err.message || "Error ocurrido durante la actualizacion",
      });
    });
};

exports.paseLocal = (req, res) => {
  Partido.increment({ pases_local: +1 }, { where: { id: req.params.id } })
    .then((data) => {
      res.send(data);
    })
    .catch((err) => {
      res.status(500).send({
        message: err.message || "Error ocurrido durante la actualizacion",
      });
    });
};

exports.paseVisitante = (req, res) => {
  Partido.increment({ pases_visitante: +1 }, { where: { id: req.params.id } })
    .then((data) => {
      res.send(data);
    })
    .catch((err) => {
      res.status(500).send({
        message: err.message || "Error ocurrido durante la actualizacion",
      });
    });
};

exports.cornerLocal = (req, res) => {
  Partido.increment({ corners_local: +1 }, { where: { id: req.params.id } })
    .then((data) => {
      res.send(data);
    })
    .catch((err) => {
      res.status(500).send({
        message: err.message || "Error ocurrido durante la actualizacion",
      });
    });
};

exports.cornerVisitante = (req, res) => {
  Partido.increment({ corners_visitante: +1 }, { where: { id: req.params.id } })
    .then((data) => {
      res.send(data);
    })
    .catch((err) => {
      res.status(500).send({
        message: err.message || "Error ocurrido durante la actualizacion",
      });
    });
};

exports.tiroLocal = (req, res) => {
  Partido.increment({ tiros_local: +1 }, { where: { id: req.params.id } })
    .then((data) => {
      res.send(data);
    })
    .catch((err) => {
      res.status(500).send({
        message: err.message || "Error ocurrido durante la actualizacion",
      });
    });
};

exports.tiroVisitante = (req, res) => {
  Partido.increment({ tiros_visitante: +1 }, { where: { id: req.params.id } })
    .then((data) => {
      res.send(data);
    })
    .catch((err) => {
      res.status(500).send({
        message: err.message || "Error ocurrido durante la actualizacion",
      });
    });
};

exports.faltaLocal = (req, res) => {
  Partido.increment({ faltas_local: +1 }, { where: { id: req.params.id } })
    .then((data) => {
      res.send(data);
    })
    .catch((err) => {
      res.status(500).send({
        message: err.message || "Error ocurrido durante la actualizacion",
      });
    });
};

exports.faltaVisitante = (req, res) => {
  Partido.increment({ faltas_visitante: +1 }, { where: { id: req.params.id } })
    .then((data) => {
      res.send(data);
    })
    .catch((err) => {
      res.status(500).send({
        message: err.message || "Error ocurrido durante la actualizacion",
      });
    });
};

exports.posesion = (req, res) => {
  var posLocal = req.body.posesion_local;
  var posVisitante = req.body.posesion_visitante;

  if (posLocal + posVisitante == 100) {
    Partido.update(
      {
        posesion_local: posLocal,
        posesion_visitante: posVisitante,
      },
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
  } else {
    res.send("Las posesiones de local y visitante son incongruentes");
  }
};

exports.terminar = (req, res) => {
  Partido.update({ terminado: true }, { where: { id: req.params.id } })
    .then((data) => {
      res.send(data);
    })
    .catch((err) => {
      res.status(500).send({
        message: err.message || "Error ocurrido durante la actualizacion",
      });
    });
};

exports.getGolesFavorLocal = async (req, res) => {
  Partido.findAll({
    attributes: [
      ["id_equipo_local", "id_equipo"],
      [sequelize.fn("sum", sequelize.col("goles_local")), "total"],
    ],
    group: ["id_equipo_visitante"],
  })
    .then((data) => {
      res.send(data);
    })
    .catch((err) => {
      res.status(500).send({
        message: err.message || "Some error occurred while retrieving partdos.",
      });
    });
};

exports.getGolesFavorVisitante = async (req, res) => {
  Partido.findAll({
    attributes: [
      ["id_equipo_visitante", "id_equipo"],
      [sequelize.fn("sum", sequelize.col("goles_visitante")), "total"],
    ],
    group: ["id_equipo_visitante"],
  })
    .then((data) => {
      res.send(data);
    })
    .catch((err) => {
      res.status(500).send({
        message: err.message || "Some error occurred while retrieving partdos.",
      });
    });
};

exports.getGolesContraComoVisitante = async (req, res) => {
  Partido.findAll({
    attributes: [
      ["id_equipo_visitante", "id_equipo"],
      [sequelize.fn("sum", sequelize.col("goles_local")), "total"],
    ],
    group: ["id_equipo_visitante"],
  })
    .then((data) => {
      res.send(data);
    })
    .catch((err) => {
      res.status(500).send({
        message: err.message || "Some error occurred while retrieving partdos.",
      });
    });
};

exports.getGolesContraComoLocal = async (req, res) => {
  Partido.findAll({
    attributes: [
      ["id_equipo_local", "id_equipo"],
      [sequelize.fn("sum", sequelize.col("goles_visitante")), "total"],
    ],
    group: ["id_equipo_local"],
  })
    .then((data) => {
      res.send(data);
    })
    .catch((err) => {
      res.status(500).send({
        message: err.message || "Some error occurred while retrieving partdos.",
      });
    });
};

exports.getPartidosGanadosLocal = async (req, res) => {
  Partido.findAll({
    attributes: [
      ["id_equipo_local", "id_equipo"],
      [sequelize.fn("COUNT", sequelize.col("*")), "total"],
    ],
    where: { goles_local: { [Op.gt]: sequelize.col("goles_visitante") } },
    group: ["id_equipo_local"],
  })
    .then((data) => {
      res.send(data);
    })
    .catch((err) => {
      res.status(500).send({
        message: err.message || "Some error occurred while retrieving partdos.",
      });
    });
};

exports.getPartidosGanadosVisitante = async (req, res) => {
  Partido.findAll({
    attributes: [
      ["id_equipo_visitante", "id_equipo"],
      [sequelize.fn("COUNT", sequelize.col("*")), "total"],
    ],
    where: { goles_visitante: { [Op.gt]: sequelize.col("goles_local") } },
    group: ["id_equipo_visitante"],
  })
    .then((data) => {
      res.send(data);
    })
    .catch((err) => {
      res.status(500).send({
        message: err.message || "Some error occurred while retrieving partdos.",
      });
    });
};

exports.getPartidosEmpatadosLocal = async (req, res) => {
  Partido.findAll({
    attributes: [
      ["id_equipo_local", "id_equipo"],
      [sequelize.fn("COUNT", sequelize.col("*")), "total"],
    ],
    where: { goles_local: { [Op.eq]: sequelize.col("goles_visitante") } },
    group: ["id_equipo_local"],
  })
    .then((data) => {
      res.send(data);
    })
    .catch((err) => {
      res.status(500).send({
        message: err.message || "Some error occurred while retrieving partdos.",
      });
    });
};

exports.getPartidosEmpatadosVisitante = async (req, res) => {
  Partido.findAll({
    attributes: [
      ["id_equipo_visitante", "id_equipo"],
      [sequelize.fn("COUNT", sequelize.col("*")), "total"],
    ],
    where: { goles_visitante: { [Op.eq]: sequelize.col("goles_local") } },
    group: ["id_equipo_visitante"],
  })
    .then((data) => {
      res.send(data);
    })
    .catch((err) => {
      res.status(500).send({
        message: err.message || "Some error occurred while retrieving partdos.",
      });
    });
};

exports.getPartidosPerdidosLocal = async (req, res) => {
  Partido.findAll({
    attributes: [
      ["id_equipo_local", "id_equipo"],
      [sequelize.fn("COUNT", sequelize.col("*")), "total"],
    ],
    where: { goles_local: { [Op.lt]: sequelize.col("goles_visitante") } },
    group: ["id_equipo_local"],
  })
    .then((data) => {
      res.send(data);
    })
    .catch((err) => {
      res.status(500).send({
        message: err.message || "Some error occurred while retrieving partdos.",
      });
    });
};

exports.getPartidosPerdidosVisitante = async (req, res) => {
  Partido.findAll({
    attributes: [
      ["id_equipo_visitante", "id_equipo"],
      [sequelize.fn("COUNT", sequelize.col("*")), "total"],
    ],
    where: { goles_visitante: { [Op.lt]: sequelize.col("goles_local") } },
    group: ["id_equipo_visitante"],
  })
    .then((data) => {
      res.send(data);
    })
    .catch((err) => {
      res.status(500).send({
        message: err.message || "Some error occurred while retrieving partdos.",
      });
    });
};

exports.getPatidosEquipo = async (req, res) => {
  const id = req.params.id_equipo;

  Partido.findAll({
    where: { [Op.or]: [{ id_equipo_local: id }, { id_equipo_visitante: id }] },
  })
    .then((data) => {
      res.send(data);
    })
    .catch((err) => {
      res.status(500).send({
        message: err.message || "Some error occurred while retrieving partdos.",
      });
    });
};
