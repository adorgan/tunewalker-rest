package com.tunewalker.rest.controller;

import com.tunewalker.rest.dto.BlogpostDTO;
import com.tunewalker.rest.service.BlogpostService;
import com.tunewalker.rest.util.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogpost")
public class BlogpostRestController {

    @Autowired
    private BlogpostService blogpostService;
    @GetMapping(value = "/list")
    public ResponseEntity<List<BlogpostDTO>> getAllBlogposts() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ObjectMapperUtils.mapAll(blogpostService.findAll(), BlogpostDTO.class));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BlogpostDTO> getBlogpostById(@PathVariable("id") String id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ObjectMapperUtils.map(blogpostService.findByBlogpostId(id), BlogpostDTO.class));
    }
}
