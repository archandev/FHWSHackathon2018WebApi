package database.dataaccessobjekts;

import bean.Job;
import database.utils.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JobDAO {

    public Job getJobByid(String jid) {
        Connection con = DataAccess.getConnection();
        String sql = "select * from jobs p where p.id=?";
        PreparedStatement pst = null ;
        Job job = null;
        ResultSet rs = null ;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, jid);
            rs = pst.executeQuery();
            if (rs.next()) {
                job = new Job();
                job.setLocation(rs.getString("location"));
                job.setDateTime(rs.getString("time"));
                job.setDescription(rs.getString("description"));
                job.setResponsiblePerson(rs.getString("responsible_person"));
                job.setReward(rs.getInt("reward"));
                job.setName(rs.getString("job_name"));
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
        return job;
    }

    public void updateJob(Job job) {
        Connection con = DataAccess.getConnection();
        String sql = "update jobs set job_id=?,location=?,time=?,description=?,responsible_person=?,reward=?,job_name=? where job_id=?";
        PreparedStatement pst = null ;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, job.getJobId());
            pst.setString(2, job.getLocation());
            pst.setString(3, job.getDateTime());
            pst.setString(4, job.getDescription());
            pst.setString(5, job.getResponsiblePerson());
            pst.setInt(5, job.getReward());
            pst.setString(5, job.getName());
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
