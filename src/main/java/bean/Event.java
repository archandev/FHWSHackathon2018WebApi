package bean;

public class Event {
    private String eventId;
    private String title;
    private String location;
    private User host;
    private String date;
    private String description;

    public Event() {
    }

    public Event(String eventId, String title, String location, User host, String date, String descriptor) {
        this.eventId = eventId;
        this.title = title;
        this.location = location;
        this.host = host;
        this.date = date;
        this.description = description;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public User getHost() {
        return host;
    }

    public void setHost(User host) {
        this.host = host;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
