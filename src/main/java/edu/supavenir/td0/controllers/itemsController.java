package edu.supavenir.td0.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import edu.supavenir.td0.models.Categorie;
import edu.supavenir.td0.models.Element;
import edu.supavenir.td0.technics.CssMessage;

@SessionAttributes("categorie")
@Controller
public class itemsController {

	@ModelAttribute("categorie")
	public List<Categorie> getcategorie() {
		return Arrays.asList(new Categorie("Famille"), new Categorie("Amis"));
	}

	@GetMapping("/categorie")
	public String categorieAction() {
		return "index";
	}

	@GetMapping("test/add")
	public RedirectView add(@SessionAttribute List<Categorie> categories, RedirectAttributes attrs,
			@PathVariable String categorie) {
		Element elm = new Element();
		elm.setNom("bop");
		Categorie ctg = new Categorie(categorie);
		if (!categories.contains(ctg)) {
			int index = categories.indexOf(ctg);
			categories.get(index);
			ctg.AddItems(elm);
			attrs.addFlashAttribute("msg", CssMessage.SucessMessage("Element <b>" + elm + "</b> ajouté"));
		} else {
			attrs.addFlashAttribute("msg", CssMessage.ErrorMessage("Cet élément existe déjà !"));
		}
		return new RedirectView("/categorie");
	}

	// @PostMapping(path = "/categorie/addNew")
	// public RedirectView add(Element elm, @SessionAttribute List<Element>
	// categorie) {
	// if (!categorie.contains(elm)) {
	// categorie.add(elm);
	// }
	// return new RedirectView("/");
	// }

	@PostMapping("/categorie/new")
	@ResponseBody
	public RedirectView categorieAdd(@RequestParam String nom, @SessionAttribute List<Element> categorie,
			RedirectAttributes attrs) {
		Element elm = new Element();
		elm.setNom(nom);
		if (!categorie.contains(elm)) {
			categorie.add(elm);
			attrs.addFlashAttribute("msg", CssMessage.SucessMessage("Element <b>" + elm + "</b> ajouté"));
		} else {
			attrs.addFlashAttribute("msg", CssMessage.ErrorMessage("Cet élément existe déjà !"));
		}
		categorie.add(elm);
		return new RedirectView("/categorie");
	}

	@GetMapping("/categorie/new")
	public String categorieNew() {
		return "formAddItem";
	}

	@GetMapping("/categorie/inc{nom}")
	public RedirectView Inc(@SessionAttribute List<Element> categorie, String nom) {
		Element element = new Element(nom);
		int index = categorie.indexOf(element);
		categorie.get(index).inc();
		return new RedirectView("/categorie");
	}

	@GetMapping("/categorie/dec{nom}")
	public RedirectView Dec(@SessionAttribute List<Element> categorie, String nom) {
		Element element = new Element(nom);
		int index = categorie.indexOf(element);
		categorie.get(index).dec();
		return new RedirectView("/categorie");
	}

}