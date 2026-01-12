package cdu.finalproject.petstore.dao;

import cdu.finalproject.petstore.entity.Supply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplyDao extends JpaRepository<Supply, Integer>, JpaSpecificationExecutor<Supply> {


    @Modifying
    @Query("update Supply e set e.stypes=null where e.stypes.id=:id")
    void modBySyType(Integer id);
}