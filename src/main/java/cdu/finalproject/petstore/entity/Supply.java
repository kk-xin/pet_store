package cdu.finalproject.petstore.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Supply {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    @Column
    private  String name;
    @Column
    private String date;
    @Column
    private  String address;
    @Column
    private  String price;
    @Column
    private  String inventory;
    @Column
    private  String img;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    private SupplyType stypes;

    @OneToMany( mappedBy = "syMessage")
    private List<Message> syMessageList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public SupplyType getStypes() {
        return stypes;
    }

    public void setStypes(SupplyType stypes) {
        this.stypes = stypes;
    }

    public List<Message> getSyMessageList() {
        return syMessageList;
    }

    public void setSyMessageList(List<Message> syMessageList) {
        this.syMessageList = syMessageList;
    }

    @Override
    public String toString() {
        return "Supply{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", address='" + address + '\'' +
                ", price='" + price + '\'' +
                ", inventory='" + inventory + '\'' +
                ", img='" + img + '\'' +
                ", stypes=" + stypes +
                ", syMessageList=" + Arrays.toString(syMessageList.toArray()) +
                '}';
    }
}
