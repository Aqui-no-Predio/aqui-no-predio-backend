package com.univesp.aquinopredio.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O atributo nome é obrigatório")
    @Size(max = 150, message = "O atributo nome deve conter no máximo 150 caracteres. ")
    private String petName;

    @Schema(example = "Cachorro, gato, roedor, réptil, outro")
    @NotBlank(message = "O atributo tipo é obrigatório")
    private String petType;

    @Size(min = 5, max = 500, message = "O atributo características deve conter no mínimo 5 e no máximo 500 caracteres. ")
    private String characteristics;

    @NotBlank(message = "O atributo nome do tutor é obrigatório")
    @Size(max = 150, message = "O atributo nome do tutor deve conter no máximo 150 caracteres. ")
    private String person;

    @NotBlank(message = "O atributo apartamento é obrigatório")
    @Size(max = 150, message = "O atributo a deve conter no máximo 150 caracteres. ")
    private String apartment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }


}
