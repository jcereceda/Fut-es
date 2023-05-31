const jwt = require("jsonwebtoken");
const config = require("../config/auth.config.js");
const db = require("../models");
const User = db.usuario;

verifyToken = (req, res, next) => {
  let token = req.headers["x-access-token"];

  if (!token) {
    return res.status(403).send({
      message: "No token provided!"
    });
  }

  jwt.verify(token, config.secret, (err, decoded) => {
    if (err) {
      return res.status(401).send({
        message: "Unauthorized!"
      });
    }
    req.id = decoded.id;
    next();
  });
};


isAdmin = (req, res, next) => {
  User.findByPk(req.id).then(user => {
    if(user.rol == "admin"){
        next();
        return;
    }
    res.status(403).send({
        message: "Necesario Rol de admin"
      });
      return;
  });
};

isCreador = (req, res, next) => {
    User.findByPk(req.id).then(user => {
      if(user.rol == "creador"){
          next();
          return;
      }
      res.status(403).send({
          message: "Necesario Rol de creador"
        });
        return;
    });
  };
  

isPeriodista = (req, res, next) => {
User.findByPk(req.id).then(user => {
    if(user.rol == "periodista"){
        next();
        return;
    }
    res.status(403).send({
        message: "Necesario Rol de periodista"
    });
    return;
});
};

const authJwt = {
  verifyToken: verifyToken,
  isAdmin: isAdmin,
  isCreador: isCreador,
  isPeriodista: isPeriodista
};

module.exports = authJwt;