package com.heshanthenura.talkio.Controllers;

import com.heshanthenura.talkio.Database.Models.User;
import com.heshanthenura.talkio.Database.Repositories.UserRepository;
import com.heshanthenura.talkio.Services.IDGenerator;
import com.heshanthenura.talkio.Services.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.logging.Logger;

@Controller
public class PostController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    IDGenerator idGenerator = new IDGenerator();

    @Autowired
    Validator validator = new Validator();

    Logger infoLogger = Logger.getLogger("Information");
    @RequestMapping(value = "/registername",method =  RequestMethod.POST)
    public String Index(@RequestParam String name, RedirectAttributes redirectAttributes){

        infoLogger.info(name);
        infoLogger.info(String.valueOf(validator.isValidName(name)));
        String ID = idGenerator.Generate();
        infoLogger.info(ID);

        if(validator.isValidName(name)){

            User user = new User();
            user.setId(ID);
            user.setName(name);
            userRepository.save(user);
            Iterable<User> users = userRepository.findAll();
            // Iterate through the users and print their information
            for (User u : users) {
                infoLogger.info(u.toString());
            }
            redirectAttributes.addFlashAttribute("ID", ID);
            return "redirect:/home";
        }else{
            redirectAttributes.addFlashAttribute("errorMessage", "Name should  be longer than 3 characters");
            return "redirect:/";
        }

    }
}
