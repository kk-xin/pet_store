package cdu.finalproject.petstore.service;

import cdu.finalproject.petstore.entity.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PetService {
    //全部查询
    List<Pet> findAll();
    //某种条件查询
    List<Pet> find(Pet condition);

    //按页某种条件查询
    Page<List<Pet>> findByPage(Pet condition, Pageable pageable);
    //通过姓名查询
    List<Pet> findByName(String name);

    //通过id查询
    Pet findById(int id);

//     boolean add(Pet pet);
//
//     boolean mod(Pet pet);

    //添加、修改
    boolean save(Pet pet);
    //删除
    void del(int id);

}
