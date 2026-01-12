package cdu.finalproject.petstore.service.impl;

import cdu.finalproject.petstore.dao.SupplyDao;
import cdu.finalproject.petstore.entity.Supply;
import cdu.finalproject.petstore.service.SupplyService;
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
public class SupplyServiceImpl implements SupplyService {

    @Autowired
    SupplyDao supplyDao;

    //按页某种条件查询
    @Override
    public Page<List<Supply>> findByPage(Supply condition, Pageable pageable) {
        Page<List<Supply>> supplyList = null;

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
                if (StringUtils.hasLength(condition.getDate())) {
                    Predicate predicate = criteriaBuilder.like(root.get("date").as(String.class), "%" + condition.getDate() + "%");
                    predicates.add(predicate);
                }
                if (StringUtils.hasLength(condition.getAddress())) {
                    Predicate predicate = criteriaBuilder.like(root.get("address").as(String.class), "%" + condition.getAddress()+ "%");
                    predicates.add(predicate);
                }
                if (StringUtils.hasLength(condition.getPrice())) {
                    Predicate predicate = criteriaBuilder.like(root.get("price").as(String.class), "%" + condition.getPrice() + "%");
                    predicates.add(predicate);
                }
                if (StringUtils.hasLength(condition.getInventory())) {
                    Predicate predicate = criteriaBuilder.equal(root.get("inventory").as(Integer.class), "%"+ condition.getInventory()+"%" );
                    predicates.add(predicate);
                }
                if (StringUtils.hasLength(condition.getImg())) {
                    Predicate predicate = criteriaBuilder.like(root.get("img").as(String.class), "%" + condition.getImg() + "%");
                    predicates.add(predicate);
                }
                if (condition.getStypes() != null && condition.getStypes().getId()!=null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("stypes").get("id").as(Integer.class), condition.getStypes().getId());
                    predicates.add(predicate);
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };
        supplyList = supplyDao.findAll(specification, pageable);

        return supplyList;
    }

    //某种条件查询
    @Override
    public List<Supply> find(Supply condition) {
        List<Supply> supplyList = null;

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
                if (StringUtils.hasLength(condition.getDate())) {
                    Predicate predicate = criteriaBuilder.like(root.get("date").as(String.class), "%" + condition.getDate() + "%");
                    predicates.add(predicate);
                }
                if (StringUtils.hasLength(condition.getAddress())) {
                    Predicate predicate = criteriaBuilder.like(root.get("address").as(String.class), "%" + condition.getAddress()+ "%");
                    predicates.add(predicate);
                }
                if (StringUtils.hasLength(condition.getPrice())) {
                    Predicate predicate = criteriaBuilder.like(root.get("price").as(String.class), "%" + condition.getPrice() + "%");
                    predicates.add(predicate);
                }
                if (StringUtils.hasLength(condition.getInventory())) {
                    Predicate predicate = criteriaBuilder.equal(root.get("inventory").as(Integer.class), "%"+ condition.getInventory()+"%" );
                    predicates.add(predicate);
                }
                if (StringUtils.hasLength(condition.getImg())) {
                    Predicate predicate = criteriaBuilder.like(root.get("img").as(String.class), "%" + condition.getImg() + "%");
                    predicates.add(predicate);
                }
                if (condition.getStypes() != null && condition.getStypes().getId()!=null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("stypes").get("id").as(Integer.class), condition.getStypes().getId());
                    predicates.add(predicate);
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };
        supplyList = supplyDao.findAll(specification);

        return supplyList;
    }

    //全部查询
    @Override
    public List<Supply> findAll() {
        return supplyDao.findAll();
    }

    //通过id查询
    @Override
    public Supply findById(int id) {
        return supplyDao.getById(id);
    }

    //添加
    @Override
    public boolean add(Supply supply) {
        supplyDao.save(supply);
        return true;
    }

    //修改
    @Override
    public boolean mod(Supply supply) {
        supplyDao.save(supply);
        return true;
    }

    //删除
    @Override
    public void del(int id) {
        supplyDao.deleteById(id);
    }

}
