package cdu.finalproject.petstore.controller;

import cdu.finalproject.petstore.entity.SysRole;
import cdu.finalproject.petstore.entity.SysUser;
import cdu.finalproject.petstore.service.SysRoleService;
import cdu.finalproject.petstore.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@Controller
@RequestMapping("user")
public class SysUserController {

    @Autowired
    SysUserService sysUserService;
    @Autowired
    SysRoleService sysRoleService;

    //按页查找用户信息
    @RequestMapping("search")
    public ModelAndView search(SysUser condition, @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        ModelAndView mv = new ModelAndView("user/list");
        List<SysRole> roleList = sysRoleService.findAll();
        Page<List<SysUser>> pageInfo = sysUserService.findByPage(condition, pageable);

        mv.addObject("pageInfo", pageInfo);
        mv.addObject("roleList", roleList);
        mv.addObject("c", condition);
        return mv;
    }

    //添加
    @GetMapping("add")
    public ModelAndView add() {
        ModelAndView mv = new ModelAndView("user/add");
        return mv;
    }
    @GetMapping("add2")
    public ModelAndView add2() {
        ModelAndView mv = new ModelAndView("user/add2");
        List<SysRole> roleList = sysRoleService.findAll();
        mv.addObject("roleList", roleList);
        return mv;
    }

    //添加
    @PostMapping("addpost")
    public String add(@RequestParam Map<String, Object> params, @RequestParam("img") MultipartFile file) {
        SysUser user = new SysUser();

        //通过param获取相应数据并用set方法存入
        user.setUsername(params.get("username").toString());
        user.setPassword(params.get("password").toString());
        user.setEmail(params.get("email").toString());

        SysRole sysRole = sysRoleService.findById(3);
        List<SysRole> roleList = new ArrayList<SysRole>();
        roleList.add(sysRole);
        user.setRoles(roleList);
//        -------------------------------

        //上传图片
        //如果文件不存在上传失败
        if (file.isEmpty()) {
            System.out.println("文件不存在");
        } else {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String fileName = formatter.format(date);
            fileName = fileName + ".jpg";
            //获取文件名字
//            String fileName = file.getOriginalFilename();
            //设置编译后文件存在路径
            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/img/";
            //获取项目路径
            File directory = new File("src/main/resources/static/img");
            String paths = null;
            try {
                paths = directory.getCanonicalPath();
                File dest = new File(paths + '/' + fileName);
                System.out.println(dest.getAbsoluteFile());

                FileInputStream fileInputStream = (FileInputStream) file.getInputStream();
                //以流的方式上传
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + File.separator + fileName));
                byte[] bs = new byte[1024];
                int len;
                while ((len = fileInputStream.read(bs)) != -1) {
                    bos.write(bs, 0, len);
                }
                bos.flush();
                bos.close();
                //文件上传
                file.transferTo(dest);
                user.setImg("/img/" + fileName);//添加图片路径
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (sysUserService.add(user)) {
            return "redirect:/login";
        } else {
            return "redirect:/register";
        }
    }
    @PostMapping("addpost2")
    public String add2(@RequestParam Map<String, Object> params, @RequestParam("img") MultipartFile file) {
        SysUser user = new SysUser();

        //通过param获取相应数据并用set方法存入
        user.setUsername(params.get("username").toString());
        user.setPassword(params.get("password").toString());
        user.setEmail(params.get("email").toString());

        SysRole sysRole = sysRoleService.findById(Integer.valueOf(params.get("roles").toString()));
        List<SysRole> roleList = new ArrayList<SysRole>();
        roleList.add(sysRole);
        user.setRoles(roleList);
//        -------------------------------
        //上传图片
        //如果文件不存在上传失败
        if (file.isEmpty()) {
            System.out.println("文件不存在");
        } else {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String fileName = formatter.format(date);
            fileName = fileName + ".jpg";
            //获取文件名字
//            String fileName = file.getOriginalFilename();
            //设置编译后文件存在路径
            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/img/";
            //获取项目路径
            File directory = new File("src/main/resources/static/img");
            String paths = null;
            try {
                paths = directory.getCanonicalPath();
                File dest = new File(paths + '/' + fileName);
                System.out.println(dest.getAbsoluteFile());

                FileInputStream fileInputStream = (FileInputStream) file.getInputStream();
                //以流的方式上传
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + File.separator + fileName));
                byte[] bs = new byte[1024];
                int len;
                while ((len = fileInputStream.read(bs)) != -1) {
                    bos.write(bs, 0, len);
                }
                bos.flush();
                bos.close();
                //文件上传
                file.transferTo(dest);
                user.setImg("/img/" + fileName);//添加图片路径
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (sysUserService.add(user)) {
            return "redirect:/search";
        } else {
            return "redirect:/add2";
        }
    }

    //修改
    @GetMapping("modu")
    public ModelAndView modug(HttpSession session) {
        int userid = (int) session.getAttribute("userid");
        ModelAndView mv = new ModelAndView("user/mod");
        SysUser user = sysUserService.findById(Integer.valueOf(userid));
        mv.addObject("user", user);
        return mv;
    }

    //修改
    @GetMapping("modu2")
    public ModelAndView modug2(@RequestParam("id") String id) {
        ModelAndView mv = new ModelAndView("user/mod2");
        SysUser user = sysUserService.findById(Integer.valueOf(id));//通过param获取相应数据
        List<SysRole> roleList = sysRoleService.findAll();
        mv.addObject("user", user);
        mv.addObject("roleList", roleList);
        return mv;
    }

    //修改
    @PostMapping("modu")
    public String modup(@RequestParam Map<String, Object> params, @RequestParam("img") MultipartFile file) {
        SysUser user = sysUserService.findById(Integer.valueOf(params.get("id").toString()));//通过param获取相应数据

        //通过param获取相应数据并用set方法存入
        user.setUsername(params.get("username").toString());
        user.setPassword(params.get("password").toString());
        user.setEmail(params.get("email").toString());

        //上传图片
        //如果文件不存在上传失败
        if (file.isEmpty()) {
            System.out.println("文件不存在");
        } else {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String fileName = formatter.format(date);
            fileName = fileName + ".jpg";
            //获取文件名字
//            String fileName = file.getOriginalFilename();
            //设置编译后文件存在路径
            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/img/";
            //获取项目路径
            File directory = new File("src/main/resources/static/img");
            String paths = null;
            try {
                paths = directory.getCanonicalPath();
                File dest = new File(paths + '/' + fileName);
                System.out.println(dest.getAbsoluteFile());

                FileInputStream fileInputStream = (FileInputStream) file.getInputStream();
                //以流的方式上传
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + File.separator + fileName));
                byte[] bs = new byte[1024];
                int len;
                while ((len = fileInputStream.read(bs)) != -1) {
                    bos.write(bs, 0, len);
                }
                bos.flush();
                bos.close();
                //文件上传
                file.transferTo(dest);
                user.setImg("/img/" + fileName);//保存图片路径
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (sysUserService.mod(user)) {
            return "redirect:/login";
        } else {
            return "redirect:/modu";
        }
    }

    //修改
    @PostMapping("modu2")
    public String modup2(@RequestParam Map<String, Object> params, @RequestParam("img") MultipartFile file) {
        SysUser user = sysUserService.findById(Integer.valueOf(params.get("id").toString()));

        //通过param获取相应数据并用set方法存入
        user.setUsername(params.get("username").toString());
        user.setPassword(params.get("password").toString());
        user.setEmail(params.get("email").toString());

        SysRole sysRole = sysRoleService.findById(Integer.valueOf(params.get("roles").toString()));
        List<SysRole> roleList = new ArrayList<SysRole>();
        roleList.add(sysRole);//添加
        user.setRoles(roleList);//用set方法存入

        //上传图片
        //如果文件不存在上传失败
        if (file.isEmpty()) {
            System.out.println("文件不存在");
        } else {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String fileName = formatter.format(date);
            fileName = fileName + ".jpg";
            //获取文件名字
//            String fileName = file.getOriginalFilename();
            //设置编译后文件存在路径
            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/img/";
            //获取项目路径
            File directory = new File("src/main/resources/static/img");
            String paths = null;
            try {
                paths = directory.getCanonicalPath();
                File dest = new File(paths + '/' + fileName);
                System.out.println(dest.getAbsoluteFile());

                FileInputStream fileInputStream = (FileInputStream) file.getInputStream();
                //以流的方式上传
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + File.separator + fileName));
                byte[] bs = new byte[1024];
                int len;
                while ((len = fileInputStream.read(bs)) != -1) {
                    bos.write(bs, 0, len);
                }
                bos.flush();
                bos.close();
                //文件上传
                file.transferTo(dest);
                user.setImg("/img/" + fileName);//添加图片路径
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (sysUserService.mod(user)) {
            return "redirect:search";
        } else {
            return "redirect:/modu2";
        }
    }

    //删除
    @GetMapping("del")
    public String del(int id) {
        sysUserService.del(id);
        return "redirect:search";
    }

    //重置密码
    @GetMapping("resetPwd")
    public String resetPwd(int id) {
        sysUserService.resetPwd(id);
        return "redirect:/login";
    }

    @GetMapping("center")
    public ModelAndView center(HttpSession session) {
        //获取session中保存的数据
        int userid = (int) session.getAttribute("userid");
        String username = (String) session.getAttribute("username");
        String userimg = (String) session.getAttribute("userimg");

        ModelAndView mv = new ModelAndView("user/userCenter");
        mv.addObject("username", username);
        mv.addObject("userimg", userimg);
        mv.addObject("userid", userid);
        return mv;
    }


}