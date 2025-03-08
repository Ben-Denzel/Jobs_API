package com.backend.vuejobs.Services;

import com.backend.vuejobs.Models.Company;
import com.backend.vuejobs.Models.Job;
import com.backend.vuejobs.Repositories.JobRepository;
import com.backend.vuejobs.Repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    public Job createJob(Job job) {
        Company company = job.getCompany();
        if(company != null){
            Optional<Company> existingCompany = companyRepository.findByName(company.getName());
            if (existingCompany.isEmpty()){
                company = companyRepository.save(company);
            } else {
                company = existingCompany.get();
            }
            job.setCompany(company);
        }
        return jobRepository.save(job);
    }

    public Job updateJob(Long id, Job jobDetails) {
        Job job = jobRepository.findById(id).orElse(null);
        if (job != null) {
            job.setTitle(jobDetails.getTitle());
            job.setType(jobDetails.getType());
            job.setDescription(jobDetails.getDescription());
            job.setLocation(jobDetails.getLocation());
            job.setSalary(jobDetails.getSalary());
            job.setCompany(jobDetails.getCompany());
            return jobRepository.save(job);
        }
        return null;
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}
