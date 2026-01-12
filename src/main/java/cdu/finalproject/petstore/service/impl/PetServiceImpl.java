package cdu.finalproject.petstore.service.impl;

import cdu.finalproject.petstore.dao.PetDao;
import cdu.finalproject.petstore.entity.Pet;
import cdu.finalproject.petstore.service.PetService;
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
public class PetServiceImpl implements PetService {

    @Autowired
    PetDao petDao;

    //全部查询
    @Override
    public List<Pet> findAll() {
        return petDao.findAll();
    }

    //某种条件查询
    @Override
    public List<Pet> find(Pet condition) {
        List<Pet> petList = null;

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
                if (StringUtils.hasLength(condition.getBirth())) {
                    Predicate predicate = criteriaBuilder.like(root.get("birth").as(String.class), "%" + condition.getBirth() + "%");
                    predicates.add(predicate);
                }
                if (StringUtils.hasLength(condition.getAge())) {
                    Predicate predicate = criteriaBuilder.like(root.get("age").as(String.class), "%" + condition.getAge() + "%");
                    predicates.add(predicate);
                }

                if (StringUtils.hasLength(condition.getWeight())) {
                    Predicate predicate = criteriaBuilder.like(root.get("weight").as(String.class), "%" + condition.getWeight() + "%");
                    predicates.add(predicate);
                }
                if (StringUtils.hasLength(condition.getPrice())) {
                    Predicate predicate = criteriaBuilder.like(root.get("price").as(String.class), "%" + condition.getPrice() + "%");
                    predicates.add(predicate);
                }

                if (StringUtils.hasLength(condition.getRemark())) {
                    Predicate predicate = criteriaBuilder.like(root.get("remark").as(String.class), "%" + condition.getRemark() + "%");
                    predicates.add(predicate);
                }

                if (StringUtils.hasLength(condition.getImg())) {
                    Predicate predicate = criteriaBuilder.like(root.get("img").as(String.class), "%" + condition.getImg() + "%");
                    predicates.add(predicate);
                }
                if (condition.getTypes() != null && condition.getTypes().getId()!=null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("types").get("id").as(Integer.class), condition.getTypes().getId());
                    predicates.add(predicate);
                }
                if (condition.getTypes() != null && condition.getTypes().getId()!=null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("types").get("id").as(Integer.class), condition.getTypes().getId());
                    predicates.add(predicate);
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };
        petList = petDao.findAll(specification);

        return petList;
    }

    //按页某种条件查询
    @Override
    public Page<List<Pet>> findByPage(Pet condition, Pageable pageable) {
        Page<List<Pet>> petList = null;

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
                if (StringUtils.hasLength(condition.getBirth())) {
                    Predicate predicate = criteriaBuilder.like(root.get("birth").as(String.class), "%" + condition.getBirth() + "%");
                    predicates.add(predicate);
                }
                if (StringUtils.hasLength(condition.getAge())) {
                    Predicate predicate = criteriaBuilder.like(root.get("age").as(String.class), "%" + condition.getAge() + "%");
                    predicates.add(predicate);
                }

                if (StringUtils.hasLength(condition.getWeight())) {
                    Predicate predicate = criteriaBuilder.like(root.get("weight").as(String.class), "%" + condition.getWeight() + "%");
                    predicates.add(predicate);
                }
                if (StringUtils.hasLength(condition.getPrice())) {
                    Predicate predicate = criteriaBuilder.like(root.get("price").as(String.class), "%" + condition.getPrice() + "%");
                    predicates.add(predicate);
                }

                if (StringUtils.hasLength(condition.getRemark())) {
                    Predicate predicate = criteriaBuilder.like(root.get("remark").as(String.class), "%" + condition.getRemark() + "%");
                    predicates.add(predicate);
                }

                if (StringUtils.hasLength(condition.getImg())) {
                    Predicate predicate = criteriaBuilder.like(root.get("img").as(String.class), "%" + condition.getImg() + "%");
                    predicates.add(predicate);
                }
                if (condition.getTypes() != null && condition.getTypes().getId()!=null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("types").get("id").as(Integer.class), condition.getTypes().getId());
                    predicates.add(predicate);
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };
        petList = petDao.findAll(specification, pageable);

        return petList;
    }

    //通过姓名查询
    @Override
    public List<Pet> findByName(String name) {
        return petDao.findByName(name);
    }

    //通过id查询
    @Override
    public Pet findById(int id) {
        return petDao.getById(id);
    }

    // @Override
    // public boolean add(pet pet) {
    // petDao.save(pet);
    // return true;
    // }
    //
    // @Override
    // public boolean mod(pet pet) {
    // petDao.save(pet);
    // return true;
    // }

    //添加和修改
    @Override
    public boolean save(Pet pet) {
        petDao.save(pet);
        return true;
    }

    //删除
    @Override
    public void del(int id) {
        petDao.deleteById(id);
    }
}
