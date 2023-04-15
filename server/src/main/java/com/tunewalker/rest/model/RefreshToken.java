package com.tunewalker.rest.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Ref;
import java.util.Date;

@Document(collection = "refreshTokens")
public class RefreshToken {
    @Id
    private String id;
    private String token;
    private Date createdAt;
    public RefreshToken(){};

    public RefreshToken(String id, String token, Date createdAt) {
        this.id = id;
        this.token = token;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
