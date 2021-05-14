package com.example.demo.service;

import com.example.demo.NotFoundException;
import com.example.demo.mapper.BlogMapper;
import com.example.demo.po.Blog;
import com.example.demo.util.MarkdownUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogService {
    @Autowired
    BlogMapper blogMapper;

    public Date getCreateTime(@Param("id") Long id){
        return blogMapper.getCreateTime(id);
    }

    public Blog getBlog(Long id){
        return blogMapper.getBlog(id);
    }

    public Long getBlogId(String title){
        return blogMapper.getBlogId(title);
    }

    public List<Blog> listBlog(Integer pageNo,Integer pageSize){
        return blogMapper.listBlog(pageNo,pageSize);
    }

    public List<Blog> listAllBlog(){
        return blogMapper.listAllBlog();
    }

    public List<Blog> listBlogByTypeId(Long typeId){
        return blogMapper.listBlogByTypeId(typeId);
    }

    public List<Blog> listBlogById(Integer pageNo,Integer pageSize, Long id){
        return blogMapper.listBlogById(pageNo,pageSize,id);
    }

    public List<Blog> listNewBlog(Integer size){
        return blogMapper.listNewBlog(size);
    }

    public Blog listBlogCon(Long id){
        Blog blog=blogMapper.getBlog(id);
        if(blog==null){
            throw new NotFoundException("该博客不存在");
        }
        Blog b=new Blog();
        BeanUtils.copyProperties(blog,b);
        String content=b.getContent();
        b.setContent(MarkdownUtils.markdownToHtml(content));

        return b;
    }

    @Transactional
    public void saveBlog(Blog newBlog){

        if(newBlog.getId()==null){
            newBlog.setCreate_time(new Date());
            newBlog.setUpdate_time(new Date());
            newBlog.setViews(0);
        }else{
            newBlog.setUpdate_time(new Date());
        }

        blogMapper.saveBlog(newBlog);
    }



    @Transactional
    public void updateBlog(Long id,Blog newBlog){
        blogMapper.updateBlog(id,newBlog);
    }

    @Transactional
    public void deleteBlog(Long id){
        blogMapper.deleteBlog(id);
    }

    public List<Blog> listBlogRec(Integer pageNo,Integer pageSize){
        return blogMapper.listBlogRec(pageNo,pageSize);
    }

    public List<Blog> listBlogQue(Integer pageNo, Integer pageSize, String query){
        query="%"+query+"%";
        return blogMapper.listBlogQue(pageNo,pageSize,query);
    }

    public List<String> getYear(){
        return blogMapper.getYear();
    }

    public Map<String,List<Blog>> archiveBlog(){
        List<String> years=blogMapper.getYear();
        Map<String,List<Blog>> map = new LinkedHashMap<>();
        for(String year : years){
            map.put(year,blogMapper.listBlogByYear(year));
        }

        return map;
    }

}
