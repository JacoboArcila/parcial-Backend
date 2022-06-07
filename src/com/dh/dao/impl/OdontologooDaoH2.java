package com.dh.dao.impl;

import com.dh.dao.IDao;
import com.dh.model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologooDaoH2 implements IDao<Odontologo> {

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/test;INIT=RUNSCRIPT FROM 'create.sql'";
    private final static String DB_USER = "";
    private final static String DB_PASSWORD = "";

    private final static Logger logger = Logger.getLogger(OdontologooDaoH2.class);


    @Override
    public Odontologo guardar(Odontologo odontologo) throws Exception {
        logger.info("Guardando odontologo " + odontologo.toString() );

        Connection connection = null;

        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("INSERT INTO odontologos(nombre, apellido, numeroMatricula) VALUES(?,?,?)");
            preparedStatement.setString(1, odontologo.getNombre());
            preparedStatement.setString(2, odontologo.getApellido());
            preparedStatement.setString(3, odontologo.getMatricula());
            preparedStatement.execute();



        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        preparedStatement.close();
        connection.close();

        return odontologo;
    }

    @Override
    public List<Odontologo> buscarTodos() throws SQLException {
        logger.info("Lista de odontologos ");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Odontologo> odontologos = new ArrayList<>();

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("SELECT * FROM odontologos");

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Odontologo odontologo = new Odontologo(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getString("numeroMatricula")
                );
                odontologos.add(odontologo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        preparedStatement.close();
        connection.close();

        return odontologos;
    }

    @Override
    public Odontologo buscarId(Integer id) throws Exception {
        logger.info("ID odontologo ");
        Connection connection = null;
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM odontologos WHERE id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Odontologo odontologo = new Odontologo();

        if (resultSet.next()) {
            odontologo.setId(resultSet.getInt("id"));
            odontologo.setNombre(resultSet.getString("nombre"));
            odontologo.setApellido(resultSet.getString("apellido"));
            odontologo.setMatricula(resultSet.getString("numeroMatricula"));
        } else {
            throw new Exception("No existe ningun odontologo con ese ID");
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
        return odontologo;
    }
}
