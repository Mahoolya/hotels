package com.app.hotels.repository;

import com.app.hotels.domain.Cost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CostRepository extends JpaRepository<Cost, Long> {
}
