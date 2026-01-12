package cdu.finalproject.petstore.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="supplytype")
public class SupplyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String remark;

    @OneToMany(mappedBy = "stypes")
    private List<Supply> supplyTypes;


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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Supply> getSupplyTypes() {
        return supplyTypes;
    }

    public void setSupplyTypes(List<Supply> supplyTypes) {
        this.supplyTypes = supplyTypes;
    }

    @Override
    public String toString() {
        return "SupplyType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", supplyTypes=" + supplyTypes +
                '}';
    }
}

