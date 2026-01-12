package cdu.finalproject.petstore.service;

import cdu.finalproject.petstore.entity.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
public interface SysUserService {
    //全部查询
    List<SysUser> findAll();
    //某种条件查询
    List<SysUser> find(SysUser condition);
    //按页某种条件查询
    Page<List<SysUser>> findByPage(SysUser condition, Pageable pageable);
    //通过id查询
    SysUser findById(int id);
    //通过姓名查找
    SysUser findByUsername(String username);
    //添加
    boolean add(SysUser sysUser);
    //修改
    boolean mod(SysUser sysUser);
    //删除
    void del(int id);
    //重置密码
    boolean resetPwd(int id);

}
