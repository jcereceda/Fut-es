module.exports = (sequelize, Sequelize) => {
  const Arbitro = sequelize.define(
    "arbitro",
    {
      nombre_completo: {
        type: Sequelize.STRING,
      },
    },
    {
      // Sequelize por defecto pluraliza el nombre de las tablas en la BD, con esto, lo evita
      freezeTableName: true,
    }
  );

  return Arbitro;
};
