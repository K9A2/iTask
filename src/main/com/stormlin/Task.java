package main.com.stormlin;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {

    private static final long serialVersionUID = -1964980944842514670L;

    private String id;
    private String title;
    private String priority;
    private Date date;

    Task(String title, String priority) {
        id = Util.get8BitIdFromString(title);
        this.title = title;
        this.priority = priority;
        date = new Date();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
