package database.dataaccessobjekts;

import database.utils.DataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public User getProductUserByid(int uid) {
        Connection con = DataAccess.getConnection();
        String sql = "select * from usr p where p.id=?";
        PreparedStatement pst = null ;
        User pu = null;
        ResultSet rs = null ;
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, uid);
            rs = pst.executeQuery();
            if (rs.next()) {
                pu = new User();
                pu.setId(rs.getInt("id"));
                pu.setUsername(rs.getString("username"));
                pu.setPassword(rs.getString("password"));
                pu.setFullname(rs.getString("fullname"));
                pu.setTitle(rs.getString("title"));
                pu.setCompanyname(rs.getString("companyname"));
                pu.setCompanyaddress(rs.getString("companyaddress"));
                pu.setCity(rs.getString("city"));
                pu.setJob(rs.getString("job"));
                pu.setTel(rs.getString("tel"));
                pu.setEmail(rs.getString("email"));
                pu.setCountry(rs.getString("country"));
                pu.setZip(rs.getString("zip"));
                pu.setSuperuser(rs.getString("superuser"));
                pu.setDelsoft(rs.getString("delsoft"));
                pu.setNote(rs.getString("note"));
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

    /**
     * �����û�����ѯ�û�����
     * @param username �û���
     * @return �û�����
     */
    public User findUserByusername(String username) {
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
                pu.setId(rs.getInt("id"));
                pu.setUsername(rs.getString("username"));
                pu.setPassword(rs.getString("password"));
                pu.setFullname(rs.getString("fullname"));
                pu.setTitle(rs.getString("title"));
                pu.setCompanyname(rs.getString("companyname"));
                pu.setCompanyaddress(rs.getString("companyaddress"));
                pu.setCity(rs.getString("city"));
                pu.setJob(rs.getString("job"));
                pu.setTel(rs.getString("tel"));
                pu.setEmail(rs.getString("email"));
                pu.setCountry(rs.getString("country"));
                pu.setZip(rs.getString("zip"));
                pu.setSuperuser(rs.getString("superuser"));
                pu.setDelsoft(rs.getString("delsoft"));
                pu.setNote(rs.getString("note"));
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

    /**
     * ����û���Ϣ
     * @param pu �û�����
     */
    public void addProductUser(User pu) {
        Connection con = DataAccess.getConnection();
        String sql = "insert into User (username,password,fullname,title,companyname,companyaddress,city,job,tel,email,country,zip,superuser,delsoft,note) values(?,?,?,?,?,?,?,?,?,?,?,?,'1','0',?)";
        PreparedStatement pst = null ;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, pu.getUsername());
            pst.setString(2, pu.getPassword());
            pst.setString(3, pu.getFullname());
            pst.setString(4, pu.getTitle());
            pst.setString(5, pu.getCompanyname());
            pst.setString(6, pu.getCompanyaddress());
            pst.setString(7, pu.getCity());
            pst.setString(8, pu.getJob());
            pst.setString(9, pu.getTel());
            pst.setString(10, pu.getEmail());
            pst.setString(11, pu.getCountry());
            pst.setString(12, pu.getZip());
            pst.setString(13, pu.getNote());
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

    /**
     * �����û���Ϣ
     * @param pu �û�����
     */
    public void updateProductuser(User pu) {
        Connection con = DataAccess.getConnection();
        String sql = "update User set username=?,password=?,fullname=?,title=?,companyname=?,companyaddress=?,city=?,job=?,tel=?,email=?,country=?,zip=?,note=? where id=?";
        PreparedStatement pst = null ;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, pu.getUsername());
            pst.setString(2, pu.getPassword());
            pst.setString(3, pu.getFullname());
            pst.setString(4, pu.getTitle());
            pst.setString(5, pu.getCompanyname());
            pst.setString(6, pu.getCompanyaddress());
            pst.setString(7, pu.getCity());
            pst.setString(8, pu.getJob());
            pst.setString(9, pu.getTel());
            pst.setString(10, pu.getEmail());
            pst.setString(11, pu.getCountry());
            pst.setString(12, pu.getZip());
            pst.setString(13, pu.getNote());
            pst.setInt(14, pu.getId());
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

    /**
     * �����û�Ȩ��
     * @param uid �û�����id
     * @param superuser �û���ɫ
     */
    public void updateSuperuser(int uid, String superuser) {
        Connection con = DataAccess.getConnection();
        String sql = "update User set superuser=? where id=?";
        PreparedStatement pst = null ;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, superuser);
            pst.setInt(2, uid);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
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

    /**
     * �����û�����ɾ���û�1Ϊɾ����
     * @param uid �û����� id
     * @param delsoft ��ɾ����־ 1Ϊ��ɾ����0Ϊ����
     */
    public void delSoftuser(int uid, String delsoft) {
        Connection con = DataAccess.getConnection();
        String sql = "update User set delsoft=? where id=?";
        PreparedStatement pst = null ;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, delsoft);
            pst.setInt(2, uid);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
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

    /**
     * ��ѯ�����û�����
     * @return �����û�����
     */
    public List<User> getAllProductUser() {
        Connection con = DataAccess.getConnection();
        String sql = "select * from User p  order by p.id ";
        List<User> list = new ArrayList<User>();
        Statement stmt = null;
        ResultSet rs = null ;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                User pu = new User();
                pu.setId(rs.getInt("id"));
                pu.setUsername(rs.getString("username"));
                pu.setPassword(rs.getString("password"));
                pu.setFullname(rs.getString("fullname"));
                pu.setTitle(rs.getString("title"));
                pu.setCompanyname(rs.getString("companyname"));
                pu.setCompanyaddress(rs.getString("companyaddress"));
                pu.setCity(rs.getString("city"));
                pu.setJob(rs.getString("job"));
                pu.setTel(rs.getString("tel"));
                pu.setEmail(rs.getString("email"));
                pu.setCountry(rs.getString("country"));
                pu.setZip(rs.getString("zip"));
                pu.setSuperuser(rs.getString("superuser"));
                pu.setDelsoft(rs.getString("delsoft"));
                pu.setNote(rs.getString("note"));
                list.add(pu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try {
                if(rs!=null){
                    rs.close();
                }
                if(stmt!=null){
                    stmt.close();
                }
                if(con!=null){
                    con.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return list;
    }
}
