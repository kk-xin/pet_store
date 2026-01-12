package cdu.finalproject.petstore.controller;


import cdu.finalproject.petstore.entity.Message;
import cdu.finalproject.petstore.entity.Supply;
import cdu.finalproject.petstore.service.MessageService;
import cdu.finalproject.petstore.service.SupplyService;
import cdu.finalproject.petstore.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("supply")
public class SupplyInfoController {

    @Autowired
    SysUserService sysUserService;
    @Autowired
    MessageService messageService;
    @Autowired
    SupplyService supplyService;

    //某一个商品的详细信息获取
    @RequestMapping("supplyInfo")
    public ModelAndView search(@RequestParam(name="id") String id) {
        ModelAndView mv = new ModelAndView("supply/supplyInform");

        //通过从上一个页面传来的id进行查询
        Supply supplyInfo = supplyService.findById(Integer.valueOf(id));
        List<Message> messageList = messageService.findBySupplyMessageId(Integer.valueOf(id));

        mv.addObject("supplyInfo", supplyInfo);
        mv.addObject("messageList", messageList);
        return mv;
    }

    //商品列表信息获取
    @GetMapping("supplyList")
    public ModelAndView supplyList() {
        ModelAndView mv = new ModelAndView("supply/supplyList");
        List<Supply> supplyList = supplyService.findAll();
        mv.addObject("supplyList", supplyList);
        return mv;
    }

    //发表评论
    @RequestMapping(value = "addmsg",method = RequestMethod.POST)
    public ModelAndView add(@RequestParam("content") String newContent, @RequestParam("supplyId") int supplyId, HttpSession session) {
        //获取session中保存的数据
        String nowUsername = (String) session.getAttribute("username");
        String userimg = (String) session.getAttribute("userimg");

        ModelAndView mv = new ModelAndView("supply/supplyInform");

        //创建date类 获取当前时间并转化其书写格式
        Date date= new Date();
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ) ;
        String nowTime =formatter.format(date);

        Supply supplyInfo = supplyService.findById(supplyId);

        //创建Message类 添加相关信息
        Message newMessage= new Message();
        newMessage.setContent(newContent);
        newMessage.setSyMessage(supplyInfo);
        newMessage.setImg(userimg);
        newMessage.setTime(nowTime);
        newMessage.setUsername(nowUsername);

        messageService.add(newMessage);
        List<Message> messageList = messageService.findByPetMessageId(supplyId);//通过id找到对应的评论
        mv.addObject("supplyInfo", supplyInfo);
        mv.addObject("messageList", messageList);
        return mv;
    }

    //按页查找商品的信息
    @RequestMapping("search")
    public ModelAndView search(Supply condition, @PageableDefault(page = 0, size = 5, sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable) {
        ModelAndView mv = new ModelAndView("supply/list");
        Page<List<Supply>> pageInfo = supplyService.findByPage(condition, pageable);
        mv.addObject("pageInfo", pageInfo);
        mv.addObject("c", condition);
        return mv;
    }

    //添加 跳转到add.html页面
    @GetMapping("add")
    public String add() {
        return "supply/add";
    }

    //添加 add.html页面点击提交后调用add方法存入
    @PostMapping("add")
    public String add(Supply supply) {
        if (supplyService.add(supply)) {
            return "redirect:search";
        } else {
            return "redirect:add";
        }
    }

    //删除
    @GetMapping("del")
    public String del(int id) {
        supplyService.del(id);
        return "redirect:search";
    }

    //图片上传
    @PostMapping("addpost")
    public String add(@RequestParam Map<String, Object> params, @RequestParam("img") MultipartFile file) {
//        -------------------------------
        Supply supply = new Supply();

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
                supply.setImg("/img/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        supply.setName(params.get("supplyname").toString());
        supply.setDate(params.get("date").toString());
        supply.setAddress(params.get("address").toString());
        supply.setPrice(params.get("price").toString());
        supply.setInventory(params.get("inventory").toString());


        if (supplyService.add(supply)) {
            return "redirect:/supply/search";
        } else {
            return "redirect:/supply/add";
        }
    }

    //修改 获取上一个页面传入的id并跳转到mod.html页面
    @GetMapping("mods")
    public ModelAndView modug(HttpSession session,@RequestParam("id") String id) {
        Supply supply = supplyService.findById(Integer.valueOf(id));

        ModelAndView mv = new ModelAndView("supply/mod");
        mv.addObject("supplyInfo", supply);
        return mv;
    }

    //修改 保存新修改的数据
    @PostMapping("mods")
    public String modup(@RequestParam Map<String, Object> params, @RequestParam("img") MultipartFile file) {
        Supply supply = supplyService.findById(Integer.valueOf(params.get("id").toString()));
        supply.setName(params.get("supplyname").toString());
        supply.setDate(params.get("date").toString());
        supply.setAddress(params.get("address").toString());
        supply.setPrice(params.get("price").toString());
        supply.setInventory(params.get("inventory").toString());

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
                supply.setImg("/img/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //保存新数据并跳转
        if (supplyService.mod(supply)) {
            return "redirect:/supply/search";
        } else {
            return "redirect:/supply/search";
        }
    }

}
