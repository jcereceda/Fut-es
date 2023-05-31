const db = require("../models");
const Arbitro = db.arbitro;
const Op = db.Sequelize.Op;

exports.getOneArbitro =  (req,res) => {
    const id = req.params.id;

    Arbitro.findByPk(id)
    .then(data => {
        if (data) {
          res.send(data);
        } else {
          res.status(404).send({
            message: `Cannot find Arbitro with id=${id}.`
          });
        }
      })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving Arbitros."
      });
    });
};
