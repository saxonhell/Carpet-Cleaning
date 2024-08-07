package sx.CRUDApp.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "carpet")
public class Carpet {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "length")
    @NotNull(message = "Значение длинны ковра не может быть пустым.")
    @Min(value = 1, message = "Значение длины не может быть меньше 1 см.")
    @Max(value = 700, message = "Значение длины не может быть больше 700 см.")
    private double length;

    @Column(name = "width")
    @NotNull(message = "Значение ширины ковра не может быть пустым.")
    @Min(value = 1, message = "Значение ширины не может быть меньше 1 см.")
    @Max(value = 700, message = "Значение ширины не может быть больше 700 см.")
    private double width;

    @Column(name = "height_worth")
    @NotNull(message = "Значение высоты ворса не может быть пустым.")
    @Min(value = 1, message = "Значение высоты ворса не может быть меньше 1 см.")
    @Max(value = 50, message = "Значение высоты ворса не может быть больше 50 см.")
    //todo: обработка пустых значений в конструкторе
    private double heightWorth;

    @Column(name = "overlock")
    @NotNull
    private boolean overlock;

    @Column(name = "remove_smell")
    @NotNull
    private boolean removeSmell;

    @Column(name = "remove_Plasticine")
    private boolean removePlasticine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    public Carpet() {

    }

    public Carpet(double length, double width, double heightWorth, boolean overlock, boolean removeSmell, boolean removePlasticine) {
        this.length = length;
        this.width = width;
        this.heightWorth = heightWorth;
        this.overlock = overlock;
        this.removeSmell = removeSmell;
        this.removePlasticine = removePlasticine;
    }

    public Carpet(double length, double width, double heightWorth, boolean overlock, boolean removeSmell, boolean removePlasticine, Client owner, Order order) {
        this.length = length;
        this.width = width;
        this.heightWorth = heightWorth;
        this.overlock = overlock;
        this.removeSmell = removeSmell;
        this.removePlasticine = removePlasticine;
        this.owner = owner;
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeightWorth() {
        return heightWorth;
    }

    public void setHeightWorth(double heightWorth) {
        this.heightWorth = heightWorth;
    }

    public boolean isOverlock() {
        return overlock;
    }

    public void setOverlock(boolean overlock) {
        this.overlock = overlock;
    }

    public boolean isRemoveSmell() {
        return removeSmell;
    }

    public void setRemoveSmell(boolean removeSmell) {
        this.removeSmell = removeSmell;
    }

    public boolean isRemovePlasticine() {
        return removePlasticine;
    }

    public void setRemovePlasticine(boolean removePlasticine) {
        this.removePlasticine = removePlasticine;
    }
}
