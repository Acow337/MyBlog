package com.example.demo.mapper;

import com.example.demo.po.Tag;
import com.example.demo.po.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TagMapper {
    void saveTag(@Param("name") String name);

    Tag getTag(Long id);

    List<Tag> listTag(@Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize);

    List<Tag> listAllTag();

    void updateTag(@Param("id")Long id,@Param("name")String name);

    void deleteTag(@Param("id") Long id);

    Type findByName(@Param("id") String name);
}