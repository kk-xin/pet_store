package cdu.finalproject.petstore.service;

import cdu.finalproject.petstore.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    //全部查询
    List<Order> findAll();
    //某种条件查询
    List<Order> find(Order condition);
    //按页某种条件查询
    Page<List<Order>> findByPage(Order condition, Pageable pageable);
    //通过id查询
    Order findById(int id);

    //通过id查询
    List<Order> findByOuserId(int id);

    //添加
    boolean add(Order order);
    //修改
    boolean mod(Order order);
    //删除
    void del(int id);
}
