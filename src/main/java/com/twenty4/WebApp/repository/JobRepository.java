package com.twenty4.WebApp.repository;

import com.twenty4.WebApp.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Integer> {
    List<Job> findByJobName(String jobName);

    List<Job> findByJobNumber(String jobNumber);

    Optional<Job> findById(Integer id);
}
