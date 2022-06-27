package ru.gretchen.dressshop.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.AbstractWork;
import ru.gretchen.dressshop.hiber.HibernateSessionFactoryUtil;
import ru.gretchen.dressshop.model.DressEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Random;

public class DressRepository {

    public DressEntity getById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(DressEntity.class, id);
    }

    public DressEntity save(DressEntity dress) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(dress);
        transaction.commit();
        session.close();
        return dress;
    }

    public DressEntity update(Long id, DressEntity dress) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        DressEntity newDress = session.get(DressEntity.class, id);
        newDress.setColor(dress.getColor());
        newDress.setPrice(dress.getPrice());
        newDress.setInStock(dress.getInStock());
        session.update(newDress);
        transaction.commit();
        session.close();
        return newDress;
    }

    public void delete(Long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        DressEntity dress = (DressEntity) session.get(DressEntity.class, id);
        session.delete(dress);
        transaction.commit();
        session.close();
    }

    public List<DressEntity> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<DressEntity> dresses = session.createQuery("FROM DressEntity")
                .setFirstResult(0)
                .setMaxResults(100)
                .list();
        session.close();
        return dresses;
    }

    public long getCountOfDressWithPriceMoreThan2000() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        long count = (long) session.createQuery("SELECT count(*) FROM DressEntity WHERE price > 2000").uniqueResult();
        session.close();
        return count;
    }

    private void add20MillionsRows() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.doWork(new AbstractWork() {
            @Override
            public void execute(Connection connection) throws SQLException {
                String query = "INSERT INTO dress (color, price, in_stock) VALUES ('%d', %d, %d);";
                connection.setAutoCommit(false);

                Random random = new Random(1);

                Statement statement = connection.createStatement();
                for (int i = 0; i < 18; i++) {

                    for (int j = 0; j < 1_000_000; j++) {
                        statement.addBatch(
                                String.format(query, random.nextInt(11), random.nextInt(3000), random.nextInt(101)));

                    }
                    statement.executeBatch();
                    connection.commit();
                }
            }
        });
    }
}
