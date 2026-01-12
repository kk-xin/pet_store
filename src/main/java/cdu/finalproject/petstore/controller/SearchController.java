package cdu.finalproject.petstore.controller;


import cdu.finalproject.petstore.entity.Message;
import cdu.finalproject.petstore.entity.Pet;
import cdu.finalproject.petstore.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("search")
public class SearchController {
    @Autowired
    PetService petService;

    //搜索
    @GetMapping("search")
    public ModelAndView search() {
        ModelAndView mv = new ModelAndView("search/search");
        return mv;
    }

    //搜索
    @PostMapping("result")
    public ModelAndView doSearch(@RequestParam(name="petName") String name) {
        ModelAndView mv = new ModelAndView("search/searchResult");
        List<Pet> petList = petService.findByName(name);//获取搜索框中的数据调用方法查询
        mv.addObject("petList", petList);
        return mv;
    }

}
