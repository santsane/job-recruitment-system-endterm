package com.jrs.service;

import com.jrs.model.JobPosting;
import com.jrs.patterns.LoggerSingleton;
import com.jrs.repository.JobRepository;
import com.jrs.patterns.JobCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    private final JobCache jobCache = JobCache.getInstance();

    public JobPosting createJob(String title, String desc, String loc, double salary) {
        LoggerSingleton.getInstance().log("Creating a new job posting: " + title);

        JobPosting newJob = new JobPosting.Builder()
                .setTitle(title)
                .setDescription(desc)
                .setLocation(loc)
                .setSalary(salary)
                .build();

        JobPosting savedJob = jobRepository.save(newJob);

        jobCache.clearCache();

        return savedJob;
    }

    public JobPosting updateJob(Long id, String title, String desc, String loc, double salary) {
        LoggerSingleton.getInstance().log("Updating job posting ID: " + id);

        JobPosting existingJob = jobRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Job not found with id: " + id));

        existingJob = new JobPosting.Builder()
                .setTitle(title)
                .setDescription(desc)
                .setLocation(loc)
                .setSalary(salary)
                .build();

        existingJob.setId(id);
        JobPosting updatedJob = jobRepository.save(existingJob);

        jobCache.clearCache();

        return updatedJob;
    }

    public List<JobPosting> getAllJobs() {
        if (jobCache.getJobs() != null) {
            LoggerSingleton.getInstance().log("Getting jobs from the Cache");
            return jobCache.getJobs();
        }

        LoggerSingleton.getInstance().log("Getting jobs from the Database");
        List<JobPosting> jobs = jobRepository.findAll();
        jobCache.setJobs(jobs);

        return jobs;
    }

    public void deleteJob(Long id) {
        LoggerSingleton.getInstance().log("Deleting job ID: " + id);
        jobRepository.deleteById(id);

        jobCache.clearCache();
    }
}