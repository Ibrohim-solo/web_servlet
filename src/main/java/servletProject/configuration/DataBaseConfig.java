package servletProject.configuration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;
public class DataBaseConfig {
    private static Properties properties;
    private static DriverManagerDataSource dataSource;
    private static DataBaseConfig dataBaseConfig;
    private static SessionFactory sessionFactory;
    private static Session session;
//    private Environment environment;
    private DataBaseConfig(){
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static DataBaseConfig getInstance(){
        if (dataBaseConfig != null){
            return dataBaseConfig;
        }

        dataBaseConfig = new DataBaseConfig();
        return dataBaseConfig;
    }

    public static DataSource dataSourcePostgres(){
        if (dataSource != null){
            return dataSource;
        }
        dataSource = new DriverManagerDataSource();
        dataSource.setUrl(properties.getProperty("database.url"));
        dataSource.setUsername(properties.getProperty("database.username"));
        dataSource.setPassword(properties.getProperty("database.password"));
        dataSource.setDriverClassName(properties.getProperty("database.driver.classname"));

        return dataSource;
    }

    public static SessionFactory sessionFactory(){
        if (sessionFactory != null){
            return sessionFactory;
        }

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate/hibernate.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources(serviceRegistry).buildMetadata();

        sessionFactory = metadata.buildSessionFactory();
        return sessionFactory;
    }

    public static Session getSession(){
        if (session != null){
            return session;
        }

        sessionFactory = sessionFactory();
        session = sessionFactory.openSession();
        return session;
    }
}
