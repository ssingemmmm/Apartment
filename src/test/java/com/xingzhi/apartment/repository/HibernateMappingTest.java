package com.xingzhi.apartment.repository;



import com.xingzhi.apartment.init.AppInitializer;
import com.xingzhi.apartment.model.Apartment;
import com.xingzhi.apartment.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= AppInitializer.class)
public class HibernateMappingTest {
    @Autowired
    Logger logger;
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