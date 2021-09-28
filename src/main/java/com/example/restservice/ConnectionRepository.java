package com.example.restservice;

import org.springframework.data.repository.CrudRepository;

public interface ConnectionRepository extends CrudRepository<DBConnection, Integer> {

}
