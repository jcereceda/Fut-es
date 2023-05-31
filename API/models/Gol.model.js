module.exports = (sequelize, Sequelize) => {
    const Gol = sequelize.define("gol", {
      minuto: {
        type: Sequelize.INTEGER
      },
      id_jugador: {
        type: Sequelize.INTEGER
      },
      id_partido: {
        type: Sequelize.INTEGER
      }
    }, {
      // Sequelize por defecto pluraliza el nombre de las tablas en la BD, con esto, lo evita
      freezeTableName: true
    } );
  
    return Gol;

};