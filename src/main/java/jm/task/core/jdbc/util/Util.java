package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {
    private static final String dbURL = "jdbc:mysql://localhost:3306/namebd";
    private static final String db_driver = "com.mysql.cj.jdbc.Driver";
    private static final String username = "root";
    private static final String password = "root";

    public static Connection getConnectuin() {
        Connection connection = null;
        try {
            Class.forName(db_driver);
            connection = DriverManager.getConnection(dbURL, username, password);
            System.out.println("Connection ok");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Connection ERROR");
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        Properties properties = new Properties();
        properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        properties.put(Environment.URL, "jdbc:mysql://localhost:3306/namebd");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "root");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        configuration.setProperties(properties);

        configuration.addAnnotatedClass(User.class);


        SessionFactory sessionFactory = configuration.addAnnotatedClass(User.class).addProperties(configuration.getProperties()).buildSessionFactory();

        return sessionFactory;

    }
}

