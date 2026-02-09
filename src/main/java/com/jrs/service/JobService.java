package com.jrs.service;

import com.jrs.model.JobPosting;
import com.jrs.patterns.LoggerSingleton;
import com.jrs.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public JobPosting createJob(String title, String desc, String loc, double salary) {
        LoggerSingleton.getInstance().log("Creating a new job posting: " + title);

        JobPosting newJob = new JobPosting.Builder()
                .setTitle(title)
                .setDescription(desc)
                .setLocation(loc)
                .setSalary(salary)
                .build();

        return jobRepository.save(newJob);
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

        return jobRepository.save(existingJob);
    }

    public List<JobPosting> getAllJobs() {
        return jobRepository.findAll();
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}