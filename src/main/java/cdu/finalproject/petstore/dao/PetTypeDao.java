package cdu.finalproject.petstore.dao;

import cdu.finalproject.petstore.entity.PetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PetTypeDao extends JpaRepository<PetType, Integer>, JpaSpecificationExecutor<PetType> {


}