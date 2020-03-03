package com.xingzhi.apartment.repository;

import com.xingzhi.apartment.model.Apartment;
import com.xingzhi.apartment.model.PropertyInfo;
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

@Repository
public class PropertyInfoDaoImpl implements PropertyInfoDao {
    private Logger logger;
    private SessionFactory sessionFactory;
    @Autowired
    public PropertyInfoDaoImpl(Logger logger, SessionFactory sessionFactory) {
        this.logger = logger;
        this.sessionFactory = sessionFactory;
    }
    //propertyInfo.setApartment(apt);
    @Override
    public boolean save(PropertyInfo propertyInfo) {
        Transaction transaction = null;
        boolean isSuccessful = false;
        try  {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(propertyInfo);
            transaction.commit();
            isSuccessful = true;
        }
        catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }

        logger.debug(String.format("The propertyInfo %s was inserted into the table.", propertyInfo.toString()));
        return isSuccessful;
    }

    @Override
    public int updatePropertyInfo(Integer id, PropertyInfo propertyInfo) {
        int updatedCount = 0;
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(propertyInfo);
            transaction.commit();
            updatedCount++;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        logger.debug(String.format("The propertyInfo %d was updated, total updated record(s): %d", id, updatedCount));
        return updatedCount;
    }

    @Override
    //@Cacheable(cacheNames = "propertyInfos")
    public List<PropertyInfo> getPropertyInfos() {
        String hql = "FROM PropertyInfo";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<PropertyInfo> query = session.createQuery(hql);
            return query.list();
        }
    }

    @Override
    public PropertyInfo getPropertyInfoByName(String name) {
        String hql = "FROM PropertyInfo as pi where lower(pi.apartment.name) = :name";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<PropertyInfo> query = session.createQuery(hql);
            query.setParameter("name", name.toLowerCase());
            return query.uniqueResult();
        }
    }

    @Override
    public PropertyInfo getPropertyInfoById(Integer id) {
        String hql = "FROM PropertyInfo as pi where pi.id = :id";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<PropertyInfo> query = session.createQuery(hql);
            query.setParameter("id", id);
            return query.uniqueResult();
        }
    }

    @Override
    public boolean deletePropertyInfoById(Integer id) {
        String hql = "DELETE PropertyInfo where id = :id";
        int deletedCount = 0;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query<PropertyInfo> query = session.createQuery(hql);
            query.setParameter("id", id);
            deletedCount = query.executeUpdate();
            transaction.commit();
            deletedCount = 1;
        }
        catch (Exception e) {
            if (transaction != null) transaction.rollback();
//            logger.error(e.getMessage());
        }
        logger.debug(String.format("The propertyInfo %d was deleted", id));
        return deletedCount >= 1 ? true : false;
    }
}
