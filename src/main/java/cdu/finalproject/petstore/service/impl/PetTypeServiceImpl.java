package cdu.finalproject.petstore.service.impl;

import cdu.finalproject.petstore.dao.PetDao;
import cdu.finalproject.petstore.dao.PetTypeDao;
import cdu.finalproject.petstore.entity.PetType;
import cdu.finalproject.petstore.service.PetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class PetTypeServiceImpl implements PetTypeService {

    @Autowired
    PetTypeDao petTypeDao;
    @Autowired
    PetDao petDao;

    //按页某种条件查询
    @Override
    public Page<List<PetType>> findByPage(PetType condition, Pageable pageable) {
        Page<List<PetType>> supplyList = null;

        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (condition.getId() != null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("id").as(Integer.class), condition.getId());
                    predicates.add(predicate);
                }
                if (StringUtils.hasLength(condition.getName())) {
                    Predicate predicate = criteriaBuilder.like(root.get("name").as(String.class), "%" + condition.getName() + "%");
                    predicates.add(predicate);
                }
                if (StringUtils.hasLength(condition.getRemark())) {
                    Predicate predicate = criteriaBuilder.like(root.get("remark").as(String.class), "%" + condition.getRemark() + "%");
                    predicates.add(predicate);
                }


                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };
        supplyList = petTypeDao.findAll(specification, pageable);

        return supplyList;
    }

    //某种条件查询
    @Override
    public List<PetType> find(PetType condition) {
        List<PetType> supplyList = null;

        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (condition.getId() != null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("id").as(Integer.class), condition.getId());
                    predicates.add(predicate);
                }
                if (StringUtils.hasLength(condition.getName())) {
                    Predicate predicate = criteriaBuilder.like(root.get("name").as(String.class), "%" + condition.getName() + "%");
                    predicates.add(predicate);
                }
                if (StringUtils.hasLength(condition.getRemark())) {
                    Predicate predicate = criteriaBuilder.like(root.get("remark").as(String.class), "%" + condition.getRemark() + "%");
                    predicates.add(predicate);
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };
        supplyList = petTypeDao.findAll(specification);

        return supplyList;
    }

    //全部查询
    @Override
    public List<PetType> findAll() {
        return petTypeDao.findAll();
    }

    //通过id查询
    @Override
    public PetType findById(int id) {
        return petTypeDao.getById(id);
    }

    //添加
    @Override
    public boolean add(PetType petType) {
        petTypeDao.save(petType);
        return true;
    }

    //修改
    @Override
    public boolean mod(PetType petType) {
        petTypeDao.save(petType);
        return true;
    }

    //删除
    @Override
    public void del(int id) {

        petTypeDao.deleteById(id);
        petDao.modByType(id);
    }

}
