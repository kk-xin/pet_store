package cdu.finalproject.petstore.controller;

import cdu.finalproject.petstore.entity.SysUser;
import cdu.finalproject.petstore.service.PetService;
import cdu.finalproject.petstore.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
public class FooterController {

    //页脚
    @RequestMapping("aboutus")
    public ModelAndView about(HttpServletResponse response, HttpSession session) {
        ModelAndView mv = new ModelAndView("footer/aboutUs");
        return mv;
    }
}