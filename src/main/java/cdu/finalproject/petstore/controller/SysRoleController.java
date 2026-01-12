package cdu.finalproject.petstore.controller;

import cdu.finalproject.petstore.entity.SysPermission;
import cdu.finalproject.petstore.entity.SysRole;
import cdu.finalproject.petstore.service.SysPermissionService;
import cdu.finalproject.petstore.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("role")
@PreAuthorize("hasRole('ADMIN')")
public class SysRoleController {

    @Autowired
    SysRoleService sysRoleService;
    @Autowired
    SysPermissionService sysPermissionService;

    //按页查找身份信息
    @RequestMapping("search")
    public ModelAndView search(SysRole condition, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        ModelAndView mv = new ModelAndView("role/list");
        Page<List<SysRole>> pageInfo = sysRoleService.findByPage(condition, pageable);
        List<SysPermission> permissionList = sysPermissionService.findAll();

        mv.addObject("pageInfo", pageInfo);
        mv.addObject("permissionList", permissionList);
        mv.addObject("c", condition);
        return mv;
    }

    //添加
    @GetMapping("add")
    public ModelAndView add() {
        ModelAndView mv = new ModelAndView("role/add");
        List<SysPermission> permissionList = sysPermissionService.findAll();
        mv.addObject("permissionList", permissionList);
        return mv;
    }

    //添加
    // @PostMapping("add")
    @GetMapping("add1")
    public String add(SysRole sysRole) {
        if (sysRoleService.add(sysRole)) {
            return "redirect:search";
        } else {
            return "redirect:add";
        }
    }

    //修改
    @GetMapping("mod")
    public ModelAndView mod(int id) {
        ModelAndView mv = new ModelAndView("role/mod");
        SysRole role = sysRoleService.findById(id);
        mv.addObject("role", role);
        List<SysPermission> permissionList = sysPermissionService.findAll();
        mv.addObject("permissionList", permissionList);
        return mv;
    }

    //修改
    // @PostMapping("mod")
    @GetMapping("mod1")
    public String mod(SysRole sysRole) {
        if (sysRoleService.mod(sysRole)) {
            return "redirect:search";
        } else {
            return "redirect:mod";
        }
    }

    //删除
    @GetMapping("del")
    public String del(int id) {
        sysRoleService.del(id);
        return "redirect:search";
    }


}