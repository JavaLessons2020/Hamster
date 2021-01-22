package org.itstep.controller;


import org.itstep.model.Hamster;
import org.itstep.service.HamsterService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDateTime;

@Controller
public class ShopController {




private final HamsterService hamsterService;

    public ShopController(HamsterService hamsterService) {
        this.hamsterService = hamsterService;
    }

    @GetMapping("")
    public String show(Model model) throws SQLException, ClassNotFoundException {
        model.addAttribute("hamsters", hamsterService.getAll());

        return "show";
    }


    @GetMapping("/hamster/{id}")
    public String getProduct(@PathVariable("id") Long id, Model model) throws SQLException, ClassNotFoundException {
        model.addAttribute("getHamster", hamsterService.getByID(id));
        Hamster byID = hamsterService.getByID(id);
        System.out.println(byID);
        return "hamster";
    }

    @GetMapping("/hamster/new")
    public String addProduct(Model model) {
        model.addAttribute("hamster", new Hamster());
        model.addAttribute("default", "Default value");
        return "addHamster";
    }


    @PostMapping("/hamster/done")
    public String postAdd(@ModelAttribute Hamster hamster)  { //, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")LocalDateTime dateTime
        hamster.setDateTime(LocalDateTime.now().minusNanos(1));
        hamsterService.save(hamster);
        return "redirect:/";
    }

}
