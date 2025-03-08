package com.backend.vuejobs.Repositories;

import com.backend.vuejobs.Models.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
