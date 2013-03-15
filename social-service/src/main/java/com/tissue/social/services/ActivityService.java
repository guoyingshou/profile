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

    /**
    public List<Activity> getActivities(int num) {
        return activityDao.getActivities(num);
    }

    public List<Activity> getWatchedActivities(String userId, int count) {
        return activityDao.getWatchedActivities(userId, count);
    }

    public List<Activity> getSelfActivities(String userId, int count) {
        return activityDao.getSelfActivities(userId, count);
    }
    */

    public List<Activity> getActivitiesForNewUser(int num) {
        return activityDao.getActivitiesForNewUser(num);
    }
}
