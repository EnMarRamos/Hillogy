package com.hillogy.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hillogy.library.entity.Configuration;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {

}
