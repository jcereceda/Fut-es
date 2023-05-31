const db = require("../models");
const User = db.usuario;

checkDuplicateUsernameOrEmail = (req, res, next) => {
  // Username
  User.findOne({
    where: {
        email: req.body.email
      }
  }).then(user => {
    if (user) {
      res.status(400).send({
        message: "Failed! email is already in use!"
      });
      return;
    }
  });
};


const verifySignUp = {
  checkDuplicateUsernameOrEmail: checkDuplicateUsernameOrEmail
};

module.exports = verifySignUp;