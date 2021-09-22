package edu.supavenir.td0.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;

import edu.supavenir.td0.models.Element;

@SessionAttributes("items")
@Controller
public class itemsController {

	@ModelAttribute("items")
	public List<Element> getItems() {
		return new ArrayList<>();
	}

	@GetMapping("/items")
	public String itemsAction() {
		return "index";
	}

	@GetMapping("test/add")
	public RedirectView add(@SessionAttribute List<Element> items) {
		Element elm = new Element();
		elm.setNom("bop");
		if (!items.contains(elm)) {
			items.add(elm);
		}
		items.add(elm);
		return new RedirectView("/items");
	}

	@PostMapping("/items/new")
	@ResponseBody
	public RedirectView itemsAdd(@RequestParam String nom, @SessionAttribute List<Element> items) {
		Element Mathieu = new Element();
		Mathieu.setNom(nom);
		items.add(Mathieu);
		return new RedirectView("/items");
	}

	@GetMapping("/items/new")
	public String itemsNew() {
		return "formAddItem";
	}

	@GetMapping("/items/inc{nom}")
	public RedirectView Inc(@SessionAttribute List<Element> items, String nom) {
		Element element = new Element(nom);
		int index = items.indexOf(element);
		items.get(index).inc();
		return new RedirectView("/items");
	}

	@GetMapping("/items/dec{nom}")
	public RedirectView Dec(@SessionAttribute List<Element> items, String nom) {
		Element element = new Element(nom);
		int index = items.indexOf(element);
		items.get(index).dec();
		return new RedirectView("/items");
	}

}