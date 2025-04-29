package com.univesp.aquinopredio.controller;

import com.univesp.aquinopredio.model.Pet;
import com.univesp.aquinopredio.repository.PetRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pets")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PetController {

    @Autowired
    private PetRepository petRepository;

    @GetMapping
    public ResponseEntity<List<Pet>> getAll() {
        return ResponseEntity.ok(petRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getById(@PathVariable Long id) {
        return petRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Pet> post(@Valid @RequestBody Pet pet) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(petRepository.save(pet));
    }

    @PutMapping
    public ResponseEntity<Pet> put(@Valid @RequestBody Pet pet) {
        return petRepository.findById(pet.getId())
                .map(response -> ResponseEntity.status(HttpStatus.OK)
                        .body(petRepository.save(pet)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Optional<Pet> pet = petRepository.findById(id);

        if(pet.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        petRepository.deleteById(id);
    }
}
