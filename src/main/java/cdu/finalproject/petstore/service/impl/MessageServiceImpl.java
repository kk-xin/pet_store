package cdu.finalproject.petstore.service.impl;

import cdu.finalproject.petstore.dao.MessageDao;
import cdu.finalproject.petstore.entity.Message;
import cdu.finalproject.petstore.service.MessageService;
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
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageDao messageDao;

    //全部查询
    @Override
    public List<Message> findAll() {
        return messageDao.findAll();
    }

    //某种条件查询
    @Override
    public List<Message> find(Message condition) {
        List<Message> messageList = null;

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
                if (StringUtils.hasLength(condition.getTime())) {
                    Predicate predicate = criteriaBuilder.like(root.get("time").as(String.class), "%" + condition.getTime() + "%");
                    predicates.add(predicate);
                }
                if (StringUtils.hasLength(condition.getContent())) {
                    Predicate predicate = criteriaBuilder.like(root.get("content").as(String.class), "%" + condition.getContent() + "%");
                    predicates.add(predicate);
                }
                if (StringUtils.hasLength(condition.getRemark())) {
                    Predicate predicate = criteriaBuilder.like(root.get("remark").as(String.class), "%" + condition.getRemark()+ "%");
                    predicates.add(predicate);
                }
                if (StringUtils.hasLength(condition.getImg())) {
                    Predicate predicate = criteriaBuilder.like(root.get("img").as(String.class), "%" + condition.getImg()+ "%");
                    predicates.add(predicate);
                }
                if (condition.getPetMessage() != null && condition.getPetMessage().getId()!=null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("petMessage").get("id").as(Integer.class), condition.getPetMessage().getId());
                    predicates.add(predicate);
                }
                if (condition.getSyMessage() != null && condition.getSyMessage().getId()!=null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("syMessage").get("id").as(Integer.class), condition.getSyMessage().getId());
                    predicates.add(predicate);
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };
        messageList = messageDao.findAll(specification);

        return messageList;
    }

    //通过id查询
    @Override
    public List<Message> findByPetMessageId(int petId) {
        return messageDao.findByPetMessageId(petId);
    }
    @Override
    public List<Message> findBySupplyMessageId(int supplyId) {
        return messageDao.findBySyMessageId(supplyId);
    }

    //按页某种条件查询
    @Override
    public Page<List<Message>> findByPage(Message condition, Pageable pageable) {
        Page<List<Message>> messageList = null;
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
                if (StringUtils.hasLength(condition.getTime())) {
                    Predicate predicate = criteriaBuilder.like(root.get("time").as(String.class), "%" + condition.getTime() + "%");
                    predicates.add(predicate);
                }
                if (StringUtils.hasLength(condition.getContent())) {
                    Predicate predicate = criteriaBuilder.like(root.get("content").as(String.class), "%" + condition.getContent() + "%");
                    predicates.add(predicate);
                }
                if (StringUtils.hasLength(condition.getRemark())) {
                    Predicate predicate = criteriaBuilder.like(root.get("remark").as(String.class), "%" + condition.getRemark()+ "%");
                    predicates.add(predicate);
                }
                if (StringUtils.hasLength(condition.getImg())) {
                    Predicate predicate = criteriaBuilder.like(root.get("img").as(String.class), "%" + condition.getImg()+ "%");
                    predicates.add(predicate);
                }
                if (condition.getPetMessage() != null && condition.getPetMessage().getId()!=null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("petMessage").get("id").as(Integer.class), condition.getPetMessage().getId());
                    predicates.add(predicate);
                }
                if (condition.getSyMessage() != null && condition.getSyMessage().getId()!=null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("syMessage").get("id").as(Integer.class), condition.getSyMessage().getId());
                    predicates.add(predicate);
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };
        messageList = messageDao.findAll(specification, pageable);

        return messageList;
    }

    //通过id查询
    @Override
    public Message findById(int id) {
        return messageDao.getById(id);
    }

    //添加
    @Override
    public boolean add(Message message) {
        messageDao.save(message);
        return true;
    }

    //修改
    @Override
    public boolean mod(Message message) {
        messageDao.save(message);
        return true;
    }

    //删除
    @Override
    public void del(int id) {
        messageDao.deleteById(id);
    }
}
