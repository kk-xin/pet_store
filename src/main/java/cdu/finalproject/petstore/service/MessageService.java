package cdu.finalproject.petstore.service;

import cdu.finalproject.petstore.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MessageService {

    //全部查询
    List<Message> findAll();
    //某种条件查询
    List<Message> find(Message condition);
    //通过id查询
    List<Message> findByPetMessageId(int petId);
    List<Message> findBySupplyMessageId(int supplyId);

    //按页某种条件查询
    Page<List<Message>> findByPage(Message condition, Pageable pageable);
    //通过id查询
    Message findById(int id);

    //添加
    boolean add(Message message);
    //修改
    boolean mod(Message message);
    //删除
    void del(int id);

}
