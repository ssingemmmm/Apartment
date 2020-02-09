package com.xingzhi.apartment.repository;



import com.xingzhi.apartment.model.Apartment;
import com.xingzhi.apartment.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HibernateMappingTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Test
    public void mappingTest() {
        String hql = "FROM Apartment";
        List<Apartment> apartments = null;

        try (
                Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Apartment> query = session.createQuery(hql);
            apartments = query.list();
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }

        apartments.forEach(dept -> logger.info(dept.toString()));

        Assert.assertNotNull(apartments);
    }
}