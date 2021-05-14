package com.example.demo.mapper;

import com.example.demo.po.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TypeMapper {
    void saveType(@Param("name") String name);

    Type getType(Long id);

    List<Type> listType(@Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize);

    List<Type> listAllType();

    void updateType(@Param("id")Long id,@Param("name")String name);

    void deleteType(@Param("id") Long id);

    Type findByName(@Param("name") String name);

    Type findById(@Param("id")Long id);

    void incTypeNum(@Param("id")Long id);

    void decTypeNum(@Param("id")Long id);

}
