package com.jyoshna.controller;

import com.jyoshna.dao.FlightDao;
import com.jyoshna.entity.Flight;
import com.jyoshna.entity.Passengers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FlightBookingController {
    @Autowired
    FlightDao flightDao;


    @PostMapping("/bookflight")
    public String book(@RequestParam("flightId") String flightId, @RequestParam("passengerId") String passengerId, @RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName, @RequestParam("mobile") int mobile, @RequestParam("email") String email, @RequestParam("traveldate") String travelDate, Model model) {
        Passengers p = new Passengers(passengerId, firstName, lastName, mobile, email);

        if (flightDao.bookFlight(flightId, p, travelDate)) {
            Flight f = flightDao.getFlightById(flightId);
            model.addAttribute("FlightObj", f);
            model.addAttribute("PassengerObj", p);
            model.addAttribute("message", "Successfull;");
            return "Display";
        }


        model.addAttribute("message", "Not Successfull");
        return "Display";
    }
}
