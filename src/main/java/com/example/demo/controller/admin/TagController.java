package com.example.demo.controller.admin;

import com.example.demo.po.Tag;
import com.example.demo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags/{num}/{size}")
    public ModelAndView tags(@PathVariable("num") Integer num,@PathVariable("size") Integer size){

        ModelAndView mv=new ModelAndView();
        mv.addObject("page",tagService.listTag(num,size));
        mv.addObject("num",num);
        mv.addObject("size",size);
        mv.addObject("pageNum",tagService.listAllTag().size()/size+1);
        mv.setViewName("admin/tags");
        return mv;
    }


    @RequestMapping("/tags")
    public String saveTag(@Validated Tag tag, BindingResult result){

        if(tagService.findByName(tag.getName())!=null){
            return "admin/tags-input";
        }

        tagService.saveTag(tag.getName());
        return "redirect:tags/0/6";
    }

    @GetMapping("tags/input")
    public String input(){
        return "admin/tags-input";
    }

    @RequestMapping("/tags/{id}/delete")
    public String delete(@PathVariable("id")Long id){
        tagService.deleteTag(id);
        return "redirect:/admin/tags/0/6";
    }




}