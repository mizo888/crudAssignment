package com.myassignment.testassignment.cities;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryCity extends CrudRepository<CityTable, Integer> {

    long countByID(Integer ID);
}