package cdu.finalproject.petstore.dao;

import cdu.finalproject.petstore.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SysRoleDao extends JpaRepository<SysRole, Integer>, JpaSpecificationExecutor<SysRole> {
}
