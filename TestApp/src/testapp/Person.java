package testapp;

import java.time.LocalDate;

/**
 * Абстрактный класс человек
 * хранит информацию об имени,
 * фамилии и обязательном методе Data,
 * который должен иметь реализацию в классах наследниках…
 */
abstract class Person {
    private int numberOfCard;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    
    public Person(int numberOfCard, String firstName, String lastName, LocalDate dateOfBirth) {
        this.numberOfCard = numberOfCard;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }
    
    public abstract String getStatusWithSalary();
    
    public String getData() {
        return "Карточка человека №"
                + this.numberOfCard + ": "
                + this.firstName + " "
                + this.lastName + " Дата рождения: "
                + this.dateOfBirth.toString();
    };

    // Геттеры и сеттеры для возможности взятия/редактирования значений атрибутов класса
    public int getNumberOfCard() {
        return numberOfCard;
    }

    public void setNumberOfCard(int numberOfCard) {
        this.numberOfCard = numberOfCard;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}