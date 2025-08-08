package testapp;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

/**
 * Класс работник - унаследуется от абстрактного человека и реализует
 * функциональность метода getStatusWithSalary() через переопределение
 */
public class Employee extends Person {
    private int salary;
    private String postInJob;
    private String status;
    private List<Task> tasksOnWork = new ArrayList<>();

    public Employee(int numberOfCard, String firstName, String lastName, LocalDate dateOfBirth) {
        super(numberOfCard, firstName, lastName, dateOfBirth);
    }

    @Override
    public String getStatusWithSalary() {
        return "Должность - " + status + " Зарплата - " + salary;
    }
    
    /**
     * Вспомогательный методдля вывода задач работника
     * @return 
     */
    public String showTasks() {
        String result = "Задачи работника:";
        
        for (Task task : tasksOnWork) {
            result += "\n\n\t"
                    + "Задача №" + task.getNumberOfTask()
                    + " Описание: " + task.getDescription()
                    + " Дата начала: " + task.getDateBegin().toString()
                    + " Дата конца: " + task.getDateEnd().toString();
        }
        
        return result;
    }
    
    @Override
    public String getData() {
        return super.getData()
                + "\n" + "Зарплата: " + this.getSalary()
                + " Должность: " + this.postInJob
                + " Статус: " + this.status + " "
                + showTasks() + "\n";
    }
    
    //геттеры и сеттеры для установки и сбора значений
    public int getSalary() {
        return salary;
    }

    public Employee setSalary(int salary) {
        this.salary = salary;
        return this;
    }

    public String getPostInJob() {
        return postInJob;
    }

    public Employee setPostInJob(String postInJob) {
        this.postInJob = postInJob;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Employee setStatus(String status) {
        this.status = status;
        return this;
    }

    public List<Task> getTasksOnWork() {
        return tasksOnWork;
    }

    public Employee setTasksOnWork(List<Task> tasksOnWork) {
        this.tasksOnWork = tasksOnWork;
        return this;
    }
}