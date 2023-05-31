module.exports = (sequelize, Sequelize) => {
    const Liga = sequelize.define("liga", {
      nombre: {
        type: Sequelize.STRING
      },
      campeon: {
        type: Sequelize.STRING
      }
    }, {
      // Sequelize por defecto pluraliza el nombre de las tablas en la BD, con esto, lo evita
      freezeTableName: true
    } );
  
    return Liga;

};