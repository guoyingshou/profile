package com.tissue.social.services;

import com.tissue.core.About;
import com.tissue.core.dao.AboutDao;
import com.tissue.core.command.ContentCommand;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Component
public class AboutService {

    @Autowired
    private AboutDao aboutDao;

    public String createAbout(ContentCommand command) {
        return aboutDao.create(command);
    }

    public List<About> getAbouts() {
        return aboutDao.getAbouts();
    }

}
