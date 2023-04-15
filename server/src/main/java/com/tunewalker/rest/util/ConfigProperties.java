package com.tunewalker.rest.util;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class ConfigProperties {

    String s3BucketName;

    public String getName() {
        return s3BucketName;
    }

    public void setName(String name) {
        this.s3BucketName = name;
    }
}
