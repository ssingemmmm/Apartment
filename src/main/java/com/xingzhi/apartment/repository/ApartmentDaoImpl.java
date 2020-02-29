package com.xingzhi.apartment.repository;

import com.xingzhi.apartment.model.Apartment;
import com.xingzhi.apartment.model.PropertyInfo;
import com.xingzhi.apartment.model.RoomInfo;
import com.xingzhi.apartment.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

    public int updateApartmentLowestPrice(Integer id, String lowestPrice) {
        String hql = "UPDATE Apartment as apt set apt.lowestPrice = :lowestPrice where apt.id = :id";
        int updatedCount = 0;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Apartment> query = session.createQuery(hql);
            query.setParameter("id", id);
            query.setParameter("lowestPrice", lowestPrice);
            transaction = session.beginTransaction();
            updatedCount = query.executeUpdate();
            transaction.commit();
            updatedCount++;
        }
        catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        logger.debug(String.format("The apartment %d was updated, total updated record(s): %d", id, updatedCount));
        return updatedCount;
    }

    @Override
    public int updateApartment(Integer id ,Apartment apartment) {
        int updatedCount = 0;
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(apartment);
            transaction.commit();
            updatedCount++;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        logger.debug(String.format("The apartment %d was updated, total updated record(s): %d", id, updatedCount));
        return updatedCount;
    }

    @Cacheable(cacheNames = "apartments")
    public List<Apartment> getApartments() {
        String hql = "FROM Apartment";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Apartment> query = session.createQuery(hql);
            return query.list();
        }
    }

    @Override
    @Cacheable(cacheNames = "apartments")
    public Apartment getApartmentByName(String name) {
        String hql = "FROM Apartment as apt where lower(apt.name) = :name";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Apartment> query = session.createQuery(hql);
            query.setParameter("name", name.toLowerCase());
            return query.uniqueResult();
        }
    }

    public Apartment getApartmentById(Integer id) {
        String hql = "FROM Apartment where id = :id";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Apartment> query = session.createQuery(hql);
            query.setParameter("id", id);
            return query.uniqueResult();
        }
    }

    @Override
    public boolean deleteApartmentByName(String name) {
        String hql = "DELETE Apartment where lower(name) = :name";
        int deletedCount = 0;
        Transaction transaction = null;

        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query<Apartment> query = session.createQuery(hql);
            query.setParameter("name", name.toLowerCase());
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
    public boolean deleteApartmentById(Integer id) {
        String hql = "DELETE Apartment where id = :id";
        int deletedCount = 0;
        Transaction transaction = null;

        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query<Apartment> query = session.createQuery(hql);
            query.setParameter("id", id);
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

        logger.debug(String.format("The apartment %d was deleted", id));

        return deletedCount >= 1 ? true : false;
    }

    @Override
    public List<Apartment> getApartmentByNameWithAllRoomInfo(String name) {
        if (name == null) return null;

        String hql = "FROM Apartment as apt left join fetch apt.roomInfos where lower(apt.name) = :name";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Apartment> query = session.createQuery(hql);
            query.setParameter("name", name.toLowerCase());
            List<Apartment> resultList= query.list().stream().distinct().collect(Collectors.toList());
            for (Apartment obj : resultList) {
                logger.debug(obj.getRoomInfos().toString());
            }
            return resultList;
        }
    }
}
