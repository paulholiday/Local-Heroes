///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.lbi.localheroes.controller;
//
//import com.lbi.localheroes.service.HeroService;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.AbstractController;
//
///**
// *
// * @author holidayp
// */
//@Controller
//public class ViewResultsController{
//    
//    public HeroService heroService = new HeroService();
//    
//    @RequestMapping(method=RequestMethod.GET, value="/results")
//    protected ModelAndView handleRequestInternal(){
//        ModelAndView model = new ModelAndView("results");
//        return model;
//    }
//    
//    @RequestMapping(method=RequestMethod.GET, value="/resultsDefined")
//    public String defineResults(Model model){
//        //ModelAndView model = new ModelAndView("results");
//        //model.addAttribute("refinedHeroes", heroService.getHeroesByCategoryAndTags(tags, category));
//        model.addAttribute("name", "Paul");
//        return "results";
//    }
//    
//}
