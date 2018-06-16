package database.dataaccessobjekts;

import bean.Room;
import bean.User;
import database.utils.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    private static List<Room> rooms = null;

    public static List<Room> getAllRooms() {
        if (rooms != null){
            return rooms;
        }
        List<Room> roomList = new ArrayList<>();
        Connection con = DataAccess.getConnection();
        String sql = "select * from rooms";
        PreparedStatement pst = null ;
        Room ro = null;
        ResultSet rs = null ;
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                ro = new Room();
                ro.setTitle(rs.getString("title"));
                ro.setRoomId(rs.getString("room_id"));
                ro.setSize(rs.getInt("size"));
                ro.setLocation(rs.getString("location"));
                ro.setResponsiblePerson(rs.getString("responsible_person"));
                ro.setDescription(rs.getString("description"));
                ro.setBooker(UserDAO.getUserByid(rs.getString("user_id_room_fk")));
                roomList.add(ro);
                rooms.add(ro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try {
                if(rs!=null){
                    rs.close();
                }
                if(pst!=null){
                    pst.close();
                }
                if(con!=null){
                    con.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return roomList;
    }


    public static User getUserByid(String uid) {
        Connection con = DataAccess.getConnection();
        String sql = "select * from usr p where p.user_id=?";
        PreparedStatement pst = null ;
        User ro = null;
        ResultSet rs = null ;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, uid);
            rs = pst.executeQuery();
            if (rs.next()) {
                ro = new User();
                ro.setCredit(rs.getInt("credit"));
                ro.setSuperuser(rs.getBoolean("is_super_user"));
                ro.setUserName(rs.getString("user_name"));
                ro.setUserPassword(rs.getString("user_passwd"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try {
                if(rs!=null){
                    rs.close();
                }
                if(pst!=null){
                    pst.close();
                }
                if(con!=null){
                    con.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return ro;
    }

    public static User findUserByUsername(String username) {
        Connection con = DataAccess.getConnection();
        String sql = "select * from usr p where p.user_name=?";
        PreparedStatement pst = null ;
        User ro = null;
        ResultSet rs =  null ;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, username);
            rs = pst.executeQuery();
            if (rs.next()) {
                ro = new User();
                ro.setUserId(rs.getString("user_id"));
                ro.setCredit(rs.getInt("credit"));
                ro.setSuperuser(rs.getBoolean("is_super_user"));
                ro.setUserPassword(rs.getString("user_passwd"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                if(rs!=null){
                    rs.close();
                }
                if(pst!=null){
                    pst.close();
                }
                if(con!=null){
                    con.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return ro;
    }

    public static void updateUser(User ro) {
        Connection con = DataAccess.getConnection();
        String sql = "update usr set user_id=?,user_name=?,user_passwd=?,credit=?,is_super_user=? where id=?";
        PreparedStatement pst = null ;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, ro.getUserId());
            pst.setString(2, ro.getUserName());
            pst.setString(3, ro.getUserPassword());
            pst.setInt(4, ro.getCredit());
            pst.setBoolean(5, ro.isSuperuser());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                if(pst!=null){
                    pst.close();
                }
                if(con!=null){
                    con.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
