const db = require("../models");
const Noticia = db.noticia;
const Op = db.Sequelize.Op;
const sequelize = db.sequelize;

exports.getNoticias = (req,res) => {

    Noticia.findAll({
      order: [
        ['fecha', 'DESC']
      ]
    })
        .then(data => {
                res.send(data);
              })
        .catch(err => {
            res.status(500).send({
              message:
                err.message || "Some error occurred while retrieving noticias."
            });
        });
};


exports.getOneNoticia =  (req,res) => {
    const id = req.params.id;

    Noticia.findByPk(id)
    .then(data => {
        if (data) {
          res.send(data);
        } else {
          res.status(404).send({
            message: `Cannot find noticia with id=${id}.`
          });
        }
      })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving noticia."
      });
    });
};

exports.createNoticia = (req, res) => {

    // Validar peticion
    if(!req.body.titular){
        res.status(400).send({message: "Debe de estar el campo completo"})
        return;
    }

    // crear una noticia
    const noticia  = new Noticia({
        titular: req.body.titular,
        cuerpo: req.body.cuerpo,
        foto: req.body.foto,
        id_periodista: req.body.id_periodista,
        fecha: sequelize.fn("NOW")
    });
    
    // Guardar noticia en la BD
    noticia
        .save(noticia)
        .then(data =>{
            res.send(data)
        })
        .catch(err => {
            res.status(500).send({
                message: err.message || "Error ocurrido durante la creacion"
            });
        });
};


exports.deleteNoticia = (req, res) => {

  Noticia.destroy({
    where: { id: req.params,id}
  })
  .then(data => {
    if(!data){
        res.status(404).send({message: "No se puede eliminar porque no existe"});
    } else {
        res.send({message: "Eliminado satisfactoriamente"});
    }
  }).catch(err => {
      res.status(500).send({message: "Error mientras se eliminaba el libro"})
  });

};