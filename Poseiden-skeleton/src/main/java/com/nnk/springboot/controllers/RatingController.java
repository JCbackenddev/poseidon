package com.nnk.springboot.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;

@Controller
public class RatingController {
	
	private static final Logger log = LoggerFactory.getLogger(RatingController.class);
    
	@Autowired
	RatingService ratingService;

	/***
	 * 
	 * @param model
	 * @return to the page "list" with all the existing object for this entity
	 */
    @RequestMapping("/rating/list")
    public String home(Model model) {
    	
    	log.info("accessing /rating/list endpoint with home method");
    	
    	model.addAttribute("rating", ratingService.findAll());
        return "rating/list";
    }

    /***
	 * 
	 * @param rating
	 * @return send user to the "add" view to create a new entity object
	 */
    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
    	
    	log.info("accessing /rating/add endpoint with addRatingForm method");
    	
        return "rating/add";
    }

    /***
     * 
     * @param rating
     * @param result
     * @param model
     * @return send the user back to the "list" page if the rating was correct. Keeps the user on the add page if the rating did contain errors
     */
    @PostMapping("/rating/validate")
    public String validate(@Valid @ModelAttribute("rating") Rating rating, BindingResult result, Model model) {
    	
    	log.info("accessing /rating/validate endpoint with validate method");
    	
    	if (!result.hasErrors()) {
    		ratingService.saveRating(rating);
            model.addAttribute("rating", ratingService.findAll());
            return "redirect:/rating/list";
        }
    	
        return "rating/add";
    }

    /***
     * sends the user to the "update" page containing the right rating
     * @param id
     * @param model
     * @return to the endpoint to post the update
     */
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	
    	log.info("accessing /rating/update/{id} endpoint with showUpdateForm method");
    	
    	Rating rating = ratingService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
        model.addAttribute("rating", rating);
        
        return "rating/update";
    }

    /***
     * 
     * @param id
     * @param rating
     * @param result
     * @param model
     * @return send the user back to the "list" page when the rating was correct. Keeps the user on the update page if the rating did contain errors
     */
    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid @ModelAttribute("rating")Rating rating,
                             BindingResult result, Model model) {
    	
    	log.info("accessing /rating/update/{id} endpoint with updateRating method");
    	
    	if (result.hasErrors()) {
            return "rating/update";
        }

    	rating.setId(id);;
        ratingService.saveRating(rating);
        model.addAttribute("rating", ratingService.findAll());
    	
        return "redirect:/rating/list";
    }

    /***
     * 
     * @param id
     * @param model
     * @return redirect user to the "list" page after deletion of the object
     */
    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
    	
    	log.info("accessing /rating/delete/{id} endpoint with deleteRating method");
    	
    	Rating rating = ratingService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
    	ratingService.delete(rating);
        model.addAttribute("rating", ratingService.findAll());
        
        return "redirect:/rating/list";
    }
}
