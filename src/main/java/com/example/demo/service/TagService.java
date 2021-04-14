package com.example.demo.service;

import com.example.demo.mapper.TagMapper;
import com.example.demo.mapper.TypeMapper;
import com.example.demo.po.Tag;
import com.example.demo.po.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class TagService {
    @Autowired
    TagMapper tagMapper;

    public void saveTag(String name){
        tagMapper.saveTag(name);
    }

    public Tag getTag(Long id){
        return tagMapper.getTag(id);
    }

    public List<Tag> listTag(Integer pageNo, Integer pageSize){
        return tagMapper.listTag(pageNo,pageSize);
    }

    public List<Tag> listAllTag(){return tagMapper.listAllTag();};

    public List<Tag> listTags(String ids){

        HashMap map=new HashMap();
        ArrayList<Long> tagIds=new ArrayList<Long>();

        String sourceStr = ids;
        String[] sourceStrArray = sourceStr.split(",");
        for (int i=0;i<sourceStrArray.length;i++){
            tagIds.add(Long.valueOf(sourceStrArray[i]));
        }

        map.put("tagIds",tagIds);

        return tagMapper.listTags(map);
    }

    public void updateTag(Long id,String name){
        tagMapper.updateTag(id,name);
    }

    public void deleteTag(Long id){
        tagMapper.deleteTag(id);
    }

    public Tag findByName(@Param("id") String name){
        return tagMapper.findByName(name);
    }
}
