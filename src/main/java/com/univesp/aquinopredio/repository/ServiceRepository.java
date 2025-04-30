package com.univesp.aquinopredio.repository;

import com.univesp.aquinopredio.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}
