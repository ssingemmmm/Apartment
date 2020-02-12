package com.xingzhi.apartment.repository;

import com.xingzhi.apartment.model.Apartment;
import com.xingzhi.apartment.model.RoomInfo;
import com.xingzhi.apartment.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ApartmentDaoImpl implements ApartmentDao{
    private Logger logger;
    private SessionFactory sessionFactory;
    @Autowired
    public ApartmentDaoImpl(Logger logger, SessionFactory sessionFactory) {
        this.logger = logger;
        this.sessionFactory = sessionFactory;
    }
    public boolean save(Apartment apartment) {
        Transaction transaction = null;
        boolean isSuccessful = false;
        try  {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(apartment);
            transaction.commit();
            isSuccessful = true;
        }
        catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }

        logger.debug(String.format("The apartment %s was inserted into the table.", apartment.toString()));
        return isSuccessful;
    }

    public int updateApartmentLowestPrice(String name, String lowestPrice) {
        String hql = "UPDATE Apartment as apt set apt.lowestPrice = :lowestPrice where apt.name = :name";
        int updatedCount = 0;
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()){

            Query<Apartment> query = session.createQuery(hql);
            query.setParameter("name", name);
            query.setParameter("lowestPrice", lowestPrice);

            transaction = session.beginTransaction();
            updatedCount = query.executeUpdate();
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }

        logger.debug(String.format("The apartment %s was updated, total updated record(s): %d", name, updatedCount));

        return updatedCount;
    }

    public List<Apartment> getApartments() {
        String hql = "FROM Apartment";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Apartment> query = session.createQuery(hql);
            return query.list();
        }
    }

    @Override
    public Apartment getApartmentByName(String name) {
        String hql = "FROM Apartment as apt where apt.name = :name";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Apartment> query = session.createQuery(hql);
            query.setParameter("name", name);
            return query.uniqueResult();
        }
    }

    public Apartment getApartmentById(int id) {
        String hql = "FROM Apartment where id = :id";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Apartment> query = session.createQuery(hql);
            query.setParameter("id", id);
            return query.uniqueResult();
        }
    }

    @Override
    public boolean deleteApartmentByName(String name) {
        String hql = "DELETE Apartment where name = :name";
        int deletedCount = 0;
        Transaction transaction = null;

        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query<Apartment> query = session.createQuery(hql);
            query.setParameter("name", name);
            deletedCount = query.executeUpdate();
            //Apartment apt = getAmentByName(deptName);
            //session.delete(apt);
            transaction.commit();
            deletedCount = 1;
        }
        catch (Exception e) {
            if (transaction != null) transaction.rollback();
//            logger.error(e.getMessage());
        }

       logger.debug(String.format("The apartment %s was deleted", name));

        return deletedCount >= 1 ? true : false;
    }

    @Override
    public List<Apartment> getApartmentByNameWithAllRoomInfo(String name) {
        if (name == null) return null;

        String hql = "FROM Apartment as apt left join fetch apt.roomInfos where apt.name = :name";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Apartment> query = session.createQuery(hql);
            query.setParameter("name", name);
            return query.list().stream().distinct().collect(Collectors.toList());
        }
    }
}
