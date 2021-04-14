package com.example.demo.controller;

import com.example.demo.po.Blog;
import com.example.demo.po.Tag;
import com.example.demo.service.BlogAndTagService;
import com.example.demo.service.BlogService;
import com.example.demo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.LinkedList;
import java.util.List;


@Controller
public class TagsShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogAndTagService blogAndTagService;

    @GetMapping("/tags/{id}/{num}/{size}")
    public String tags(@PathVariable("id")Long id, @PathVariable("num")Integer num,
                        @PathVariable("size")Integer size, Model model){

        List<Tag> tags=tagService.listAllTag();
        if(id==-1){
            id=tags.get(0).getId();
        }

        List<Long> blogIds=blogAndTagService.listBlogByTagId(id);
        List<Blog> Blogs=new LinkedList<>();

        for(Long blogId : blogIds){
            Blogs.add(blogService.getBlog(blogId));
        }

        model.addAttribute("tags",tags);
        model.addAttribute("page",Blogs);
        model.addAttribute("activeTagId",id);
        model.addAttribute("pageNum",blogService.listBlogById(0,99999,id).size()/size+1);
        model.addAttribute("size",size);

        return "tags";
    }
}
