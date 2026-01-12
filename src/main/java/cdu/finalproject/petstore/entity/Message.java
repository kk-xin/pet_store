package cdu.finalproject.petstore.entity;


import javax.persistence.*;

@Entity
@Table(name="message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String username;
    @Column
    private String time;
    @Column
    private String content;
    @Column
    private String img;
    @Column
    private String remark;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    private Pet petMessage;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    private Supply syMessage;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Pet getPetMessage() {
        return petMessage;
    }

    public void setPetMessage(Pet petMessage) {
        this.petMessage = petMessage;
    }

    public Supply getSyMessage() {
        return syMessage;
    }

    public void setSyMessage(Supply syMessage) {
        this.syMessage = syMessage;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", time='" + time + '\'' +
                ", content='" + content + '\'' +
                ", img='" + img + '\'' +
                ", remark='" + remark + '\'' +
                ", petMessage=" + petMessage +
                ", syMessage=" + syMessage +
                '}';
    }
}
