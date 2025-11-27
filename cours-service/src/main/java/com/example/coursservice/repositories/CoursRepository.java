package com.example.coursservice.repositories;

import com.example.coursservice.entities.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "cours", collectionResourceRel = "cours")
public interface CoursRepository extends JpaRepository<Cours, Long> {
}
