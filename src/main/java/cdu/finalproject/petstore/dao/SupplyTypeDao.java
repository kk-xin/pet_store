package cdu.finalproject.petstore.dao;

import cdu.finalproject.petstore.entity.Supply;
import cdu.finalproject.petstore.entity.SupplyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplyTypeDao extends JpaRepository<SupplyType, Integer>, JpaSpecificationExecutor<SupplyType> {


}