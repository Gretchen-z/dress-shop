package ru.gretchen.dressshop.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.gretchen.dressshop.hiber.HibernateSessionFactoryUtil;
import ru.gretchen.dressshop.model.DressEntity;
import java.util.List;

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
        Transaction transaction = session.beginTransaction();
        List<DressEntity> dresses = session.createQuery("FROM DressEntity").list();
        session.close();
        return dresses;
    }
}
