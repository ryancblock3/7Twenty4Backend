package com.twenty4.WebApp.service;

import com.twenty4.WebApp.entity.Job;
import com.twenty4.WebApp.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job getJobById(Integer id) {
        return jobRepository.findById(id).orElse(null);
    }

    public List<Job> getJobByName(String jobName) {
        return jobRepository.findByJobName(jobName);
    }

    public List<Job> getJobByNumber(String jobNumber) {
        return jobRepository.findByJobNumber(jobNumber);
    }
}
