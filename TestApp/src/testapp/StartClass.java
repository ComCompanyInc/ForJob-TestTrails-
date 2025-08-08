package testapp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Стартовый класс для формирования людей
 */
public class StartClass {
    public void init() {
        //формирую список людей из обьектов
        List<Person> persons = Arrays.asList(
                 new Employee(1, "Иван", "Иванов", LocalDate.of(2000, 5, 1)),
                 new Employee(2, "Сергей", "Петров", LocalDate.of(2008, 10, 2)),
                 new Employee(3, "Борислав", "Горячев", LocalDate.of(1605, 6, 17)),
                 new Employee(4, "Олег", "Игорев", LocalDate.of(1816, 12, 25))
         );
       
        //список работников из обьектов
        List<Employee> employees = new ArrayList<>();
        
        //формирую каждому информацию о работе 
        //(также тут использую паттерн проектирования "Строитель" для формирования данных разом через сеттеры)
        employees.add(
            this.createEmployee(persons, 0, 200000, "Директор", "не уволен",
                Arrays.asList(
                        new Task(1, "Полежать на диване", LocalDate.of(2025, 10, 2), LocalDate.of(2025, 10, 3), "в будующем выполнении"),
                        new Task(2, "Попить чай", LocalDate.of(2025, 1, 2), LocalDate.of(2025, 10, 3), "выполнено")
                )
            )
        );

        employees.add(
            this.createEmployee(persons, 1, 12312, "Офисный работник", "пока не уволен",
                Arrays.asList(
                        new Task(1, "Сделать отчет", LocalDate.of(2025, 10, 2), LocalDate.of(2025, 10, 3), "не выполнено"),
                        new Task(2, "Отчитаться перед руководством", LocalDate.of(2025, 1, 2), LocalDate.of(2025, 10, 3), "не выполнено"),
                        new Task(3, "Проиграть директору в домино", LocalDate.of(2025, 1, 2), LocalDate.of(2025, 10, 3), "не выполнено")
                )
            )
        );
        
        employees.add(
            this.createEmployee(persons, 2, 1321, "Охранник офиса", "не уволен",
                Arrays.asList(
                        new Task(1, "Бить супостатых 1 раз", LocalDate.of(2025, 10, 2), LocalDate.of(2025, 10, 3), "не выполнено"),
                        new Task(2, "Бить супостатых 5 раз", LocalDate.of(2025, 1, 2), LocalDate.of(2025, 10, 3), "выполнено"),
                        new Task(3, "Бить супостатых 10 раз", LocalDate.of(2025, 1, 3), LocalDate.of(2025, 10, 4), "выполнено"),
                        new Task(4, "Бить супостатых 28 раз", LocalDate.of(2025, 1, 4), LocalDate.of(2025, 10, 5), "выполнено"),
                        new Task(5, "Бить супостатых 66 раз", LocalDate.of(2025, 1, 5), LocalDate.of(2025, 10, 6), "выполнено")
                )
            )
        );
        
        employees.add(
            this.createEmployee(persons, 3, 12000, "Отдел кадров", "уволен",
                Arrays.asList(
                        new Task(1, "Принять 1000 человек в офис", LocalDate.of(2025, 10, 2), LocalDate.of(2025, 10, 3), "в будующем выполнении"),
                        new Task(2, "Принять 100 000 человек в офис", LocalDate.of(2025, 1, 2), LocalDate.of(2025, 10, 3), "в будующем выполнении")
                )
            )
        );
        
        //Вывод работников
        for(Employee employee : employees) {
            System.out.println(employee.getData());
        }
    }
    
    //Метод по заполнению работников (приватный потому что доступ только для этого класса)
    private Employee createEmployee(List<Person> persons, int employeeIndex, int salary, String postInJob, String status, List<Task> tasksOnWork) {
        Employee employee = (Employee) persons.get(employeeIndex);
            
        return employee.setSalary(salary)
            .setPostInJob(postInJob)
            .setStatus(status)
            .setTasksOnWork(tasksOnWork);
    }
}