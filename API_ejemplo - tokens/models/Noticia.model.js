module.exports = (sequelize, Sequelize) => {	

    const Noticia = sequelize.define("noticia", {
        titular: {
        type: Sequelize.STRING
      },
      cuerpo: {
        type: Sequelize.STRING
      },
      foto: {
        type: Sequelize.STRING            
      },
      id_periodista: {
        type: Sequelize.INTEGER
      },
      fecha: {
        type: Sequelize.DATE
      }
    }, {
      // Sequelize por defecto pluraliza el nombre de las tablas en la BD, con esto, lo evita
      freezeTableName: true
    } );
  
    return Noticia;

};