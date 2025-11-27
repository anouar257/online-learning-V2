package com.example.inscriptionservice.client;

import com.example.inscriptionservice.model.Cours;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "cours-service")
public interface CoursClient {

    @GetMapping("/cours")
    PagedModel<Cours> findAll();
}
