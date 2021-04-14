package com.example.demo.po;

import com.sun.istack.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Type {

    public Long id;

    @NotNull
    private String name;

    private List<Blog> blogs=new ArrayList<>();

    public Type(){

    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
