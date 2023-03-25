package com.tunewalker.rest.dto;

import com.tunewalker.rest.model.AlbumArtDetails;
import com.tunewalker.rest.model.CouldaShouldaDetails;
import com.tunewalker.rest.model.MapDetails;

public class BlogpostDTO {

    private String id;
    private String blogpost_album_1_rank;
    private String blogpost_album_2_rank;
    private String blogpost_title;
    private String blogpost_intro;
    private String blogpost_review_1;
    private String blogpost_review_2;
    private String blogpost_hero_photo;
    private String blogpost_hero_photo_caption;
    private String blogpost_album_art_1;
    private String blogpost_album_art_2;
    private AlbumArtDetails blogpost_album_art_1_details;
    private AlbumArtDetails blogpost_album_art_2_details;
    private String blogpost_map;
    private MapDetails blogpost_map_details;
    private String blogpost_coulda_shoulda_album_art_1;
    private String blogpost_coulda_shoulda_album_art_2;
    private CouldaShouldaDetails blogpost_coulda_shoulda_1_details;
    private CouldaShouldaDetails blogpost_coulda_shoulda_2_details;
    private String createdAt;
    private String updatedAt;
    public BlogpostDTO(){};

    public BlogpostDTO(String _id, String blogpost_album_1_rank, String blogpost_album_2_rank, String blogpost_title, String blogpost_intro, String blogpost_review_1, String blogpost_review_2, String blogpost_hero_photo, String blogpost_hero_photo_caption, String blogpost_album_art_1, String blogpost_album_art_2, AlbumArtDetails blogpost_album_art_1_details, AlbumArtDetails blogpost_album_art_2_details, String blogpost_map, MapDetails blogpost_map_details, String blogpost_coulda_shoulda_album_art_1, String blogpost_coulda_shoulda_album_art_2, CouldaShouldaDetails blogpost_coulda_shoulda_1_details, CouldaShouldaDetails blogpost_coulda_shoulda_2_details, String createdAt, String updatedAt) {
        this.id = _id;
        this.blogpost_album_1_rank = blogpost_album_1_rank;
        this.blogpost_album_2_rank = blogpost_album_2_rank;
        this.blogpost_title = blogpost_title;
        this.blogpost_intro = blogpost_intro;
        this.blogpost_review_1 = blogpost_review_1;
        this.blogpost_review_2 = blogpost_review_2;
        this.blogpost_hero_photo = blogpost_hero_photo;
        this.blogpost_hero_photo_caption = blogpost_hero_photo_caption;
        this.blogpost_album_art_1 = blogpost_album_art_1;
        this.blogpost_album_art_2 = blogpost_album_art_2;
        this.blogpost_album_art_1_details = blogpost_album_art_1_details;
        this.blogpost_album_art_2_details = blogpost_album_art_2_details;
        this.blogpost_map = blogpost_map;
        this.blogpost_map_details = blogpost_map_details;
        this.blogpost_coulda_shoulda_album_art_1 = blogpost_coulda_shoulda_album_art_1;
        this.blogpost_coulda_shoulda_album_art_2 = blogpost_coulda_shoulda_album_art_2;
        this.blogpost_coulda_shoulda_1_details = blogpost_coulda_shoulda_1_details;
        this.blogpost_coulda_shoulda_2_details = blogpost_coulda_shoulda_2_details;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBlogpost_album_1_rank() {
        return blogpost_album_1_rank;
    }

    public void setBlogpost_album_1_rank(String blogpost_album_1_rank) {
        this.blogpost_album_1_rank = blogpost_album_1_rank;
    }

    public String getBlogpost_album_2_rank() {
        return blogpost_album_2_rank;
    }

    public void setBlogpost_album_2_rank(String blogpost_album_2_rank) {
        this.blogpost_album_2_rank = blogpost_album_2_rank;
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

    public String getBlogpost_review_1() {
        return blogpost_review_1;
    }

    public void setBlogpost_review_1(String blogpost_review_1) {
        this.blogpost_review_1 = blogpost_review_1;
    }

    public String getBlogpost_review_2() {
        return blogpost_review_2;
    }

    public void setBlogpost_review_2(String blogpost_review_2) {
        this.blogpost_review_2 = blogpost_review_2;
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

    public String getBlogpost_album_art_1() {
        return blogpost_album_art_1;
    }

    public void setBlogpost_album_art_1(String blogpost_album_art_1) {
        this.blogpost_album_art_1 = blogpost_album_art_1;
    }

    public String getBlogpost_album_art_2() {
        return blogpost_album_art_2;
    }

    public void setBlogpost_album_art_2(String blogpost_album_art_2) {
        this.blogpost_album_art_2 = blogpost_album_art_2;
    }

    public AlbumArtDetails getBlogpost_album_art_1_details() {
        return blogpost_album_art_1_details;
    }

    public void setBlogpost_album_art_1_details(AlbumArtDetails blogpost_album_art_1_details) {
        this.blogpost_album_art_1_details = blogpost_album_art_1_details;
    }

    public AlbumArtDetails getBlogpost_album_art_2_details() {
        return blogpost_album_art_2_details;
    }

    public void setBlogpost_album_art_2_details(AlbumArtDetails blogpost_album_art_2_details) {
        this.blogpost_album_art_2_details = blogpost_album_art_2_details;
    }

    public String getBlogpost_map() {
        return blogpost_map;
    }

    public void setBlogpost_map(String blogpost_map) {
        this.blogpost_map = blogpost_map;
    }

    public MapDetails getBlogpost_map_details() {
        return blogpost_map_details;
    }

    public void setBlogpost_map_details(MapDetails blogpost_map_details) {
        this.blogpost_map_details = blogpost_map_details;
    }

    public String getBlogpost_coulda_shoulda_album_art_1() {
        return blogpost_coulda_shoulda_album_art_1;
    }

    public void setBlogpost_coulda_shoulda_album_art_1(String blogpost_coulda_shoulda_album_art_1) {
        this.blogpost_coulda_shoulda_album_art_1 = blogpost_coulda_shoulda_album_art_1;
    }

    public String getBlogpost_coulda_shoulda_album_art_2() {
        return blogpost_coulda_shoulda_album_art_2;
    }

    public void setBlogpost_coulda_shoulda_album_art_2(String blogpost_coulda_shoulda_album_art_2) {
        this.blogpost_coulda_shoulda_album_art_2 = blogpost_coulda_shoulda_album_art_2;
    }

    public CouldaShouldaDetails getBlogpost_coulda_shoulda_1_details() {
        return blogpost_coulda_shoulda_1_details;
    }

    public void setBlogpost_coulda_shoulda_1_details(CouldaShouldaDetails blogpost_coulda_shoulda_1_details) {
        this.blogpost_coulda_shoulda_1_details = blogpost_coulda_shoulda_1_details;
    }

    public CouldaShouldaDetails getBlogpost_coulda_shoulda_2_details() {
        return blogpost_coulda_shoulda_2_details;
    }

    public void setBlogpost_coulda_shoulda_2_details(CouldaShouldaDetails blogpost_coulda_shoulda_2_details) {
        this.blogpost_coulda_shoulda_2_details = blogpost_coulda_shoulda_2_details;
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
