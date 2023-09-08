package com.jyoshna.controller;

import com.jyoshna.dao.FlightDao;
import com.jyoshna.entity.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class flightController {
    @Autowired
    FlightDao flightDao;
    @GetMapping("Search")
    public String searchFlight(@RequestParam("source")String source, @RequestParam("destination")String destination, Model model) throws ClassNotFoundException {
        ArrayList<Flight> flights = flightDao.searchFlight(source, destination);
        System.out.println("jeee");
        model.addAttribute("flights", flights);
        return "FlightBooking";
    }

}
