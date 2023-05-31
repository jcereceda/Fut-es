module.exports = (sequelize, Sequelize) => {
    const Usuario = sequelize.define("usuario", {
      nombre: {
        type: Sequelize.STRING
      },
      apellidos: {
        type: Sequelize.STRING
      },
      fechaNac: {
        type: Sequelize.DATE
      },
      foto: {
        type: Sequelize.STRING
      },
      fondo: {
        type: Sequelize.STRING
      },
      rol: {
        type: Sequelize.STRING
      },
      id_equipo_fav: {
        type: Sequelize.INTEGER
      },
      id_jugador_fav: {
        type: Sequelize.INTEGER
      },
      passwd: {
        type: Sequelize.STRING
      },
      email: {
        type: Sequelize.STRING
      }
    }, {
      // Sequelize por defecto pluraliza el nombre de las tablas en la BD, con esto, lo evita
      freezeTableName: true
    } );
  
    return Usuario;

};