package com.univesp.aquinopredio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O atributo nome é obrigatório")
    @Size(min = 5, max = 150, message = "O atributo nome deve conter no mínimo 5 e no máximo 150 caracteres. ")
    private String serviceName;

    @Size(min = 5, max = 500, message = "O atributo descrição deve conter no mínimo 5 e no máximo 500 caracteres. ")
    private String description;

    @NotBlank(message = "O atributo nome de quem oferece o serviço é obrigatório")
    @Size(min = 5, max = 150, message = "O atributo nome de quem oferece o serviço deve conter no mínimo 5 e no máximo 150 caracteres. ")
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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
