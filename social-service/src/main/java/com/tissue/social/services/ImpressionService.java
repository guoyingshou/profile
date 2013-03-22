package com.tissue.social.services;

import com.tissue.social.Impression;
import com.tissue.social.dao.ImpressionDao;
import com.tissue.social.command.ImpressionCommand;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ImpressionService {

    @Autowired
    private ImpressionDao impressionDao;
 
    public void createImpression(ImpressionCommand command) {
        impressionDao.create(command);
    }

    public List<Impression> getImpressions(String userId) {
        return impressionDao.getImpressions(userId);
    }
}
