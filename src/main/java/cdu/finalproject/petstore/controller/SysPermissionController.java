package cdu.finalproject.petstore.controller;

import cdu.finalproject.petstore.entity.SysPermission;
import cdu.finalproject.petstore.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("permission")
@PreAuthorize("hasRole('ADMIN')")
public class SysPermissionController {

    @Autowired
    SysPermissionService sysPermissionService;

    //按页查找管理权限信息
    @RequestMapping("search")
    public ModelAndView search(SysPermission condition, @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        ModelAndView mv = new ModelAndView("permission/list");
        Page<List<SysPermission>> pageInfo = sysPermissionService.findByPage(condition, pageable);

        mv.addObject("pageInfo", pageInfo);
        mv.addObject("c", condition);
        return mv;
    }

    //添加
    @GetMapping("add")
    public ModelAndView add() {
        ModelAndView mv = new ModelAndView("permission/add");
        return mv;
    }

    //添加
    @PostMapping("add")
    public String add(SysPermission sysPermission) {
        if (sysPermissionService.add(sysPermission)) {
            return "redirect:search";
        } else {
            return "redirect:add";
        }
    }

    //修改
    @GetMapping("mod")
    public ModelAndView mod(int id) {
        ModelAndView mv = new ModelAndView("permission/mod");
        SysPermission sysPermission = sysPermissionService.findById(id);//按照id查询
        mv.addObject("permission", sysPermission);
        return mv;
    }

    //修改
    @PostMapping("mod")
    public String mod(SysPermission sysPermission) {
        if (sysPermissionService.mod(sysPermission)) {
            return "redirect:search";
        } else {
            return "redirect:mod";
        }
    }

    //删除
    @GetMapping("del")
    public String del(int id) {
        sysPermissionService.del(id);
        return "redirect:search";
    }


}