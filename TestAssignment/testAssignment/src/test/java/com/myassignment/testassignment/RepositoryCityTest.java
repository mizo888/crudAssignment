package com.myassignment.testassignment;

import com.myassignment.testassignment.cities.CityTable;
import com.myassignment.testassignment.cities.RepositoryCity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class RepositoryCityTest {
    @Autowired private RepositoryCity repo;


    /// ADD NEW
    @Test
    public void testAddNew(){

        CityTable cityTable = new CityTable();
        cityTable.setCity("Tuzla");
        cityTable.setName("Mirso");
        cityTable.setAddress("2nd Street");
        cityTable.setLatitude(12.3343);
        cityTable.setLongitude(4.4686);


        CityTable savedCity = repo.save(cityTable);

        Assertions.assertThat(savedCity).isNotNull();
        Assertions.assertThat(savedCity.getID()).isGreaterThan(0);


    }

   /// GET ALL
    @Test
    public void testGetAll(){

        Iterable<CityTable> cityTables = repo.findAll();
        for (CityTable city : cityTables){
            System.out.println(city);
        }
    }

    /// UPDATE VALUES
    @Test
    public void updateValues(){
        Integer userId = 1;
        Optional<CityTable> optionalCityTable = repo.findById(userId);
        CityTable cityTable = optionalCityTable.get();
        cityTable.setCity("Zivinice");
        cityTable.setName("Nusreta");
        cityTable.setAddress("ulica boraca");
        repo.save(cityTable);

        CityTable updateCity = repo.findById(userId).get();
        Assertions.assertThat(updateCity.getCity()).isEqualTo("Zivinice");
        Assertions.assertThat(updateCity.getName()).isEqualTo("Nusreta");
        Assertions.assertThat(updateCity.getAddress()).isEqualTo("ulica boraca");

    }


    @Test

    /// GET SINGLE
    public void getSingle(){
        Integer userId = 2;
        Optional<CityTable> optionalCityTable = repo.findById(userId);
        CityTable cityTable = optionalCityTable.get();

        Assertions.assertThat(optionalCityTable).isPresent();
        System.out.println(optionalCityTable.get());
    }

    /// DELETE
    @Test
    public void deleteTest(){
        Integer userId =5;
        repo.deleteById(5);

        Optional<CityTable> optionalCityTable = repo.findById(userId);
        Assertions.assertThat(optionalCityTable).isNotPresent();
    }

}
