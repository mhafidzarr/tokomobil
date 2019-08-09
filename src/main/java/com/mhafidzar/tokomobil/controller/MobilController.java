/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mhafidzar.tokomobil.controller;

import com.mhafidzar.tokomobil.model.Mobil;
import com.mhafidzar.tokomobil.service.MobilService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author natar
 */
@Controller
@RequestMapping(value = "api")
public class MobilController {
    
    @Autowired
    private MobilService mobilService;
    
    @GetMapping("/home")
    public String home(){
        return "Dashboard";
    }
    
    @GetMapping("/mobil")
    public ModelAndView carManagement(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("mobils", mobilService.findAll());
        modelAndView.setViewName("CarManagement");
        return modelAndView;
    }
    
    @GetMapping("/mobil/add")
    public ModelAndView carAdd(){
        ModelAndView modelAndView = new ModelAndView();
        Mobil mobil = new Mobil();
        modelAndView.addObject("mobil", mobil);
        modelAndView.setViewName("CarAdd");
        return modelAndView;
    }
    
    @PostMapping("/mobil")
    public ModelAndView carAddSave(@Valid Mobil mobil){
        mobilService.carAddSave(mobil);
        return new ModelAndView("redirect:/api/mobil");
    }
    
    @GetMapping("/mobil/{id}")
    public ModelAndView carEdit(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        Mobil mobil = mobilService.getCarById(id).orElse(null);
        modelAndView.addObject("mobil", mobil);
        modelAndView.setViewName("CarEdit");
        return modelAndView;
    }
    
    @PutMapping("/mobil/{id}")
    public ModelAndView carEditSave(@PathVariable("id") int id, @Valid Mobil mobil){
        mobilService.carEditSave(mobil);
        return new ModelAndView("redirect:/api/mobil");
    }
    
    @DeleteMapping("/mobil/{id}")
    public ModelAndView carDelete(@PathVariable("id") int id){
        Mobil mobil = mobilService.getCarById(id).orElse(null);
        mobilService.carDelete(mobil);
        return new ModelAndView("redirect:/api/mobil");
    }
}
