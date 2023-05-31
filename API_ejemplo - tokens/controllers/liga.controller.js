const db = require("../models");
const Liga = db.liga;
const Op = db.Sequelize.Op;
const sequelize = db.sequelize;

exports.getLiga =  (req,res) => {
    const id = req.params.id;

    Liga.findByPk(id)
    .then(data => {
        if (data) {
          res.send(data);
        } else {
          res.status(404).send({
            message: `Cannot find liga  with id=${id}.`
          });
        }
      })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving partdos."
      });
    });
};