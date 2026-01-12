package cdu.finalproject.petstore.service;

import cdu.finalproject.petstore.entity.PetType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PetTypeService {

    //全部查询
    List<PetType> findAll();
    //某种条件查询
    List<PetType> find(PetType condition);
    //按页某种条件查询
    Page<List<PetType>> findByPage(PetType condition, Pageable pageable);
    //通过id查询
    PetType findById(int id);
    //添加
    boolean add(PetType petType);
    //修改
    boolean mod(PetType petType);
    //删除
    void del(int id);

}
