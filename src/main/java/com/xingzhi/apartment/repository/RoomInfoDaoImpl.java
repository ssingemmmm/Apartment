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
    public void save(RoomInfo roomInfo) {
            Transaction transaction = null;
            try  {
                Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                transaction = session.beginTransaction();
                session.save(roomInfo);
                transaction.commit();
            }
            catch (Exception e) {
                if (transaction != null) transaction.rollback();
                logger.error(e.getMessage());
            }
            logger.debug(String.format("The account %s was inserted into the table.", roomInfo.toString()));
    }

    @Override
    public int updateRoomInfoPrice(int id, String priceRange) {
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
    public List<RoomInfo> getRoomInfos() {
        String hql = "FROM RoomInfo ";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<RoomInfo> query = session.createQuery(hql);
            return query.list();
        }
    }

    @Override
    public List<RoomInfo> getRoomInfoByApartmentName(String name) {
        String hql = "FROM RoomInfo as rm where rm.apartment.name = :name";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<RoomInfo> query = session.createQuery(hql);
            query.setParameter("name", name);
            List<RoomInfo> roomInfos = query.list();
            roomInfos.forEach(System.out::println);

            return roomInfos;
        }
    }

    @Override
    public RoomInfo getRoomInfoById(int id){
        String hql = "FROM RoomInfo where id = :id";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<RoomInfo> query = session.createQuery(hql);
            query.setParameter("id", id);
            return query.uniqueResult();
        }
    }

    @Override
    public boolean deleteRoomInfoById(int id) {
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
}
