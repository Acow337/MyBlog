package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BlogAndTagMapper {
    void insert(@Param("blogId")Long blogId,@Param("tagId")Long tagId);

    List<Long> listBlogByTagId(@Param("tagId")Long tagId);

    List<Long> listTagIdByBlog(@Param("blogId")Long blogId);
}
