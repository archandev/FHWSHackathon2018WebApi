package database.dataaccessobjekts;

import bean.User;
import database.utils.DataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private static List<User> users = null;

    public static void insertUser (User user) {
        Connection con = DataAccess.getConnection();
        String sql = "insert into usr (user_id, user_name, user_passwd, credit, is_super_user) " +
                "VALUES (?, ?, ?, ?, ?);";
        PreparedStatement pstm = null;

        try {
            pstm = con.prepareStatement(sql);
            pstm.setString(1, user.getUserId());
            pstm.setString(2, user.getUserName());
            pstm.setString(3, user.getUserPassword());
            pstm.setInt(4, user.getCredit());
            pstm.setBoolean(5, user.getSuperuser());
            pstm.execute();

            if (users != null)
                users.add(user);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try {
                if(pstm!=null){
                    pstm.close();
                }
                if(con!=null){
                    con.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static List<User> getAllUsers() {
        if (users != null){
            return users;
        }
        List<User> userList = new ArrayList<>();
        Connection con = DataAccess.getConnection();
        String sql = "select * from usr";
        PreparedStatement pst = null ;
        User pu = null;
        ResultSet rs = null ;
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                pu = new User();
                pu.setUserId(rs.getString("user_id"));
                pu.setCredit(rs.getInt("credit"));
                pu.setSuperuser(rs.getBoolean("is_super_user"));
                pu.setUserName(rs.getString("user_name"));
                pu.setUserPassword(rs.getString("user_passwd"));
                userList.add(pu);
                users.add(pu);
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
        return userList;
    }


    public static User getUserByid(String uid) {
        Connection con = DataAccess.getConnection();
        String sql = "select * from usr p where p.user_id=?";
        PreparedStatement pst = null ;
        User pu = null;
        ResultSet rs = null ;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, uid);
            rs = pst.executeQuery();
            if (rs.next()) {
                pu = new User();
                pu.setCredit(rs.getInt("credit"));
                pu.setSuperuser(rs.getBoolean("is_super_user"));
                pu.setUserName(rs.getString("user_name"));
                pu.setUserPassword(rs.getString("user_passwd"));
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
        return pu;
    }

    public static User findUserByUsername(String username) {
        Connection con = DataAccess.getConnection();
        String sql = "select * from usr p where p.user_name=?";
        PreparedStatement pst = null ;
        User pu = null;
        ResultSet rs =  null ;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, username);
            rs = pst.executeQuery();
            if (rs.next()) {
                pu = new User();
                pu.setUserId(rs.getString("user_id"));
                pu.setCredit(rs.getInt("credit"));
                pu.setSuperuser(rs.getBoolean("is_super_user"));
                pu.setUserPassword(rs.getString("user_passwd"));
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
        return pu;
    }

    public static void updateUser(User pu) {
        Connection con = DataAccess.getConnection();
        String sql = "update usr set user_id=?,user_name=?,user_passwd=?,credit=?,is_super_user=? where id=?";
        PreparedStatement pst = null ;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, pu.getUserId());
            pst.setString(2, pu.getUserName());
            pst.setString(3, pu.getUserPassword());
            pst.setInt(4, pu.getCredit());
            pst.setBoolean(5, pu.getSuperuser());
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
