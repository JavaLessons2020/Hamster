package org.itstep.controller;


import org.itstep.model.Hamster;
import org.itstep.model.Owner;
import org.itstep.service.HamsterService;
import org.itstep.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDateTime;

@Controller
public class ShopController {




private final HamsterService hamsterService;

private final OwnerService ownerService;

    public ShopController(HamsterService hamsterService, OwnerService ownerService) {
        this.hamsterService = hamsterService;
        this.ownerService = ownerService;
    }

    @GetMapping("")
    public String show(Model model) throws SQLException, ClassNotFoundException {
        model.addAttribute("hamsters", hamsterService.getAll());
        return "show";
    }

    @GetMapping(value = "hamsters")
    public String showHamsterByName(@RequestParam (value = "search", required = false) String name, Model model) {
        model.addAttribute("getHamster", hamsterService.findByName(name));
        return "hamster";
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
        model.addAttribute("owners",ownerService.getAll());
        return "addHamster";
    }


    @PostMapping("/hamster/done")
    public String postAdd(@RequestParam("owner_name") String name, @ModelAttribute Hamster hamster)  { //, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")LocalDateTime dateTime
        hamster.setDateTime(LocalDateTime.now().minusNanos(1));
        System.out.println("Owner name = " + name);
        hamster.setOwner(ownerService.geyByName(name));
        hamsterService.save(hamster);
        return "redirect:/";
    }

}
