package com.tunewalker.rest.service.impl;

import com.tunewalker.rest.model.About;
import com.tunewalker.rest.repository.AboutRepository;
import com.tunewalker.rest.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AboutServiceImpl implements AboutService {

    @Autowired
    private AboutRepository aboutRepository;
    @Override
    public List<About> findAll() {
        return aboutRepository.findAll();
    }
}
