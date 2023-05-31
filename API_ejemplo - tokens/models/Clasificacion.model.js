module.exports = (sequelize, Sequelize) => {	

    const Clasificacion = sequelize.define("clasificacion", {
    
    id_liga: {
        type: Sequelize.INTEGER
    },
    id_equipo: {
        type: Sequelize.INTEGER
    },
    puntos: {
        type: Sequelize.INTEGER            
    }
}, {
    // Sequelize por defecto pluraliza el nombre de las tablas en la BD, con esto, lo evita
    freezeTableName: true
} );

return Clasificacion;

};