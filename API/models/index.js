const dbConfig = require("../config/db.config.js");

const Sequelize = require("sequelize");
const sequelize = new Sequelize(dbConfig.DB, dbConfig.USER, dbConfig.PASSWORD, { 
    host: dbConfig.HOST,
  dialect: dbConfig.dialect,
  operatorsAliases: true,

  pool: {
    max: dbConfig.pool.max,
    min: dbConfig.pool.min,
    acquire: dbConfig.pool.acquire,
    idle: dbConfig.pool.idle
  },
    define: {
    scopes: {
     excludeCreatedAtUpdateAt: {
       attributes: { exclude: ['createdAt', 'updatedAt'] } // Evitar createdat al crear tablas
     }
    },
    timestamps: false 
 }
});

const db = {};

db.Sequelize = Sequelize;
db.sequelize = sequelize;

db.equipo = require("./Equipo.model.js")(sequelize, Sequelize);
db.jugador = require("./Jugador.model.js")(sequelize, Sequelize);
db.gol = require("./Gol.model.js")(sequelize, Sequelize);
db.asistencia = require("./Asistencia.model.js")(sequelize, Sequelize);
db.tarjeta = require("./Tarjeta.model.js")(sequelize, Sequelize);
db.partido = require("./Partido.model.js")(sequelize, Sequelize);
db.arbitro = require("./Arbitro.model.js")(sequelize, Sequelize);
db.usuario = require("./Usuario.model.js")(sequelize, Sequelize);
db.noticia = require("./Noticia.model.js")(sequelize, Sequelize);
db.clasificacion = require("./Clasificacion.model.js")(sequelize, Sequelize);
db.comentario = require("./Comentario.model.js")(sequelize, Sequelize);
db.liga = require("./Liga.model.js")(sequelize, Sequelize);

module.exports = db;