module.exports = (sequelize, Sequelize) => {
    const Equipo = sequelize.define("equipo", {
      nombre: {
        type: Sequelize.STRING
      },
      estadio: {
        type: Sequelize.STRING
      },
      presi: {
        type: Sequelize.STRING
      },
      descripcion: {
        type: Sequelize.TEXT
      },
      escudo: {
        type: Sequelize.STRING
      },
      nombre_abrev: {
        type: Sequelize.STRING
      }
    }, {
      // Sequelize por defecto pluraliza el nombre de las tablas en la BD, con esto, lo evita
      freezeTableName: true
    } );
  
    return Equipo;

};