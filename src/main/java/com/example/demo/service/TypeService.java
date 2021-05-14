package com.example.demo.service;

import com.example.demo.mapper.TypeMapper;
import com.example.demo.po.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {

    @Autowired
    TypeMapper typeMapper;

    public void saveType(String name){
         typeMapper.saveType(name);
    }

    public Type getType(Long id){
        return typeMapper.getType(id);
    }

    public List<Type> listType(Integer pageNo, Integer pageSize){
        return typeMapper.listType(pageNo,pageSize);
    }

    public List<Type> listAllType(){return typeMapper.listAllType();};

    public void updateType(Long id,String name){
        typeMapper.updateType(id,name);
    }

    public void deleteType(Long id){
        typeMapper.deleteType(id);
    }

    public Type findByName(String name){
        return typeMapper.findByName(name);
    }

    public Type findById(Long id){
        return typeMapper.findById(id);
    }

    public void incTypeNum(Long id){
        typeMapper.incTypeNum(id);
    }

    public void decTypeNum(Long id){
        typeMapper.decTypeNum(id);
    }

}
