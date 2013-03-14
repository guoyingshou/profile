package com.tissue.social.services;

import com.tissue.core.command.CommentCommand;
import com.tissue.core.social.About;
import com.tissue.core.social.dao.AboutDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Component
public class AboutService {

    @Autowired
    private AboutDao aboutDao;

    public String addAbout(CommentCommand command) {
        return aboutDao.addAbout(command);
    }

    public List<About> getAbouts() {
        return aboutDao.getAbouts();
    }

}
