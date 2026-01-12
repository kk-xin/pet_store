package cdu.finalproject.petstore.dao;

import cdu.finalproject.petstore.entity.Message;
import cdu.finalproject.petstore.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageDao extends JpaRepository<Message, Integer>, JpaSpecificationExecutor<Message> {

    List<Message> findByPetMessageId(int petId);
    List<Message> findBySyMessageId(int supplyId);

}