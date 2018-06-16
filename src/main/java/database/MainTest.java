package database;

import bean.User;
import database.dataaccessobjekts.UserDAO;

public class MainTest {
    public static void main(String[] args) {
        User user = UserDAO.getUserByid("1");
        System.out.println(user.getUserId());
        System.out.println(user.getCredit());
        System.out.println(user.getUserPassword());
        System.out.println(user.getUserName());
        System.out.println(user.isSuperuser());
    }
}
