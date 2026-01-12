package cdu.finalproject.petstore.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
public class SysRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String code;
    @Column
    private String name;

    @ManyToMany
    @JoinTable(name = "r_role_permission",
            joinColumns = @JoinColumn(name = "r_id", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT)),
            inverseJoinColumns = @JoinColumn(name = "p_id", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT)),
            uniqueConstraints = @UniqueConstraint(columnNames = {"r_id", "p_id"}))
    private List<SysPermission> permissions;

    @ManyToMany(mappedBy = "roles")
    private List<SysUser> users;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }
}
