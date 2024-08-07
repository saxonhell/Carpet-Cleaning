package sx.CRUDApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Carpet> carpetList;

    @Column(name = "date")
    @NotNull(message = "Дата не может быть пустой")
    private LocalDate date;

    @Column(name = "status")
    @NotNull(message = "Статус не может быть пустым")
    private String status;

    @Column(name = "delivery_address")
    @NotNull(message = "Адрес доставки не может быть пустым")
    private String deliveryAddress;

    @Column(name = "amount")
    @NotNull(message = "Сумма не может быть пустой")
    @Min(value = 1, message = "Недопустимая сумма заказа")
    private double amount;

    public Order(List<Carpet> carpetList, LocalDate date, String status, String deliveryAddress, double amount) {
        this.carpetList = carpetList;
        this.date = date;
        this.status = status;
        this.deliveryAddress = deliveryAddress;
        this.amount = amount;
    }

    public Order(LocalDate date, String status, String deliveryAddress, double amount) {
        this.date = date;
        this.status = status;
        this.deliveryAddress = deliveryAddress;
        this.amount = amount;
    }

    public Order() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Carpet> getCarpetList() {
        return carpetList;
    }

    public void setCarpetList(List<Carpet> carpetList) {
        this.carpetList = carpetList;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
