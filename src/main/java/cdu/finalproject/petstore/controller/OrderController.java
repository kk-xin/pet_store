package cdu.finalproject.petstore.controller;

import cdu.finalproject.petstore.entity.*;
import cdu.finalproject.petstore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    SysUserService sysUserService;
    @Autowired
    MessageService messageService;
    @Autowired
    PetService petService;
    @Autowired
    OrderService orderService;
    @Autowired
    SupplyService supplyService;

    //某一个订单的详细信息获取
    @RequestMapping("orderSyInfo")
    public ModelAndView search(@RequestParam(name="id") String id) {
        ModelAndView mv = new ModelAndView("order/orderSyInform");

        //通过从上一个页面传来的id进行查询
        Supply supplyInfo = supplyService.findById(Integer.valueOf(id));
        Pet petInfo = petService.findById(Integer.valueOf(id));
        List<Message> messageList = messageService.findBySupplyMessageId(Integer.valueOf(id));

        mv.addObject("supplyInfo", supplyInfo);
        mv.addObject("petInfo", petInfo);
        mv.addObject("messageList", messageList);

        return mv;
    }

    //添加新的商品订单
    @RequestMapping(value = "addordSy",method = RequestMethod.GET)
    public ModelAndView addSy(@RequestParam("id") String id, HttpSession session) {

        //获取session中保存的数据
        int userid = (int) session.getAttribute("userid");
        String username = (String) session.getAttribute("username");
        String userimg = (String) session.getAttribute("userimg");

        ModelAndView mv = new ModelAndView("order/orderSyInform");

        //创建Date类 获取当前时间并转化其书写格式
        Date date= new Date();
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ) ;
        String nowTime =formatter.format(date);

        Supply supplyInfo = supplyService.findById(Integer.valueOf(id));

        //创建Order类 添加相关信息
        Order newOrder = new Order();
        newOrder.setNum("1");//已购买
        newOrder.setPrice(supplyInfo.getPrice());
        newOrder.setTime(nowTime);
        SysUser newuser=sysUserService.findById(userid);
        newOrder.setOuser(newuser);
        newOrder.setBuy("0");
        newOrder.setOsupply(supplyInfo);
        orderService.add(newOrder);

        mv.addObject("supplyInfo", supplyInfo);
        mv.addObject("newOrder", newOrder);
        mv.addObject("nowTime", nowTime);
        return mv;
    }

    //添加新的宠物订单
    @RequestMapping(value = "addordPet",method = RequestMethod.GET)
    public ModelAndView addPet(@RequestParam("id") String id,HttpSession session) {

        //获取session中保存的数据
        int userid = (int) session.getAttribute("userid");
        String username = (String) session.getAttribute("username");
        String userimg = (String) session.getAttribute("userimg");

        ModelAndView mv = new ModelAndView("order/orderPetInform");

        //创建Date类 获取当前时间并转化其书写格式
        Date date= new Date();
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ) ;
        String nowTime =formatter.format(date);

        SysUser newuser=sysUserService.findById(userid);
        Pet petInfo = petService.findById(Integer.valueOf(id));

        //创建Order类 添加相关信息
        Order newOrder = new Order();
        newOrder.setNum("1");
        newOrder.setPrice(petInfo.getPrice());
        newOrder.setTime(nowTime);
        newOrder.setOuser(newuser);
        newOrder.setBuy("0");
        newOrder.setOpet(petInfo);
        orderService.add(newOrder);

        mv.addObject("petInfo", petInfo);
        mv.addObject("newOrder", newOrder);
        mv.addObject("nowTime", nowTime);
        return mv;
    }

    //某一订单的查询
    @RequestMapping(value = "findOrder",method = RequestMethod.GET)
    public ModelAndView findOrder(HttpSession session){
        int userid = (int) session.getAttribute("userid");//获取session中保存的数据
        ModelAndView mv = new ModelAndView("order/findOrder");

        List<Order> orderList=orderService.findByOuserId(userid);
        mv.addObject("orderList",orderList);
        return mv;
    }

    //购物车
    @RequestMapping(value = "cart",method = RequestMethod.GET)
    public ModelAndView cart(HttpSession session){
        int userid = (int) session.getAttribute("userid");//获取session中保存的数据
        ModelAndView mv = new ModelAndView("order/cart");
        List<Order> orderList=orderService.findByOuserId(userid);
        mv.addObject("orderList",orderList);
        return mv;
    }


    //购买
    @RequestMapping(value = "buy",method = RequestMethod.GET)
    public ModelAndView buy(@RequestParam("id") String id,HttpSession session) {
        ModelAndView mv = new ModelAndView("order/findOrder");
        Order order=orderService.findById(Integer.valueOf(id));
        order.setBuy("1");//已购买
        int userid = (int) session.getAttribute("userid");
        orderService.mod(order);//修改订单属性
        List<Order> orderList=orderService.findByOuserId(userid);
        mv.addObject("orderList",orderList);
        return mv;
    }


    //删除
    @RequestMapping(value = "del",method = RequestMethod.GET)
    public ModelAndView del(@RequestParam("id") String id,HttpSession session) {
        int userid = (int) session.getAttribute("userid");//获取session中保存的数据
        ModelAndView mv = new ModelAndView("order/cart");
        orderService.del(Integer.valueOf(id));//调用del方法进行删除操作
        List<Order> orderList=orderService.findByOuserId(userid);
        mv.addObject("orderList",orderList);
        return mv;
    }

}


