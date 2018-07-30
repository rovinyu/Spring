package com.rovin.mongo.repository;

import com.rovin.mongo.domain.File;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileRepository extends MongoRepository<File, String> {
}
