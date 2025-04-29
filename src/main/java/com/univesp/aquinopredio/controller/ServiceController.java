package com.univesp.aquinopredio.controller;

import com.univesp.aquinopredio.model.Service;
import com.univesp.aquinopredio.repository.ServiceRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/services")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ServiceController {
    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping
    public ResponseEntity<List<Service>> getAll() {
        return ResponseEntity.ok(serviceRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Service> getById(@PathVariable Long id) {
        return serviceRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Service> post(@Valid @RequestBody Service service) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(serviceRepository.save(service));
    }

    @PutMapping
    public ResponseEntity<Service> put(@Valid @RequestBody Service service) {
        return serviceRepository.findById(service.getId())
                .map(response -> ResponseEntity.status(HttpStatus.OK)
                        .body(serviceRepository.save(service)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Service> service = serviceRepository.findById(id);

        if (service.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        serviceRepository.deleteById(id);
    }
}
