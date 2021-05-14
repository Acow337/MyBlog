package com.example.demo.controller.admin;

import com.example.demo.po.Blog;
import com.example.demo.po.Tag;
import com.example.demo.po.Type;
import com.example.demo.po.User;
import com.example.demo.service.BlogAndTagService;
import com.example.demo.service.BlogService;
import com.example.demo.service.TagService;
import com.example.demo.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String INPUT = "admin/blogs-input";
    private static final String UPDATE = "admin/blogs-update";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "redirect:/admin/blogs/0/6";

    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;
    @Autowired
    TagService tagService;
    @Autowired
    BlogAndTagService blogAndTagService;

    ModelAndView mv=new ModelAndView();

    @GetMapping("/blogs/{num}/{size}")
    public ModelAndView blogs(@PathVariable("num") Integer num,@PathVariable("size")Integer size){

        mv.addObject("page",blogService.listBlog(num,size));
        mv.addObject("types",typeService.listAllType());
        mv.addObject("num",num);
        mv.addObject("size",size);
        mv.addObject("pageNum",blogService.listAllBlog().size()/size+1);
        mv.setViewName("admin/blogs");

        return mv;
    }

    @PostMapping("/blogs/search/{num}/{size}")
    public String search(@PathVariable("num") Integer num ,
                         @PathVariable("size") Integer size,
                         @RequestParam("type") String typeId,
                         Model model){

        if(typeId==""){
            return "redirect:/admin/blogs/0/6";
        }

        Long newId=Long.valueOf(typeId);

        model.addAttribute("page",blogService.listBlogById(num,size,newId));
        model.addAttribute("types",typeService.listAllType());
        model.addAttribute("num",num);
        model.addAttribute("size",size);
        model.addAttribute("pageNum",blogService.listAllBlog().size()/size+1);

        return "admin/blogs";
    }

    @GetMapping("/blogs/input")
    public String input(Model model){
        model.addAttribute("types",typeService.listAllType());
        model.addAttribute("tags",tagService.listAllTag());
        return INPUT;
    }

    @GetMapping("/blogs/{id}/update")
    public String editInput(@PathVariable Long id,
                            Model model,
                            HttpSession session){

        model.addAttribute("tags",tagService.listAllTag());
        model.addAttribute("types",typeService.listAllType());
        Blog blog=blogService.getBlog(id);
        blog.setUpdate_time(new Date());

        blog.setType(typeService.getType(blog.getType_id()));
        blog.setUser((User)session.getAttribute("user"));
        blog.init();
        model.addAttribute("blog",blog);

        return UPDATE;
    }

    @RequestMapping("/blogs") //上传博客
    public String post(Blog blog,
                       @RequestParam("type_id")String type_str,
                       @RequestParam("tagIds")String tags_str,
                       HttpSession session){
        blog.setType_name(typeService.findById(blog.getType_id()).getName());//获得博客的type_name

        User user=(User)session.getAttribute("user");

        blog.setUser_id(user.getId());

        blogService.saveBlog(blog); //保存博客信息到数据库中
        Long blogId=blogService.getBlogId(blog.title);

        String[] sourceStrArray = tags_str.split(",");
        for (int i=0;i<sourceStrArray.length;i++){
            blogAndTagService.insert(blogId,Long.valueOf(sourceStrArray[i]));
            tagService.incTagNum(Long.valueOf(sourceStrArray[i]));
        }

        Long type_id=Long.valueOf(type_str);

        blog.setType_id(type_id);
        blog.setUser_id(((User) session.getAttribute("user")).getId());

        blog.setType(typeService.getType(blog.getType_id()));
        blog.setUser((User)session.getAttribute("user"));

        typeService.incTypeNum(blog.getType_id());


        return REDIRECT_LIST;
    }

    @PostMapping("/blogs/update") //更新博客
    public String update(Blog blog){
        Date creatTime=blogService.getCreateTime(blog.id);
        blog.setCreate_time(creatTime);
        blog.setUpdate_time(new Date());
        blog.setViews(0);
        blog.setType_name(typeService.findById(blog.getType_id()).getName());//获得博客的type_name
        blogService.updateBlog(blog.id,blog);
        return "redirect:/admin/blogs/0/6";
    }

    @RequestMapping("/blogs/{id}/delete")  //通过id删除博客
    public String delete(@PathVariable("id") Long id){
        Blog blog=blogService.getBlog(id);
        typeService.decTypeNum(blog.getType_id());

        List<Long> list = blogAndTagService.listTagIdByBlog(blog.getId());

        for(Long tagId:list){
            tagService.decTagNum(tagId);
        }

        blogService.deleteBlog(id);
        return "redirect:/admin/blogs/0/6";
    }

}
