package ru.gretchen.dressshop.hiber;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.gretchen.dressshop.exception.GetSessionException;
import ru.gretchen.dressshop.model.CustomerEntity;
import ru.gretchen.dressshop.model.DressEntity;
import ru.gretchen.dressshop.model.ShopEntity;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(DressEntity.class);
                configuration.addAnnotatedClass(ShopEntity.class);
                configuration.addAnnotatedClass(CustomerEntity.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                throw new GetSessionException(e.getMessage());
            }
        }
        return sessionFactory;
    }
}
