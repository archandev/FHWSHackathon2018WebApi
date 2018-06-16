package bean;

public class Room {
    private String roomId;
    private String title;
    private int size;
    private String location;
    private String responsiblePerson;
    private String description;
    private User booker;
    private int cost;

    public Room() {
    }

    public Room(String roomId, String title, int size, String location, String responsiblePerson, String description, User booker, int cost) {
        this.roomId = roomId;
        this.title = title;
        this.size = size;
        this.location = location;
        this.responsiblePerson = responsiblePerson;
        this.description = description;
        this.booker = booker;
        this.cost = cost;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getBooker() {
        return booker;
    }

    public void setBooker(User booker) {
        this.booker = booker;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
