package cdu.finalproject.petstore.entity;


import javax.persistence.*;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String num;
    @Column
    private String price;
    @Column
    private String time;
    @Column
    private String buy;
    @Column
    private String remark;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    private SysUser ouser;

    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    private Pet opet;

    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    private Supply osupply;

    public Pet getOpet() {
        return opet;
    }

    public void setOpet(Pet opet) {
        this.opet = opet;
    }

    public Supply getOsupply() {
        return osupply;
    }

    public void setOsupply(Supply osupply) {
        this.osupply = osupply;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public SysUser getOuser() {
        return ouser;
    }

    public void setOuser(SysUser ouser) {
        this.ouser = ouser;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", num='" + num + '\'' +
                ", price='" + price + '\'' +
                ", time='" + time + '\'' +
                ", buy='" + buy + '\'' +
                ", remark='" + remark + '\'' +
                ", ouser=" + ouser +
                ", opet=" + opet +
                ", osupply=" + osupply +
                '}';
    }
}
