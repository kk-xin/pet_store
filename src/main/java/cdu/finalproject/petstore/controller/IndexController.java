package cdu.finalproject.petstore.controller;

import cdu.finalproject.petstore.entity.SysUser;
import cdu.finalproject.petstore.service.PetService;
import cdu.finalproject.petstore.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
public class IndexController {

    @Autowired
    SysUserService sysUserService;
    @Autowired
    PetService petService;

    //获取当前用户信息并传入index.html
    @RequestMapping("index")
    public ModelAndView index(HttpServletResponse response, HttpSession session) {
        ModelAndView mv = new ModelAndView("index");

        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //获取当前用户的信息
        String username = principal.getUsername(); //获取当前用户的用户名
        SysUser user = sysUserService.findByUsername(username); //调用方法通过用户名查找该用户类的所有属性

        //将用户的属性存入session
        session.setAttribute("username", user.getUsername());
        session.setAttribute("userimg", user.getImg());
        session.setAttribute("userid", user.getId());

        mv.addObject("user", user);
        return mv;
    }


    @RequestMapping("login")
    public String login() {
        return "login";
    }


    //登录错误
    @RequestMapping("loginError")
    public String loginError() {
        return "loginError";
    }
}