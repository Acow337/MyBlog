package com.example.demo.mapper;

import com.example.demo.po.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface BlogMapper {

    Date getCreateTime(@Param("id") Long id);

    Blog getBlog(@Param("id") Long id);

    Long getBlogId(@Param("title") String title);

    List<Blog> listBlog(@Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize);

    List<Blog> listAllBlog();

    List<Blog> listBlogByTypeId(@Param("typeId")Long typeId);

    List<Blog> listBlogById(@Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize,
                            @Param("id") Long id);

    List<Blog> listBlogByYear(@Param("year") String year);

    List<Blog> listNewBlog(@Param("size")Integer size);

    Blog listBlogCon(@Param("id") Long id);

    Map<String,List<Blog>> archiveBlog();

    void saveBlog(@Param("blog") Blog blog);

    void updateBlog(@Param("id") Long id,@Param("newBlog")Blog newBlog);

    void deleteBlog(@Param("id") Long id);

    List<Blog> listBlogRec(@Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize);

    List<Blog> listBlogQue(@Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize,@Param("query")String query);

    List<String> getYear();

}
