package org.example.DAO;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOFactory implements IDAOFactory {
    private static IDAOFactory factory;

    private DAOFactory() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver").getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static synchronized IDAOFactory get_instance() {
        if (factory == null)
            factory = new DAOFactory();

        return factory;
    }

    @Override
    public CourseDAOImp getCourseDAO() {
        return new CourseDAOImp();
    }

    @Override
    public StudentDAOImp getStudentDAO() {
        return new StudentDAOImp();
    }

    public static Connection getConnection() throws IOException, SQLException {
        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("database.properties"))) {
            props.load(in);
        }
        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");
        return DriverManager.getConnection(url, username, password);
    }
}

