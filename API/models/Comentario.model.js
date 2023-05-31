module.exports = (sequelize, Sequelize) => {	

    const Comentario = sequelize.define("comentario", {
    
    comentario: {
        type: Sequelize.STRING
    },
    fecha: {
        type: Sequelize.DATE      
    },
    id_usuario: {
        type: Sequelize.INTEGER
    },
    id_noticia: {
        type: Sequelize.INTEGER            
    }
}, {
    // Sequelize por defecto pluraliza el nombre de las tablas en la BD, con esto, lo evita
    freezeTableName: true
} );

return Comentario;

};