package ru.gretchen.dressshop.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.gretchen.dressshop.hiber.HibernateSessionFactoryUtil;
import ru.gretchen.dressshop.model.DressEntity;
import ru.gretchen.dressshop.model.ShopEntity;

import java.util.List;
import java.util.Set;

public class ShopRepository {

    public ShopEntity getById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .get(ShopEntity.class, id);
    }

    public Set<DressEntity> getDresses(Long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        ShopEntity shop = session.get(ShopEntity.class, id);

        //LazyInitializationException example
        session.close();
        Set<DressEntity> dresses = shop.getDresses();
        DressEntity next = dresses.iterator().next();

        return shop.getDresses();
    }

    public ShopEntity save(ShopEntity shop) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(shop);
        transaction.commit();
        session.close();
        return shop;
    }

    public ShopEntity update(Long id, ShopEntity shop) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        ShopEntity newShop = session.get(ShopEntity.class, id);
        newShop.setAddress(shop.getAddress());
        session.update(newShop);
        transaction.commit();
        session.close();
        return newShop;
    }

    public ShopEntity addDress(Long id, Long dressId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        ShopEntity newShop = session.get(ShopEntity.class, id);
        DressEntity dress = session.get(DressEntity.class, dressId);
        newShop.addDress(dress);
        session.update(newShop);
        transaction.commit();
        session.close();
        return newShop;
    }

    public ShopEntity deleteDress(Long id, Long dressId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        ShopEntity newShop = session.get(ShopEntity.class, id);
        DressEntity dress = session.get(DressEntity.class, dressId);
        newShop.deleteDress(dress);
        session.update(newShop);
        transaction.commit();
        session.close();
        return newShop;
    }

    public void delete(Long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        ShopEntity shop = (ShopEntity) session.get(ShopEntity.class, id);
        session.delete(shop);
        transaction.commit();
        session.close();
    }

    //N+1 problem resolve
    public List<ShopEntity> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<ShopEntity> shops = session
                .createQuery("FROM ShopEntity s JOIN FETCH s.dresses", ShopEntity.class)
                .getResultList();
        session.close();
        return shops;
    }
}
