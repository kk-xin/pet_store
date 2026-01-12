package cdu.finalproject.petstore.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String birth;
    @Column
    private String age;
    @Column
    private String weight;
    @Column
    private String price;
    @Column
    private String remark;
    @Column
    private String img;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    private PetType types;

    @OneToMany( mappedBy = "petMessage")
    private List<Message> petMessageList;

    public PetType getTypes() {
        return types;
    }

    public void setTypes(PetType types) {
        this.types = types;
    }



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

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birth='" + birth + '\'' +
                ", age='" + age + '\'' +
                ", weight='" + weight + '\'' +
                ", price='" + price + '\'' +
                ", remark='" + remark + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
