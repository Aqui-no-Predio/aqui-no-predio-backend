package com.univesp.aquinopredio.controller;

import com.univesp.aquinopredio.model.Post;
import com.univesp.aquinopredio.repository.PostRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public ResponseEntity<List<Post>> getAll() {
        return ResponseEntity.ok(postRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getById(@PathVariable Long id) {
        return postRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Post> post(@Valid @RequestBody Post post) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postRepository.save(post));
    }

    @PutMapping
    public ResponseEntity<Post> put(@Valid @RequestBody Post post) {
        return postRepository.findById(post.getId())
                .map(response -> ResponseEntity.status(HttpStatus.OK)
                        .body(postRepository.save(post)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Optional<Post> post = postRepository.findById(id);

        if(post.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        postRepository.deleteById(id);
    }

}
