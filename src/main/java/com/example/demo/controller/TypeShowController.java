package com.example.demo.controller;

import com.example.demo.po.Type;
import com.example.demo.service.BlogService;
import com.example.demo.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}/{num}/{size}")
    public String types(@PathVariable("id")Long id,@PathVariable("num")Integer num,
                        @PathVariable("size")Integer size, Model model){

        List<Type> types=typeService.listAllType();
        if(id==-1){
            id=types.get(0).getId();
        }

        model.addAttribute("types",types);
        model.addAttribute("page",blogService.listBlogById(num,size,id));
        model.addAttribute("activeTypeId",id);
        model.addAttribute("pageNum",blogService.listBlogById(0,99999,id).size()/size+1);
        model.addAttribute("size",size);

        return "types";
    }
}
