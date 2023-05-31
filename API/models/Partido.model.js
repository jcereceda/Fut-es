module.exports = (sequelize, Sequelize) => {
    const Partido = sequelize.define("partido", {
      goles_local: {
        type: Sequelize.INTEGER
      },
      goles_visitante: {
        type: Sequelize.INTEGER
      },
      posesion_local: {
        type: Sequelize.INTEGER
      },
      posesion_visitante: {
        type: Sequelize.INTEGER
      },
      fecha: {
        type: Sequelize.DATE      
      },
      pases_local: {
        type: Sequelize.INTEGER
      },
      pases_visitante: {
        type: Sequelize.INTEGER
      },
      tiros_local: {
        type: Sequelize.INTEGER
      },
      tiros_visitante: {
        type: Sequelize.INTEGER
      },
      corners_local: {
        type: Sequelize.INTEGER
      },
      corners_visitante: {
        type: Sequelize.INTEGER
      },
      faltas_local: {
        type: Sequelize.INTEGER
      },
      faltas_visitante: {
        type: Sequelize.INTEGER
      },
      id_equipo_local: {
        type: Sequelize.INTEGER
      },
      id_equipo_visitante: {
        type: Sequelize.INTEGER
      },
      id_liga: {
        type: Sequelize.INTEGER
      },
      id_creador: {
        type: Sequelize.INTEGER
      },
      terminado: {
        type: Sequelize.BOOLEAN
      },
      id_arbitro: {
        type: Sequelize.INTEGER
      }
    }, {
      // Sequelize por defecto pluraliza el nombre de las tablas en la BD, con esto, lo evita
      freezeTableName: true
    } );
  
    return Partido;

};