package com.tissue.social.services;

import com.tissue.core.social.Activity;
import com.tissue.core.social.dao.ActivityDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Component
public class ActivityService {

    @Autowired
    private ActivityDao activityDao;

    public List<Activity> getActivitiesForNewUser(int num) {
        return activityDao.getActivitiesForNewUser(num);
    }
}
