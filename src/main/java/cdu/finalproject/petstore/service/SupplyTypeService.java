package cdu.finalproject.petstore.service;

import cdu.finalproject.petstore.entity.SupplyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SupplyTypeService {
    //全部查询
    List<SupplyType> findAll();
    //某种条件查询
    List<SupplyType> find(SupplyType condition);
    //按页某种条件查询
    Page<List<SupplyType>> findByPage(SupplyType condition, Pageable pageable);
    //通过id查询
    SupplyType findById(int id);
    //添加
    boolean add(SupplyType supplyType);
    //添加
    boolean mod(SupplyType supplyType);
    //删除
    void del(int id);

}
