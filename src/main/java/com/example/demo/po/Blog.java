package com.example.demo.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class Blog {

    public Long id;
    public String title;
    private String content;
    private String first_picture;
    private String flag;
    private Integer views;
    private boolean share_statement;
    private boolean published;
    private boolean recommend;
    private Date create_time;
    private Date update_time;
    private Long type_id;
    private Long user_id;
    private String type_name;

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    @Transient
    private String tagIds;

    public String getTagIds() {
        return tagIds;
    }

    public String getFirst_picture() {
        return first_picture;
    }

    public void setFirst_picture(String first_picture) {
        this.first_picture = first_picture;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public Long getType_id() {
        return type_id;
    }

    public void setType_id(Long type_id) {
        this.type_id = type_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    @Transient
    private Type type;

    @Transient
    private List<Tag> tags = new ArrayList<>();

    @Transient
    private User user;


    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Blog() {
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public boolean isShare_statement() {
        return share_statement;
    }

    public void setShare_statement(boolean share_statement) {
        this.share_statement = share_statement;
    }


    public void init(){
        this.tagIds=tagsToIds(this.getTags());
    }

    private String tagsToIds(List<Tag> tags){
        if(!tags.isEmpty()){
            StringBuffer ids= new StringBuffer();
            boolean flag=false;
            for(Tag tag:tags){
                if(flag){
                    ids.append(",");
                }else{
                    flag=true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        }else{
            return tagIds;
        }
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", first_picture='" + first_picture + '\'' +
                ", flag='" + flag + '\'' +
                ", views=" + views +
                ", share_statement=" + share_statement +
                ", published=" + published +
                ", recommend=" + recommend +
                ", createTime=" + create_time +
                ", updateTime=" + update_time +
                '}';
    }
}
