package com.myassignment.testassignment.cities;


import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TableController {

    @Autowired private TableService service;



    @GetMapping("/cities")
    public String showCityTableList(Model model){
        List<CityTable> listCities = service.listAll();
        model.addAttribute("listCities", listCities);

        return"cities";
    }

    @GetMapping("/cities/new")
    public String createNewLocation(Model model){
        /*List<CityTable> listDropdown = service.listAll();
        model.addAttribute("dropdown", listDropdown);*/
        model.addAttribute("location", new CityTable());
        model.addAttribute("pageTitle", "Create New");
        return"location_form";
    }

    @PostMapping("/cities/save")
    public String saveLocation(CityTable location, RedirectAttributes ra){
       service.save(location);
       ra.addFlashAttribute("message", "New location has been created successfully");
       return "redirect:/cities";
    }

    @GetMapping("/cities/edit/{ID}")
    public String showEditForm(@PathVariable("ID") Integer ID, Model model, RedirectAttributes ra){
        try {
           CityTable cityTable = service.get(ID);
           model.addAttribute("location", cityTable);
           model.addAttribute("pageTitle", "Edit Location (ID: " + ID + ")");

           return "location_form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/cities";
        }
    }


    @GetMapping("/cities/delete/{ID}")
    public String deleteLocation(@PathVariable("ID") Integer ID, RedirectAttributes ra){
        try {
            service.delete(ID);
            ra.addFlashAttribute("message", "User " + " ID:" + ID + " was deleted successfully");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());

        }
        return "redirect:/cities";
    }


}
