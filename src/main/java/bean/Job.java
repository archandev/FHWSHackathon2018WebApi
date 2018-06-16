package bean;

public class Job {
    private String jobId;
    private String name;
    private String location;
    private String dateTime;
    private String description;
    private String responsiblePerson;
    private User worker;
    private int reward;

    public Job(String jobId, String name, String location, String dateTime, String description, String responsiblePerson, int reward, User worker) {
        this.jobId = jobId;
        this.name = name;
        this.location = location;
        this.dateTime = dateTime;
        this.description = description;
        this.responsiblePerson = responsiblePerson;
        this.reward = reward;
        this.worker = worker;
    }

    public Job() {
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public User getWorker() {
        return worker;
    }

    public void setWorker(User worker) {
        this.worker = worker;
    }
}
