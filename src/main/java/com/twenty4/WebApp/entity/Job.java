package com.twenty4.WebApp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jobs_job_id_seq")
    @SequenceGenerator(name = "jobs_job_id_seq", sequenceName = "jobs_job_id_seq", allocationSize = 1)
    @Column(name = "job_id")
    private int jobId;

    @Column(name = "job_name")
    private String jobName;

    @Column(name = "job_number")
    private String jobNumber;

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }
}
