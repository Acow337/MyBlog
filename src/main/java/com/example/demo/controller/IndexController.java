package com.example.demo.controller;

import com.example.demo.service.BlogService;
import com.example.demo.service.TagService;
import com.example.demo.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
public class IndexController {

    @Autowired
    TagService tagService;

    @Autowired
    BlogService blogService;

    @Autowired
    TypeService typeService;

    @GetMapping("/{num}/{size}")
    public String index(Model model,
                        @PathVariable("num")Integer num,
                        @PathVariable("size")Integer size){

//        HashMap<Integer,Integer> map = new HashMap<>();
//        for(type : typeService.listAllType()){
//            map.put(,blogService.listBlogByTypeId());
//        }
//        model.addAttribute("typeCount",);

        model.addAttribute("num", num);
        model.addAttribute("size",size);

        model.addAttribute("blogAmount",blogService.listAllBlog().size());
        model.addAttribute("page",blogService.listBlog(num,size));
        model.addAttribute("types",typeService.listType(0,6));
        model.addAttribute("tags",tagService.listTag(0,10));
        model.addAttribute("pageNum",blogService.listAllBlog().size()/size+1);
        model.addAttribute("recommendBlogs",blogService.listBlogRec(0,6));

        return "index";
    }

    @GetMapping("/types/{num}/{size}")
    public String types(Model model,
                       @PathVariable("num")Integer num,
                       @PathVariable("size")Integer size){

        model.addAttribute("num",num);
        model.addAttribute("size",size);

        model.addAttribute("page",blogService.listBlog(num,size));
        model.addAttribute("types",typeService.listType(0,6));
        model.addAttribute("pageNum",blogService.listAllBlog().size()/size+1);

        return "types";
    }

    @GetMapping("/tags/{num}/{size}")
    public String tags(Model model,
                       @PathVariable("num")Integer num,
                       @PathVariable("size")Integer size){

        model.addAttribute("num",num);
        model.addAttribute("size",size);

        model.addAttribute("page",blogService.listBlog(num,size));
        model.addAttribute("tags",tagService.listTag(0,10));
        model.addAttribute("pageNum",blogService.listAllBlog().size()/size+1);

        return "tags";
    }

    @PostMapping("/search/{num}/{size}")
    public String search(Model model,
                        @PathVariable("num")Integer num,
                        @PathVariable("size")Integer size,
                        @RequestParam("query")String query){

        model.addAttribute("num", num);

        model.addAttribute("size",size);

        model.addAttribute("query",query);
        model.addAttribute("blogAmount",blogService.listAllBlog().size());
        model.addAttribute("page",blogService.listBlogQue(num,size,query));
        model.addAttribute("types",typeService.listType(0,6));
        model.addAttribute("tags",tagService.listTag(0,10));
        model.addAttribute("pageNum",blogService.listAllBlog().size()/size+1);
        model.addAttribute("recommendBlogs",blogService.listBlogRec(0,6));

        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable("id")Long id,Model model){

        model.addAttribute("blog",blogService.listBlogCon(id));
        return "blog";
    }


    @GetMapping("/footer/newBlog")
    public String newBlogs(Model model){
        model.addAttribute("newBlogs",blogService.listNewBlog(3));


        return "fragments :: newBlogList";
    }


}
