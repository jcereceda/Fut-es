module.exports = (sequelize, Sequelize) => {
    const Jugador = sequelize.define("jugador", {
      nombre: {
        type: Sequelize.STRING
      },
      apodo: {
        type: Sequelize.STRING
      },
      peso: {
        type: Sequelize.INTEGER            
      },
      altura: {
        type: Sequelize.FLOAT
      },
      foto: {
        type: Sequelize.STRING
      },
      fondo: {
        type: Sequelize.STRING
      },
      dorsal: {
        type: Sequelize.INTEGER
      },
      id_equipo: {
        type: Sequelize.INTEGER
      },
      posicion: {
        type: Sequelize.STRING
      }
    }, {
      // Sequelize por defecto pluraliza el nombre de las tablas en la BD, con esto, lo evita
      freezeTableName: true
    } );
  
    return Jugador;

};