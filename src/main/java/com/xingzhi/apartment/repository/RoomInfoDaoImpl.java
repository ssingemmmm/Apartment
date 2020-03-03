package com.xingzhi.apartment.repository;

import com.xingzhi.apartment.model.Apartment;
import com.xingzhi.apartment.model.RoomInfo;
import com.xingzhi.apartment.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class RoomInfoDaoImpl implements RoomInfoDao {
    private Logger logger;
    private SessionFactory sessionFactory;
    @Autowired
    public RoomInfoDaoImpl(Logger logger, SessionFactory sessionFactory) {
        this.logger = logger;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean save(RoomInfo roomInfo) {
            Transaction transaction = null;
            boolean isSuccessful = false;
            try  {
                Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                transaction = session.beginTransaction();
                session.save(roomInfo);
                transaction.commit();
                isSuccessful = true;
            }
            catch (Exception e) {
                if (transaction != null) transaction.rollback();
                logger.error(e.getMessage());
            }
            logger.debug(String.format("The account %s was inserted into the table.", roomInfo.toString()));
            return isSuccessful;
    }

    @Override
    public int updateRoomInfoPrice(Integer id, String priceRange) {
        String hql = "UPDATE RoomInfo as rm set rm.priceRange = :priceRange where rm.id = :id";
        int updatedCount = 0;
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Apartment> query = session.createQuery(hql);
            query.setParameter("id", id);
            query.setParameter("priceRange", priceRange);

            transaction = session.beginTransaction();
            updatedCount = query.executeUpdate();
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }

        logger.debug(String.format("The roomInfo %d was updated, total updated record(s): %d", id, updatedCount));

        return updatedCount;
    }

    @Override
    public int updateRoomInfo(Integer id, RoomInfo roomInfo) {
        int updatedCount = 0;
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(roomInfo);
            transaction.commit();
            updatedCount++;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        logger.debug(String.format("The roomInfo %d was updated, total updated record(s): %d", id, updatedCount));
        return updatedCount;
    }

    @Override
    //@Cacheable(cacheNames = "roomInfos")
    public List<RoomInfo> getRoomInfos() {
        String hql = "FROM RoomInfo ";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<RoomInfo> query = session.createQuery(hql);
            return query.list();
        }
    }

    @Override
    //@Cacheable(cacheNames = "roomInfos")
    public List<RoomInfo> getRoomInfoByApartmentName(String name) {
        String hql = "FROM RoomInfo as rm where lower(rm.apartment.name) = :name";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<RoomInfo> query = session.createQuery(hql);
            query.setParameter("name", name.toLowerCase());
            List<RoomInfo> roomInfos = query.list();
            roomInfos.forEach(System.out::println);

            return roomInfos;
        }
    }

    @Override
    public RoomInfo getRoomInfoById(Integer id){
        String hql = "FROM RoomInfo where id = :id";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<RoomInfo> query = session.createQuery(hql);
            query.setParameter("id", id);
            return query.uniqueResult();
        }
    }

    @Override
    public boolean deleteRoomInfoById(Integer id) {
        String hql = "DELETE RoomInfo where id = :id";
        int deletedCount = 0;
        Transaction transaction = null;

        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query<Apartment> query = session.createQuery(hql);
            query.setParameter("id", id);
            deletedCount = query.executeUpdate();
            transaction.commit();
            deletedCount = 1;
        }
        catch (Exception e) {
            if (transaction != null) transaction.rollback();
//            logger.error(e.getMessage());
        }

        logger.debug(String.format("The roomInfo %d was deleted", id));

        return deletedCount >= 1 ? true : false;
    }

    @Override
    public RoomInfo getRoomInfoByNameSize(String name, String size) {
        String hql = "FROM RoomInfo as rm where lower(rm.apartment.name)=lower(:name) AND lower(rm.size)=lower(:size)";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<RoomInfo> query = session.createQuery(hql);
            query.setParameter("name", name);
            query.setParameter("size", size);
            return query.uniqueResult();
        }
    }

    @Override
    public RoomInfo getRoomInfoByNamePriceRange(String name, String priceRange) {
        String hql = "FROM RoomInfo as rm where lower(rm.apartment.name)= :name and lower(rm.priceRange)=:priceRange";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<RoomInfo> query = session.createQuery(hql);
            query.setParameter("name", name.toLowerCase());
            query.setParameter("priceRange", priceRange.toLowerCase());
            return query.uniqueResult();
        }
    }
}
