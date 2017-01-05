package demo.jaxrs.server;

public class UserData {
    private long id;
    private int workingTime;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStepCount() {

        return stepCount;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }

    public int getWorkingTime() {

        return workingTime;
    }

    public void setWorkingTime(int workingTime) {
        this.workingTime = workingTime;
    }

    private int stepCount;

    public void setId(long id) {
        this.id = id;
    }

    private String date;

    public UserData() {
        workingTime = stepCount = 0;
        date = "";
        id = -1;
    }

    public UserData(long id, int workingTime, int stepCount, String date, String name) {
        this.id = id;
        this.workingTime = workingTime;
        this.stepCount = stepCount;
        this.date = date;
        this.name = name;
    }
}