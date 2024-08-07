package sx.CRUDApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "fio")
    @NotNull
    @Size(min = 5, max = 255, message = "Слишком короткое ФИО")
    private String fio;

    @Column(name = "number")
    @NotNull
    @Pattern(regexp = "^\\+375\\(?\\d{2}\\)?[-\\s]?\\d{3}[-\\s]?\\d{2}[-\\s]?\\d{2}$",
            message = "Неправильный телефон")
    private String phoneNumber;

    @Column(name = "pass")
    @NotNull
    @Size(min = 8, max = 24, message = "Недопустимая длинна пароля")
    private String pass;

    @Column(name = "role")
    @NotNull
    private String role;

    @Column(name = "username")
    @NotNull
    @Size(min = 4, max = 25, message = "Недопустимый размер имени пользователя")
    private String username;

    public Employee() {
    }

    public Employee(String fio, String phoneNumber, String pass, String role, String username) {
        this.fio = fio;
        this.phoneNumber = phoneNumber;
        this.pass = pass;
        this.role = role;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
