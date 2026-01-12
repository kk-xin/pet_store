package cdu.finalproject.petstore.dao;


import cdu.finalproject.petstore.entity.Order;
import cdu.finalproject.petstore.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<Order,Integer>, JpaSpecificationExecutor<Order> {

    List<Order> findByOuserId(int id);
}
