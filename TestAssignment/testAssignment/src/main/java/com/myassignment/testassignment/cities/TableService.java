package com.myassignment.testassignment.cities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class TableService {

    @Autowired private RepositoryCity repo;

    public List<CityTable> listAll(){
        return (List<CityTable>) repo.findAll();
    }


    public CityTable get(Integer ID) throws UserNotFoundException {
        Optional<CityTable> result = repo.findById(ID);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("Could not find any users with ID: " + ID);
    }


    public void save(CityTable location) {
        repo.save(location);


    }

    public void delete(Integer ID) throws UserNotFoundException {
        Long count = repo.countByID(ID);
        if(count == 0){

            throw new UserNotFoundException("Could not find any users with ID: " + ID);
        }
        repo.deleteById(ID);
    }
}
