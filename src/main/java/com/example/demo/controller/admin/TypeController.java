package com.example.demo.controller.admin;

import com.example.demo.po.Type;
import com.example.demo.service.TypeService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types/{num}/{size}")
    public ModelAndView types(@PathVariable("num") Integer num,@PathVariable("size") Integer size){

        ModelAndView mv=new ModelAndView();
        mv.addObject("page",typeService.listType(num,size));
        mv.addObject("num",num);
        mv.addObject("size",size);
        mv.addObject("pageNum",typeService.listAllType().size()/size+1);
        mv.setViewName("admin/types");
        return mv;
    }


    @RequestMapping("/types")
    public String saveType(@Validated Type type,BindingResult result){

        if(typeService.findByName(type.getName())!=null){
            return "admin/types-input";
        }

        typeService.saveType(type.getName());
        return "redirect:types/0/6";
    }

    @GetMapping("types/input")
    public String input(){
        return "admin/types-input";
    }

    @RequestMapping("/types/{id}/delete")
    public String delete(@PathVariable("id")Long id){
        typeService.deleteType(id);
        return "redirect:/admin/types/0/6";
    }




}
