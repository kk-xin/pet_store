package cdu.finalproject.petstore.controller;

import cdu.finalproject.petstore.entity.Message;
import cdu.finalproject.petstore.entity.Pet;
import cdu.finalproject.petstore.entity.SysUser;
import cdu.finalproject.petstore.service.MessageService;
import cdu.finalproject.petstore.service.PetService;
import cdu.finalproject.petstore.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.Column;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("pet")
public class PetInfoController {

    @Autowired
    SysUserService sysUserService;
    @Autowired
    MessageService messageService;
    @Autowired
    PetService petService;

    //某一个宠物的详细信息获取
    @RequestMapping("petInfo")
    public ModelAndView search(@RequestParam(name="id") String id) {
        ModelAndView mv = new ModelAndView("pet/petInform");

        //通过从上一个页面传来的id进行查询
        Pet petInfo = petService.findById(Integer.valueOf(id));
        List<Message> messageList = messageService.findByPetMessageId(Integer.valueOf(id));

        mv.addObject("petInfo", petInfo);
        mv.addObject("messageList", messageList);
        return mv;
    }

    //首页宠物展示信息
    @GetMapping("petListIndex")
    public ModelAndView petListIndex() {
        ModelAndView mv = new ModelAndView("pet/petListIndex");
        List<Pet> PetList = petService.findAll();//获取宠物表中全部信息
        mv.addObject("petList", PetList);
        return mv;
    }

    //宠物列表信息获取
    @GetMapping("petList")
    public ModelAndView petList() {
        ModelAndView mv = new ModelAndView("pet/petList");
        List<Pet> PetList = petService.findAll();
        mv.addObject("petList", PetList);
        return mv;
    }

    //发表评论
    @RequestMapping(value = "addmsg",method = RequestMethod.POST)
    public ModelAndView add(@RequestParam("content") String newContent, @RequestParam("petId") int petId, HttpSession session) {
        //获取session中保存的数据
        String username=(String) session.getAttribute("username");
        String userimg=(String) session.getAttribute("userimg");

        ModelAndView mv = new ModelAndView("pet/petInform");

        //创建date类 获取当前时间并转化其书写格式
        Date date= new Date();
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ) ;
        String nowTime =formatter.format(date);

        Pet petInfo = petService.findById(petId);

        //创建Message类 添加相关信息
        Message newMessage= new Message();
        newMessage.setContent(newContent);
        newMessage.setPetMessage(petInfo);
        newMessage.setImg(userimg);
        newMessage.setTime(nowTime);
        newMessage.setUsername(username);
        messageService.add(newMessage);

        List<Message> messageList = messageService.findByPetMessageId(petId);//通过id找到对应的评论
        mv.addObject("petInfo", petInfo);
        mv.addObject("messageList", messageList);
        return mv;
    }

    //按页查找宠物的信息
    @RequestMapping("search")
    public ModelAndView search(Pet condition, @PageableDefault(page = 0, size = 5, sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable) {
        ModelAndView mv = new ModelAndView("pet/list");
        Page<List<Pet>> pageInfo = petService.findByPage(condition, pageable);
        mv.addObject("pageInfo", pageInfo);
        mv.addObject("c", condition);
        return mv;
    }

    //添加 跳转到add.html页面
    @GetMapping("add")
    public String add() {
        return "pet/add";
    }

    //添加 add.html页面点击提交后调用save方法存入
    @PostMapping("add")
    public String add(Pet pet) {
        if (petService.save(pet)) {
            return "redirect:search";//成功则到search中
        } else {
            return "redirect:add";//失败重新显示add.html页面
        }
    }

    //删除
    @GetMapping("del")
    public String del(int id) {
        petService.del(id);
        return "redirect:search";
    }

    //图片上传
    @PostMapping("addpost")
    public String add(@RequestParam Map<String, Object> params, @RequestParam("img") MultipartFile file) {
//        -------------------------------
        Pet pet = new Pet();

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
                pet.setImg("/img/" + fileName);//保存图片路径
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //通过param获取相应数据并用set方法存入
        pet.setName(params.get("petname").toString());
        pet.setBirth(params.get("birth").toString());
        pet.setAge(params.get("age").toString());
        pet.setWeight(params.get("weight").toString());
        pet.setPrice(params.get("price").toString());


        if (petService.save(pet)) {
            return "redirect:/pet/search";
        } else {
            return "redirect:/pet/add";
        }
    }

    //修改 获取上一个页面传入的id并跳转到mod.html页面
    @GetMapping("modp")
    public ModelAndView modug(HttpSession session,@RequestParam("id") String id) {
        Pet pet = petService.findById(Integer.valueOf(id)); //通过上一个页面传入的id调用方法查询

        ModelAndView mv = new ModelAndView("pet/mod");
        mv.addObject("petInfo", pet);
        return mv;
    }

    //修改 保存新修改的数据
    @PostMapping("modp")
    public String modup(@RequestParam Map<String, Object> params, @RequestParam("img") MultipartFile file) {
        //通过param获取相应数据并用set方法存入
        Pet pet = petService.findById(Integer.valueOf(params.get("id").toString()));
        pet.setName(params.get("petname").toString());
        pet.setBirth(params.get("birth").toString());
        pet.setAge(params.get("age").toString());
        pet.setWeight(params.get("weight").toString());
        pet.setPrice(params.get("price").toString());

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
                pet.setImg("/img/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //保存新数据并跳转
        if (petService.save(pet)) {
            return "redirect:/pet/search";
        } else {
            return "redirect:/pet/search";
        }
    }

}
