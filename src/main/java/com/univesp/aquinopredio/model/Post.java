package com.univesp.aquinopredio.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "tb_posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O atributo título é obrigatório")
    @Size(min = 5, max = 150, message = "O atributo título deve conter no mínimo 5 e no máximo 150 caracteres. ")
    private String postTitle;

    @Schema(example = "Aviso ou Evento")
    @NotBlank(message = "O atributo tipo é obrigatório")
    private String postType;

    @Size(min = 5, max = 500, message = "O atributo descrição deve conter no mínimo 5 e no máximo 500 caracteres. ")
    private String description;

    @UpdateTimestamp
    private LocalDate postDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDate postDate) {
        this.postDate = postDate;
    }
}
