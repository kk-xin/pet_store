package cdu.finalproject.petstore.service.impl;

import cdu.finalproject.petstore.dao.SupplyDao;
import cdu.finalproject.petstore.dao.SupplyTypeDao;
import cdu.finalproject.petstore.entity.SupplyType;
import cdu.finalproject.petstore.service.SupplyTypeService;
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
public class SupplyTypeServiceImpl implements SupplyTypeService {

    @Autowired
    SupplyTypeDao supplyTypeDao;
    @Autowired
    SupplyDao supplyDao;

    //按页某种条件查询
    @Override
    public Page<List<SupplyType>> findByPage(SupplyType condition, Pageable pageable) {
        Page<List<SupplyType>> supplyTypeList = null;

        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (condition.getId() != null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("id").as(Integer.class), condition.getId());
                    predicates.add(predicate);
                }
                if (StringUtils.hasLength(condition.getName())) {
                    Predicate predicate = criteriaBuilder.like(root.get("name").as(String.class), "%" + condition.getName()+ "%");
                    predicates.add(predicate);
                }
                if (StringUtils.hasLength(condition.getRemark())) {
                    Predicate predicate = criteriaBuilder.like(root.get("remark").as(String.class), "%" + condition.getRemark() + "%");
                    predicates.add(predicate);
                }


                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };
        supplyTypeList= supplyTypeDao.findAll(specification, pageable);

        return supplyTypeList;
    }

    //某种条件查询
    @Override
    public List<SupplyType> find(SupplyType condition) {
        List<SupplyType> supplyTypeList = null;

        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (condition.getId() != null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("id").as(Integer.class), condition.getId());
                    predicates.add(predicate);
                }
                if (StringUtils.hasLength(condition.getName())) {
                    Predicate predicate = criteriaBuilder.like(root.get("name").as(String.class), "%" + condition.getName()+ "%");
                    predicates.add(predicate);
                }
                if (StringUtils.hasLength(condition.getRemark())) {
                    Predicate predicate = criteriaBuilder.like(root.get("remark").as(String.class), "%" + condition.getRemark() + "%");
                    predicates.add(predicate);
                }


                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };
        supplyTypeList = supplyTypeDao.findAll(specification);

        return supplyTypeList;
    }

    //全部查询
    @Override
    public List<SupplyType> findAll() {
        return supplyTypeDao.findAll();
    }

    //通过id查询
    @Override
    public SupplyType findById(int id) {
        return supplyTypeDao.getById(id);
    }

    //添加
    @Override
    public boolean add(SupplyType supplyType) {
        supplyTypeDao.save(supplyType);
        return true;
    }

    //修改
    @Override
    public boolean mod(SupplyType supplyType) {
        supplyTypeDao.save(supplyType);
        return true;
    }

    //删除
    @Override
    public void del(int id) {

        supplyTypeDao.deleteById(id);
        supplyDao.modBySyType(id);
    }

}
