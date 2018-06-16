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

    public static void insertRoom (Room room) {
        Connection con = DataAccess.getConnection();
        String sql = "insert into rooms (room_id, title, size, location, responsible_person, description, user_id_room_fk, cost) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement pstm = null;

        try {
            pstm = con.prepareStatement(sql);
            pstm.setString(1, room.getRoomId());
            pstm.setString(2, room.getTitle());
            pstm.setInt(3, room.getSize());
            pstm.setString(4, room.getLocation());
            pstm.setString(5, room.getResponsiblePerson());
            pstm.setString(6, room.getDescription());
            pstm.setString(7, room.getBooker().getUserId());
            pstm.setInt(8, room.getCost());
            pstm.execute();

            if (rooms != null)
                rooms.add(room);
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
                ro.setCost(rs.getInt("cost"));
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


    public static Room getRoomByid(String rid) {
        Connection con = DataAccess.getConnection();
        String sql = "select * from rooms p where p.room_id=?";
        PreparedStatement pst = null ;
        Room ro = null;
        ResultSet rs = null ;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, rid);
            rs = pst.executeQuery();
            if (rs.next()) {
                ro = new Room();
                ro.setSize(rs.getInt("size"));
                ro.setDescription(rs.getString("description"));
                ro.setLocation(rs.getString("location"));
                ro.setResponsiblePerson(rs.getString("responsible_person"));
                ro.setTitle(rs.getString("title"));
                ro.setBooker(UserDAO.getUserByid(rs.getString("user_id_room_fk")));
                ro.setCost(rs.getInt("cost"));
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

    public static void updateRoom(Room ro) {
        Connection con = DataAccess.getConnection();
        String sql = "update rooms set room_id=?,title=?,size=?,location=?,responsible_person=?,description=?,user_id_room_fk=?,cost=? where room_id=?";
        PreparedStatement pst = null ;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, ro.getRoomId());
            pst.setString(2, ro.getTitle());
            pst.setInt(3, ro.getSize());
            pst.setString(4, ro.getLocation());
            pst.setString(5, ro.getResponsiblePerson());
            pst.setString(6, ro.getDescription());
            pst.setString(7, ro.getBooker().getUserId());
            pst.setInt(8, ro.getCost());
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
