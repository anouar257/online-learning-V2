package com.example.inscriptionservice.web;

import com.example.inscriptionservice.client.CoursClient;
import com.example.inscriptionservice.model.Cours;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InscriptionController {

    private final CoursClient coursClient;

    public InscriptionController(CoursClient coursClient) {
        this.coursClient = coursClient;
    }

    @GetMapping("/cours-disponibles")
    public PagedModel<Cours> getCours() {
        return coursClient.findAll();
    }
}
