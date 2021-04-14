package com.example.demo.service;

import com.example.demo.mapper.BlogAndTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BlogAndTagService {
    @Autowired
    private BlogAndTagMapper blogAndTagMapper;

    public void insert(Long blogId,Long tagId){
        blogAndTagMapper.insert(blogId,tagId);
    }

    public List<Long> listBlogByTagId(Long tagId){
        return blogAndTagMapper.listBlogByTagId(tagId);
    }
}
