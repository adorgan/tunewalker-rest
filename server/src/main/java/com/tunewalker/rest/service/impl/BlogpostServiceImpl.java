package com.tunewalker.rest.service.impl;

import com.tunewalker.rest.model.Blogpost;
import com.tunewalker.rest.repository.BlogpostRepository;
import com.tunewalker.rest.service.BlogpostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BlogpostServiceImpl implements BlogpostService {

    @Autowired
    BlogpostRepository blogpostRepository;
    @Override
    public List<Blogpost> findAll() {
        return blogpostRepository.findAll();
    }

//    @Override
//    public List<Blogpost> findAllBlogposts() {
//        return blogpostRepository.findAllBlogposts();
//    }

    @Override
    public Blogpost findByBlogpostId(String id) {
        return blogpostRepository.findBlogpostById(id);
    }

    @Override
    public void save(Blogpost blogpost) {
        blogpostRepository.save(blogpost);
    }
}
