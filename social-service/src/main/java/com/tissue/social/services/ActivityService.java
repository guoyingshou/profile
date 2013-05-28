package com.tissue.social.services;

import com.tissue.commons.util.SecurityUtil;
import com.tissue.social.Activity;
import com.tissue.social.dao.ActivityDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Component
public class ActivityService {

    @Autowired
    private ActivityDao activityDao;

    public List<Activity> getActivities(String excludingAccountId, int num) {
        return activityDao.getActivities(excludingAccountId, num);
    }
 
    public List<Activity> getActivities(int num) {
        return activityDao.getActivities(num);
    }

    public List<Activity> getViewerWatchedActivities(int count) {
        return activityDao.getWatchedActivities(SecurityUtil.getViewerAccountId(), count);
    }

    public List<Activity> getActivitiesByUser(String userId, int count) {
        return activityDao.getActivitiesByUser(userId, count);
    }

}
