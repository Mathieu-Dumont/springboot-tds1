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

@SessionAttributes("categories")
@Controller
public class itemsController {

    private Categorie getCategorieByName(String nom, List<Categorie> categories) {
	int index = categories.indexOf(new Categorie(nom));
	return categories.get(index);
    }

    @ModelAttribute("categories")
    public List<Categorie> getcategorie() {
	return Arrays.asList(new Categorie("Famille"), new Categorie("Amis"));
    }

    @GetMapping("/")
    public String categorieAction() {
	return "index";
    }

    @GetMapping("test/add/{categorie}")
    public RedirectView add(@SessionAttribute List<Categorie> categories, RedirectAttributes attrs,
	    @PathVariable String categorie) {
	Element elm = new Element("bop");
	Categorie cat = getCategorieByName(categorie, categories);
	if (cat.addItem(elm)) {
	    attrs.addFlashAttribute("msg", CssMessage.SucessMessage("Element" + elm + "ajouté"));
	} else {
	    attrs.addFlashAttribute("msg", CssMessage.ErrorMessage("Element" + elm + "existant"));
	}
	return new RedirectView("/categorie");
    }

    @PostMapping("/categorie/new")
    @ResponseBody
    public RedirectView categorieAdd(@RequestParam String nom, @SessionAttribute List<Element> categorie,
	    RedirectAttributes attrs) {
	Element elm = new Element("bop");
	Categorie test = new Categorie("test");
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