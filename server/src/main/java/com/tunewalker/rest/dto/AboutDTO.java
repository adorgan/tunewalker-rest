package com.tunewalker.rest.dto;

public class AboutDTO {
    private String id;
    private String blogpost_title;
    private String blogpost_intro;
    private String blogpost_hero_photo;
    private String blogpost_hero_photo_caption;
    private String blogpost_map;
    private String createdAt;
    private String updatedAt;

    public AboutDTO() {
    }

    public AboutDTO(String id, String blogpost_title, String blogpost_intro, String blogpost_hero_photo, String blogpost_hero_photo_caption, String blogpost_map, String createdAt, String updatedAt) {
        this.id = id;
        this.blogpost_title = blogpost_title;
        this.blogpost_intro = blogpost_intro;
        this.blogpost_hero_photo = blogpost_hero_photo;
        this.blogpost_hero_photo_caption = blogpost_hero_photo_caption;
        this.blogpost_map = blogpost_map;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBlogpost_title() {
        return blogpost_title;
    }

    public void setBlogpost_title(String blogpost_title) {
        this.blogpost_title = blogpost_title;
    }

    public String getBlogpost_intro() {
        return blogpost_intro;
    }

    public void setBlogpost_intro(String blogpost_intro) {
        this.blogpost_intro = blogpost_intro;
    }

    public String getBlogpost_hero_photo() {
        return blogpost_hero_photo;
    }

    public void setBlogpost_hero_photo(String blogpost_hero_photo) {
        this.blogpost_hero_photo = blogpost_hero_photo;
    }

    public String getBlogpost_hero_photo_caption() {
        return blogpost_hero_photo_caption;
    }

    public void setBlogpost_hero_photo_caption(String blogpost_hero_photo_caption) {
        this.blogpost_hero_photo_caption = blogpost_hero_photo_caption;
    }

    public String getBlogpost_map() {
        return blogpost_map;
    }

    public void setBlogpost_map(String blogpost_map) {
        this.blogpost_map = blogpost_map;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
