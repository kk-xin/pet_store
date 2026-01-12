package cdu.finalproject.petstore.service.impl;

import cdu.finalproject.petstore.dao.SysUserDao;
import cdu.finalproject.petstore.entity.SysUser;
import cdu.finalproject.petstore.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    SysUserDao sysUserDao;

    //全部查询
    @Override
    public List<SysUser> findAll() {
        return sysUserDao.findAll();
    }

    //某种条件查询
    @Override
    public List<SysUser> find(SysUser condition) {
        List<SysUser> sysUserList = null;

        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (condition.getId() != null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("id").as(Integer.class), condition.getId());
                    predicates.add(predicate);
                }
                if (StringUtils.hasLength(condition.getUsername())) {
                    Predicate predicate = criteriaBuilder.like(root.get("username").as(String.class), "%" + condition.getUsername() + "%");
                    predicates.add(predicate);
                }

                if (StringUtils.hasLength(condition.getEmail())) {
                    Predicate predicate = criteriaBuilder.like(root.get("email").as(String.class), "%" + condition.getEmail() + "%");
                    predicates.add(predicate);
                }

                if (StringUtils.hasLength(condition.getImg())) {
                    Predicate predicate = criteriaBuilder.like(root.get("img").as(String.class), "%" + condition.getImg() + "%");
                    predicates.add(predicate);
                }

//                 if (condition.getRoles() != null && condition.getId() != null) {
//                 Predicate predicate = criteriaBuilder.equal(root.get("dep").get("id").as(Integer.class), condition.getId());
//                 predicates.add(predicate);
//                 }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        sysUserList = sysUserDao.findAll(specification);

        return sysUserList;
    }


    //按页某种条件查询
    @Override
    public Page<List<SysUser>> findByPage(SysUser condition, Pageable pageable) {
        Page<List<SysUser>> sysUserList = null;

        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (StringUtils.hasLength(condition.getUsername())) {
                    Predicate predicate = criteriaBuilder.like(root.get("username").as(String.class), "%" + condition.getUsername() + "%");
                    predicates.add(predicate);
                }
                 if (condition.getRole() != null && condition.getRole().getId() != null) {

                 Predicate predicate = criteriaBuilder.equal(root.get("role").get("id").as(Integer.class), condition.getRole().getId());
                 predicates.add(predicate);
                 }

                if (StringUtils.hasLength(condition.getEmail())) {
                    Predicate predicate = criteriaBuilder.like(root.get("email").as(String.class), "%" + condition.getEmail() + "%");
                    predicates.add(predicate);
                }

                if (StringUtils.hasLength(condition.getImg())) {
                    Predicate predicate = criteriaBuilder.like(root.get("img").as(String.class), "%" + condition.getImg() + "%");
                    predicates.add(predicate);
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        sysUserList = sysUserDao.findAll(specification, pageable);

        return sysUserList;
    }

    //通过id查询
    @Override
    public SysUser findById(int id) {
        return sysUserDao.getById(id);
    }

    //通过姓名查询
    @Override
    public SysUser findByUsername(String username) {
        System.out.println(":::::::::::" + this.getClass() + ": " + username);
        return sysUserDao.findByUsername(username);
    }

    //添加
    @Override
    public boolean add(SysUser sysUser) {
        sysUser.setPassword(new BCryptPasswordEncoder().encode(sysUser.getPassword()));
        sysUserDao.save(sysUser);
        return true;
    }

    //修改
    @Override
    public boolean mod(SysUser sysUser) {
        sysUser.setPassword(new BCryptPasswordEncoder().encode(sysUser.getPassword()));
        sysUserDao.save(sysUser);
        return true;
    }

    //删除
    @Override
    public void del(int id) {
        sysUserDao.deleteById(id);
    }

    //重置密码
    @Override
    public boolean resetPwd(int id) {
        String newPwd = new BCryptPasswordEncoder().encode("123");
        sysUserDao.resetPwd(id, newPwd);
        return true;
    }
}