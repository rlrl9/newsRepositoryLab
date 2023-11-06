package com.example.springnews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springnews.model.News;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Integer> {


    List<News> findByWriter(String name);

    List<News> findByContentContains(String keyword);
}
