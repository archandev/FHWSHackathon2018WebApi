package database.dataaccessobjekts;

import bean.User;
import database.utils.DataAccess;

import java.sql.*;

public class UserDAO {

    public static User getUserByid(String uid) {
        Connection con = DataAccess.getConnection();
        String sql = "select * from usr p where p.id=?";
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
        String sql = "select * from User p where p.username=?";
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
