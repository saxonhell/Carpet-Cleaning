package sx.CRUDApp.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "carpet")
public class Carpet {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "length")
    @NotNull
    private double length;

    @Column(name = "width")
    @NotNull
    private double width;

    @Column(name = "height_worth")
    @NotNull
    private double heightWorth;

    @Column(name = "overlock")
    @NotNull
    private boolean overlock;

    @Column(name = "remove_smell")
    @NotNull
    private boolean removeSmell;

    @Column(name = "remove_Plasticine")
    private boolean removePlasticine;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client owner;


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

    public Carpet(double length, double width, double heightWorth, boolean overlock, boolean removeSmell, boolean removePlasticine, Client owner) {
        this.length = length;
        this.width = width;
        this.heightWorth = heightWorth;
        this.overlock = overlock;
        this.removeSmell = removeSmell;
        this.removePlasticine = removePlasticine;
        this.owner = owner;
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
