package com.xingzhi.apartment.JDBC;

import com.xingzhi.apartment.model.RoomInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomInfoDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    //Step1:database information
    private static final String DB_URL = System.getProperty("database.url");;
    private static final String USER=System.getProperty("database.user");;
    private static final String PASS=System.getProperty("database.password");;

    //get all infos
    public List<RoomInfo> getRoomInfo() {
        List<RoomInfo> roomInfos = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;


        try {
            //stept2:open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //step 3:execute a query
            stmt = conn.createStatement();
            String sql = "SELECT * FROM roominfo";
            rs = stmt.executeQuery(sql);
            //step 4: extract data from result set
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String size = rs.getString("size");
                String priceRange = rs.getString("price_range");
                String layoutPhoto = rs.getString("layout_photo");

                RoomInfo roomInfo = new RoomInfo();
                roomInfo.setId(id);

                roomInfo.setSize(size);
                roomInfo.setPriceRange(priceRange);
                roomInfo.setLayoutPhoto(layoutPhoto);
                roomInfos.add(roomInfo);
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
        return roomInfos;
    }

    //insert data
    public void addRoomInfo(RoomInfo roomInfo){
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            //stept2:open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //step 3:execute a query
            String sql = "insert into roominfo (id,size,price_range,layout_photo,apartment_id) values(?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, roomInfo.getId());
            stmt.setString(2, roomInfo.getSize());
            stmt.setString(3, roomInfo.getPriceRange());
            stmt.setString(4, roomInfo.getLayoutPhoto());
            stmt.setInt(5, roomInfo.getApartment().getId());
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
        logger.info("exit the method addRoomInfo");
    }

    //delete data
    public void deleteRoomInfo(Integer id){
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            //stept2:open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //step 3:execute a query
            String sql = "delete from roominfo where id=?";
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
        logger.info("exit the method deleteRoomInfo");
    }

    //insert data
    public void updateRoomInfo(RoomInfo roomInfo){
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            //stept2:open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //step 3:execute a query
            String sql = "update roominfo set size=?,price_range=?,layout_photo=? where id=?";
            stmt = conn.prepareStatement(sql);


            stmt.setString(1, roomInfo.getSize());
            stmt.setString(2, roomInfo.getPriceRange());
            stmt.setString(3, roomInfo.getLayoutPhoto());
            stmt.setInt(4, roomInfo.getId());
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
        logger.info("exit the method updateRoomInfo");
    }

    //search roomInfo
    public RoomInfo searchRoomInfo(Integer id){
        RoomInfo roomInfo = new RoomInfo();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs=null;
        try {
            //stept2:open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //step 3:execute a query
            String sql = "select * from roominfo where id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs=stmt.executeQuery();
            //step 4: extract data from result set
            while (rs.next()) {
                int apartmentId = rs.getInt("apartment_id");
                String size = rs.getString("size");
                String priceRange = rs.getString("price_range");
                String layoutPhoto = rs.getString("layout_photo");
                roomInfo.setId(id);
                roomInfo.setSize(size);
                roomInfo.setPriceRange(priceRange);
                roomInfo.setLayoutPhoto(layoutPhoto);
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

        return roomInfo;
    }

}
