package cdu.finalproject.petstore.service;

import cdu.finalproject.petstore.entity.SysPermission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SysPermissionService {
    //全部查询
    List<SysPermission> findAll();
    //某种条件查询
    List<SysPermission> find(SysPermission condition);
    //按页某种条件查询
    Page<List<SysPermission>> findByPage(SysPermission condition, Pageable pageable);
    //通过id查询
    SysPermission findById(int id);
    //添加
    boolean add(SysPermission sysPermission);
    //修改
    boolean mod(SysPermission sysPermission);
    //删除
    void del(int id);
}