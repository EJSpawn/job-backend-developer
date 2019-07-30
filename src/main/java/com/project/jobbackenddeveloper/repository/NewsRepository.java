package com.project.jobbackenddeveloper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.jobbackenddeveloper.model.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

}
