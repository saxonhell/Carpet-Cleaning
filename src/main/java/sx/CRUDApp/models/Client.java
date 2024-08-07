package sx.CRUDApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

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
    private String fio;

    @Column(name = "number")
    @NotNull
    private String phoneNumber;

    @Transient
    @OneToMany(mappedBy = "owner")
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
