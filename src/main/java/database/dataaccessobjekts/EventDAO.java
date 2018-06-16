package database.dataaccessobjekts;

import bean.Event;
import bean.Room;
import database.utils.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {
    private static List<Event> events = null;

    public static void insertEvent (Event event) {
        Connection con = DataAccess.getConnection();
        String sql = "insert into events (event_id, title, location, user_host_id, due_date, description) " +
                "VALUES (?, ?, ?, ?, ?, ?);";
        PreparedStatement pstm = null;

        try {
            pstm = con.prepareStatement(sql);
            pstm.setString(1, event.getEventId());
            pstm.setString(2, event.getTitle());
            pstm.setString(3, event.getLocation());
            pstm.setString(4, event.getHost().getUserId());
            pstm.setString(5, event.getDate());
            pstm.setString(6, event.getDescription());
            pstm.execute();

            if (events != null)
                events.add(event);
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

    public static List<Event> getAllEvents() {
        if (events != null){
            return events;
        }
        List<Event> eventList = new ArrayList<>();
        Connection con = DataAccess.getConnection();
        String sql = "select * from events";
        PreparedStatement pst = null ;
        Event event = null;
        ResultSet rs = null ;
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                event = new Event();
                event.setEventId(rs.getString("event_id"));
                event.setDate(rs.getString("due_date"));
                event.setDescription(rs.getString("description"));
                event.setHost(UserDAO.getUserByid(rs.getString("user_host_id")));
                event.setLocation(rs.getString("location"));
                event.setTitle(rs.getString("title"));
                eventList.add(event);
                events.add(event);
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
        return eventList;
    }


    public static Event getEventById(String eid) {
        Connection con = DataAccess.getConnection();
        String sql = "select * from events p where p.event_id=?";
        PreparedStatement pst = null ;
        Event event = null;
        ResultSet rs = null ;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, eid);
            rs = pst.executeQuery();
            if (rs.next()) {
                event = new Event();
                event.setEventId(rs.getString("event_id"));
                event.setDescription(rs.getString("description"));
                event.setLocation(rs.getString("location"));
                event.setDate(rs.getString("date"));
                event.setTitle(rs.getString("title"));
                event.setHost(UserDAO.getUserByid(rs.getString("user_host_id")));
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
        return event;
    }

    public static void updateEvent(Event event) {
        Connection con = DataAccess.getConnection();
        String sql = "update events set event_id=?,title=?,date=?,location=?,user_host_id=?,description=?";
        PreparedStatement pst = null ;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, event.getEventId());
            pst.setString(2, event.getTitle());
            pst.setString(3, event.getDate());
            pst.setString(4, event.getLocation());
            pst.setString(5, event.getHost().getUserId());
            pst.setString(6, event.getDescription());
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
