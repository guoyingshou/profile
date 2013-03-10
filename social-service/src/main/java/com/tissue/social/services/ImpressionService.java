package com.tissue.social.services;

import com.tissue.core.command.ImpressionCommand;
import com.tissue.core.social.Account;
import com.tissue.core.social.User;
import com.tissue.core.social.Impression;
import com.tissue.core.social.dao.ImpressionDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Component
public class ImpressionService {

    @Autowired
    private ImpressionDao impressionDao;

    public void addImpression(ImpressionCommand command) {
        impressionDao.addImpression(command);
    }

    public List<Impression> getImpressions(String userId) {
        return impressionDao.getImpressions(userId);
    }

}
