package com.twenty4.WebApp.controller;

import com.twenty4.WebApp.entity.Job;
import com.twenty4.WebApp.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobRepository jobRepository;

    @GetMapping
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable int id) {
        Optional<Job> job = jobRepository.findById(id);
        return job.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        Job createdJob = jobRepository.save(job);
        return new ResponseEntity<>(createdJob, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable int id, @RequestBody Job job) {
        Optional<Job> existingJob = jobRepository.findById(id);
        if (existingJob.isPresent()) {
            job.setJobId(id);
            Job updatedJob = jobRepository.save(job);
            return new ResponseEntity<>(updatedJob, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable int id) {
        Optional<Job> existingJob = jobRepository.findById(id);
        if (existingJob.isPresent()) {
            jobRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}