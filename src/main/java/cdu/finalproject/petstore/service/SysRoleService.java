package cdu.finalproject.petstore.service;

import cdu.finalproject.petstore.entity.SysRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SysRoleService {
    //全部查询
    List<SysRole> findAll();
    //某种条件查询
    List<SysRole> find(SysRole condition);
    //按页某种条件查询
    Page<List<SysRole>> findByPage(SysRole condition, Pageable pageable);
    //通过id查询
    SysRole findById(int id);
    //添加
    boolean add(SysRole sysRole);
    //修改
    boolean mod(SysRole sysRole);
    //删除
    void del(int id);
}
