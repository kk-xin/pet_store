package cdu.finalproject.petstore.dao;

import cdu.finalproject.petstore.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetDao extends JpaRepository<Pet, Integer>, JpaSpecificationExecutor<Pet> {

    @Modifying
    @Query("update Pet e set e.types=null where e.types.id=:id")
    void modByType(Integer id);

    List<Pet> findByName(String name);
}