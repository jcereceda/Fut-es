const db = require("../models");
const Comentario = db.comentario;
const Op = db.Sequelize.Op;
const sequelize = db.sequelize;

exports.getComentarios = (req, res) => {
  Comentario.findAll()
    .then((data) => {
      res.send(data);
    })
    .catch((err) => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving comentarios.",
      });
    });
};

exports.getComentariosNoticia = async (req, res) => {
try {
  const [results, metadata] = await db.sequelize.query(
    "select distinct c.id, c.fecha, c.comentario, c.id_noticia, c.id_usuario , u.nombre, u.apellidos, u.foto from comentario c inner join usuario u on c.id_usuario = u.id where c.id_noticia = " +
      req.params.id_noticia + " order by c.fecha desc"
  );
  res.send(results);
} catch(error) {
  console.error(error);
  res.status(500).send('Error al obtener los comentarios');
}
};

// Subir gol
exports.createComentario = (req, res) => {
  // Validar peticion
  if (!req.body.comentario) {
    res.status(400).send({ message: "Debe de estar el campo completo" });
    return;
  }

  // crear un gol
  const comentario = new Comentario({
    comentario: req.body.comentario,
    id_noticia: req.body.id_noticia,
    id_usuario: req.body.id_usuario,
    fecha: sequelize.fn("NOW")
  });

  // Guardar Comentario en la BD
  comentario
    .save(comentario)
    .then((data) => {
      res.send(data);
    })
    .catch((err) => {
      res.status(500).send({
        message: err.message || "Error ocurrido durante la creacion",
      });
    });
};
