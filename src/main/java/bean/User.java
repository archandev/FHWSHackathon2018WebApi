package bean;

public class User {
    private String userId;
    private String userName;
    private String userPassword;
    private int credit;
    private boolean isSuperuser;

    public User(String userId, String userName, String userPassword, int credit, boolean isSuperuser) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.credit = credit;
        this.isSuperuser = isSuperuser;
    }

    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public boolean getSuperuser() {
        return isSuperuser;
    }

    public void setSuperuser(boolean superuser) {
        isSuperuser = superuser;
    }
}
