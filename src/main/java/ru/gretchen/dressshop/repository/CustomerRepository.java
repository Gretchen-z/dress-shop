package ru.gretchen.dressshop.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.gretchen.dressshop.hiber.HibernateSessionFactoryUtil;
import ru.gretchen.dressshop.model.CustomerEntity;

import java.util.List;

public class CustomerRepository {

    public CustomerEntity getById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .get(CustomerEntity.class, id);
    }

    public CustomerEntity save(CustomerEntity customer) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
        session.close();
        return customer;
    }

    public CustomerEntity update(Long id, CustomerEntity customer) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        CustomerEntity newCustomer = session.get(CustomerEntity.class, id);
        newCustomer.setFirstName(customer.getFirstName());
        newCustomer.setLastName(customer.getLastName());
        newCustomer.setPhoneNumber(customer.getPhoneNumber());
        session.update(newCustomer);
        transaction.commit();
        session.close();
        return newCustomer;
    }

    public void delete(Long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        CustomerEntity customer = (CustomerEntity) session.get(CustomerEntity.class, id);
        session.delete(customer);
        transaction.commit();
        session.close();
    }

    public List<CustomerEntity> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<CustomerEntity> customers = session.createQuery("FROM CustomerEntity").list();
        session.close();
        return customers;
    }
}
