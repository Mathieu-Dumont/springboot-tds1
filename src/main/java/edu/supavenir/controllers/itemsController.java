package edu.supavenir.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;

import edu.supavenir.td0.models.element;

@SessionAttributes("items")
	@Controller
	public class itemsController {
		@ModelAttribute("items") 
	    public List<element> getItems(){
	        return new ArrayList<>();
	    }
	    
	    @GetMapping("/items")
	    public String itemsAction() {
	    	return "items";
	    }
	    
	    @GetMapping("test/add")
	    public RedirectView add(@SessionAttribute List<element> items) {
	    	element elm = new element();
	    	elm.setNom("bop");
	    	getItems().add(elm);
			return new RedirectView("td0/items");
	    }
	}