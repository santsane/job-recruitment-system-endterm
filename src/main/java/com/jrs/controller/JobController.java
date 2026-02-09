package com.jrs.controller;

import com.jrs.model.JobPosting;
import com.jrs.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping
    public List<JobPosting> getAllJobs() {
        return jobService.getAllJobs();
    }

    @PostMapping
    public JobPosting createJob(@RequestBody Map<String, Object> body) {
        String title = (String) body.get("title");
        String description = (String) body.get("description");
        String location = (String) body.get("location");
        double salary = Double.parseDouble(body.get("salary").toString());

        return jobService.createJob(title, description, location, salary);
    }

    @PutMapping("/{id}")
    public JobPosting updateJob(@PathVariable Long id, @RequestBody Map<String, Object> body) {
        String title = (String) body.get("title");
        String description = (String) body.get("description");
        String location = (String) body.get("location");
        double salary = Double.parseDouble(body.get("salary").toString());

        return jobService.updateJob(id, title, description, location, salary);
    }

    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
    }
}