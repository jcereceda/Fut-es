module.exports = (sequelize, Sequelize) => {
    const Tarjeta = sequelize.define("tarjeta", {
      minuto: {
        type: Sequelize.INTEGER
      },
      id_jugador: {
        type: Sequelize.INTEGER
      },
      id_partido: {
        type: Sequelize.INTEGER
      },
      color : {
        type: Sequelize.STRING
      }
    }, {
      // Sequelize por defecto pluraliza el nombre de las tablas en la BD, con esto, lo evita
      freezeTableName: true
    } );
  
    return Tarjeta;

};