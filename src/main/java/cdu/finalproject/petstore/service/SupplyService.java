package cdu.finalproject.petstore.service;

import cdu.finalproject.petstore.entity.Supply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SupplyService {
    //全部查询
    List<Supply> findAll();
    //某种条件查询
    List<Supply> find(Supply condition);
    //按页某种条件查询
    Page<List<Supply>> findByPage(Supply condition, Pageable pageable);
    //通过id查询
    Supply findById(int id);
    //添加
    boolean add(Supply supply);
    //修改
    boolean mod(Supply supply);
    //删除
    void del(int id);

}
