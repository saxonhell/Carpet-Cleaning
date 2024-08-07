package sx.CRUDApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;


@Entity
@Table(name = "client")
public class Client {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Carpet> carpetList;


    public Client(String fio, String phoneNumber) {
        this.fio = fio;
        this.phoneNumber = phoneNumber;
    }

    public Client(String fio, String phoneNumber, List<Carpet> carpetList) {
        this.fio = fio;
        this.phoneNumber = phoneNumber;
        this.carpetList = carpetList;
    }

    public Client() {

    }

    public List<Carpet> getCarpetList() {
        return carpetList;
    }

    public void setCarpetList(List<Carpet> carpetList) {
        this.carpetList = carpetList;
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
}
