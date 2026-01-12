package cdu.finalproject.petstore.dao;

import cdu.finalproject.petstore.entity.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SysPermissionDao extends JpaRepository<SysPermission, Integer>, JpaSpecificationExecutor<SysPermission> {
}
