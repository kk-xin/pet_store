package cdu.finalproject.petstore.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "user")
public class SysUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String img;

    @ManyToMany
    @OrderBy("id asc")
    @JoinTable(name = "r_user_role",
            joinColumns = @JoinColumn(name = "u_id", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT)),
            inverseJoinColumns = @JoinColumn(name = "r_id", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT)),
            uniqueConstraints = @UniqueConstraint(columnNames = {"u_id", "r_id"}))
    private List<SysRole> roles;

    @OneToMany( mappedBy = "ouser")
    private List<Order> userOrderList;


    public List<Order> getUserOrderList() {
        return userOrderList;
    }
    public void setUserOrderList(List<Order> userOrderList) {
        this.userOrderList = userOrderList;
    }

    @Transient
    private SysRole role;
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public SysRole getRole() {
        return role;
    }

    public void setRole(SysRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", img='" + img + '\'' +
                ", roles=" + roles +
                ", role=" + role +
                '}';
    }
}
