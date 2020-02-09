package com.xingzhi.apartment.JDBC;

import com.xingzhi.apartment.model.Apartment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApartmentDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    //Step1:database information
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/apartment_db";
    private static final String USER="admin";
    private static final String PASS="Training123!";

    //get all infos
    public List<Apartment> getApartment() {
        List<Apartment> apartments = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //stept2:open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //step 3:execute a query
            stmt = conn.createStatement();
            String sql = "SELECT * FROM apartment";
            rs = stmt.executeQuery(sql);
            //step 4: extract data from result set
            while (rs.next()) {
                int propertyInfoId = rs.getInt("property_info_id");
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String lowestPrice = rs.getString("lowest_price");
                String smallestSize = rs.getString("smallest_size");
                String photo = rs.getString("photo");
                Apartment apartment = new Apartment();

                apartment.setId(id);
                apartment.setLowestPrice(lowestPrice);
                apartment.setName(name);
                apartment.setSmallestSize(smallestSize);
                apartment.setPhoto(photo);
                apartments.add(apartment);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            //close resources
            try{
                if(rs != null)rs.close();
                if(stmt !=null) stmt.close();
                if(conn!= null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        logger.info("exit the method getApartment");
        return apartments;
    }

    //insert data
    public void addApartment(Apartment apartment){
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            //stept2:open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //step 3:execute a query
            String sql = "insert into apartment (id,name,lowest_price,smallest_size,photo) values(?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, apartment.getId());
            stmt.setString(2, apartment.getName());
            stmt.setString(3, apartment.getLowestPrice());
            stmt.setString(4, apartment.getSmallestSize());
            stmt.setString(5, apartment.getPhoto());

            stmt.execute();
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        logger.info("exit the method addApartment");
    }

    //delete data
    public void deleteApartment(int id){
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            //stept2:open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //step 3:execute a query
            String sql = "delete from apartment where id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        logger.info("exit the method deleteApartment");
    }

    //insert data
    public void updateApartment(Apartment apartment){
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            //stept2:open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //step 3:execute a query
            String sql = "update apartment set name=?,lowest_price=?,smallest_size=?,photo=? where id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, apartment.getName());
            stmt.setString(2, apartment.getLowestPrice());
            stmt.setString(3, apartment.getSmallestSize());
            stmt.setString(4, apartment.getPhoto());

            stmt.setInt(5, apartment.getId());
            stmt.execute();
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        logger.info("exit the method updateApartment");
    }

    //search apartment
    public Apartment searchApartment(int id){
        Apartment apartment = new Apartment();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs=null;
        try {
            //stept2:open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //step 3:execute a query
            String sql = "select * from apartment where id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs=stmt.executeQuery();
            //step 4: extract data from result set
            while (rs.next()) {
                String name = rs.getString("name");
                String lowestPrice = rs.getString("lowest_price");
                String smallestSize = rs.getString("smallest_size");
                String photo = rs.getString("photo");
                int propertyInfoId = rs.getInt("property_info_id");
                apartment.setId(id);
                apartment.setLowestPrice(lowestPrice);
                apartment.setName(name);
                apartment.setSmallestSize(smallestSize);
                apartment.setPhoto(photo);

            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        logger.info("exit the method searchApartment");

        return apartment;
    }
}
