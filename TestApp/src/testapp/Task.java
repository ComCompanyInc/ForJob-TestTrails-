package testapp;

import java.time.LocalDate;

public class Task {
    private int numberOfTask;
    private String description;
    private LocalDate dateBegin;
    private LocalDate dateEnd;
    private String executeStatus;

    public Task(int numberOfTask, String description, LocalDate dateBegin, LocalDate dateEnd, String executeStatus) {
        this.numberOfTask = numberOfTask;
        this.description = description;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.executeStatus = executeStatus;
    }
    
    public int getNumberOfTask() {
        return numberOfTask;
    }

    public void setNumberOfTask(int numberOfTask) {
        this.numberOfTask = numberOfTask;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(LocalDate dateBegin) {
        this.dateBegin = dateBegin;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getExecuteStatus() {
        return executeStatus;
    }

    public void setExecuteStatus(String executeStatus) {
        this.executeStatus = executeStatus;
    }
}