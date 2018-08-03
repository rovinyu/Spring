package com.rovin.blog.repository;

import com.rovin.blog.domain.EsBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsBlogRepository extends ElasticsearchRepository<EsBlog, String> {

    Page<EsBlog> findByTitleOrSummaryOrContent(String title, String summary,
                                               String content, Pageable pageable);

    EsBlog findByBlogId(Long blogId);
}
