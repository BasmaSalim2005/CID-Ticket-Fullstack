package com.basma.Demo1.feature;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeatureRepository extends JpaRepository<Feature,Long> {
    List<Feature> findByAppId(Long id);
}
