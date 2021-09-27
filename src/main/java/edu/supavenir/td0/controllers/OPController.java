package edu.supavenir.td0.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import edu.supavenir.td0.technics.CssMessage;

@SessionAttributes("end")
@Controller
public class OPController {

    @GetMapping("/end")
    public RedirectView endAction(HttpSession session, RedirectAttributes attrs) {
	session.removeAttribute("items");
	session.invalidate();
	attrs.addFlashAttribute("msg", CssMessage.SucessMessage("Votre session a été fermée correctement !"));
	return new RedirectView("/");
    }
}
