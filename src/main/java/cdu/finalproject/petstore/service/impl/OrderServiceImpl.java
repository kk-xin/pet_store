package cdu.finalproject.petstore.service.impl;

import cdu.finalproject.petstore.dao.OrderDao;
import cdu.finalproject.petstore.entity.Order;
import cdu.finalproject.petstore.service.OrderService;
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
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    //按页某种条件查询
    @Override
    public Page<List<Order>> findByPage(Order condition, Pageable pageable) {
        Page<List<Order>> orderList = null;
        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (condition.getId()!= null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("id").as(Integer.class), condition.getId());
                    predicates.add(predicate);
                }
                if (StringUtils.hasLength(condition.getNum())) {
                    Predicate predicate = criteriaBuilder.like(root.get("num").as(String.class), "%" + condition.getNum() + "%");
                    predicates.add(predicate);
                }
                if (StringUtils.hasLength(condition.getPrice())) {
                    Predicate predicate = criteriaBuilder.like(root.get("price").as(String.class), "%" + condition.getPrice() + "%");
                    predicates.add(predicate);
                }
                if (StringUtils.hasLength(condition.getTime())) {
                    Predicate predicate = criteriaBuilder.like(root.get("time").as(String.class), "%" + condition.getTime() + "%");
                    predicates.add(predicate);
                }
                if (StringUtils.hasLength(condition.getBuy())) {
                    Predicate predicate = criteriaBuilder.like(root.get("buy").as(String.class), "%" + condition.getBuy() + "%");
                    predicates.add(predicate);
                }
                if (StringUtils.hasLength(condition.getRemark())) {
                    Predicate predicate = criteriaBuilder.equal(root.get("remark").as(Integer.class), "%"+ condition.getRemark()+"%" );
                    predicates.add(predicate);
                }
                if (condition.getOuser() != null && condition.getOuser().getId()!=null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("ouser").get("id").as(Integer.class), condition.getOuser().getId());
                    predicates.add(predicate);
                }

                if (condition.getOpet() != null && condition.getOpet().getId()!=null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("opet").get("id").as(Integer.class), condition.getOpet().getId());
                    predicates.add(predicate);
                }

                if (condition.getOsupply() != null && condition.getOsupply().getId()!=null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("osupply").get("id").as(Integer.class), condition.getOsupply().getId());
                    predicates.add(predicate);
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };
        orderList = orderDao.findAll(specification, pageable);

        return orderList;
    }

    //某种条件查询
    @Override
    public List<Order> find(Order condition) {
        List<Order> orderList = null;

        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (condition.getId()!= null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("id").as(Integer.class), condition.getId());
                    predicates.add(predicate);
                }
                if (StringUtils.hasLength(condition.getNum())) {
                    Predicate predicate = criteriaBuilder.like(root.get("num").as(String.class), "%" + condition.getNum() + "%");
                    predicates.add(predicate);
                }
                if (StringUtils.hasLength(condition.getPrice())) {
                    Predicate predicate = criteriaBuilder.like(root.get("price").as(String.class), "%" + condition.getPrice() + "%");
                    predicates.add(predicate);
                }
                if (StringUtils.hasLength(condition.getTime())) {
                    Predicate predicate = criteriaBuilder.like(root.get("time").as(String.class), "%" + condition.getTime() + "%");
                    predicates.add(predicate);
                }
                if (StringUtils.hasLength(condition.getBuy())) {
                    Predicate predicate = criteriaBuilder.like(root.get("buy").as(String.class), "%" + condition.getBuy() + "%");
                    predicates.add(predicate);
                }
                if (StringUtils.hasLength(condition.getRemark())) {
                    Predicate predicate = criteriaBuilder.equal(root.get("remark").as(Integer.class), "%"+ condition.getRemark()+"%" );
                    predicates.add(predicate);
                }
                if (condition.getOuser() != null && condition.getOuser().getId()!=null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("ouser").get("id").as(Integer.class), condition.getOuser().getId());
                    predicates.add(predicate);
                }
                if (condition.getOpet() != null && condition.getOpet().getId()!=null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("opet").get("id").as(Integer.class), condition.getOpet().getId());
                    predicates.add(predicate);
                }

                if (condition.getOsupply() != null && condition.getOsupply().getId()!=null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("osupply").get("id").as(Integer.class), condition.getOsupply().getId());
                    predicates.add(predicate);
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };
        orderList = orderDao.findAll(specification);

        return orderList;
    }

    //全部查询
    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    //通过id查询
    @Override
    public Order findById(int id) {
        return orderDao.getById(id);
    }

    //添加
    @Override
    public boolean add(Order order) {
        orderDao.save(order);
        return true;
    }

    //修改
    @Override
    public boolean mod(Order order) {
        orderDao.save(order);
        return true;
    }

    //通过id查询
    @Override
    public List<Order> findByOuserId(int id) {
        return orderDao.findByOuserId(id);
    }

    //删除
    @Override
    public void del(int id)
    {
        orderDao.deleteById(id);
    }

}
