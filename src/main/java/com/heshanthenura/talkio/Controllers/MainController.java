package com.heshanthenura.talkio.Controllers;

import com.heshanthenura.talkio.Services.IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.logging.Logger;


@Controller
public class MainController {

    Logger infoLogger = Logger.getLogger("Information");

    @Autowired
    IDGenerator idGenerator = new IDGenerator();

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String Index(Model model){
        return "register";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model, RedirectAttributes redirectAttributes) {
        if (!model.containsAttribute("ID")) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please enter a valid name first.");
            return "redirect:/"; // Redirect to the registration page
        }
        return "index";
    }

}
