package com.jrs.patterns;

import com.jrs.model.JobPosting;
import java.util.List;

public class JobCache {
    private static JobCache instance;

    private List<JobPosting> cachedJobs;

    private JobCache() {
        this.cachedJobs = null;
    }

    public static synchronized JobCache getInstance() {
        if (instance == null) {
            instance = new JobCache();
        }
        return instance;
    }

    public List<JobPosting> getJobs() {
        return cachedJobs;
    }

    public void setJobs(List<JobPosting> jobs) {
        this.cachedJobs = jobs;
    }

    public void clearCache() {
        this.cachedJobs = null;
        System.out.println("Cache cleared.");
    }
}