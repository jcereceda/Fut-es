const db = require("../models");
const Clasificacion = db.clasificacion;
const Op = db.Sequelize.Op;

exports.getClasificacion = (req, res) => {

    Clasificacion.findAll({
        attributes: { exclude: ['id']}, // No tiene id ya que es una tabla de relación
        where: { id_liga: req.params.id_liga},
        order: [
            // Ordenará por puntos, asi el primero de la lista y de la liga será el que más tiene.
            ['puntos', 'DESC']
        ]
    })
        .then(
            data => {res.send(data);}
        )
        .catch(
            err => {
                res.status(500).send({
                  message:
                    err.message || "Some error occurred while retrieving clasificacion."
                });
              }
        );

};
