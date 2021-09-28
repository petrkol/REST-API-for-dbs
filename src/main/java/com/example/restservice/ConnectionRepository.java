package com.example.restservice;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface ConnectionRepository extends CrudRepository<DBConnection, Integer>{

  @Query(value = "SELECT MAX(id) FROM user", nativeQuery = true)
  public BigDecimal findMaxUser();
  
}
