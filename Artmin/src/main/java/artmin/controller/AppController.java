package artmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import artmin.service.ArtistService;
import artmin.service.UserService;

@Controller
@RequestMapping("/")
public class AppController {

    @Autowired
    UserService userService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    ArtistController artistController;

    @Autowired
    ArtistService artistService;
    
    @Autowired
    EventController eventController;

    /*
     * This method will list all existing users.
     */
    // *FROM* Route aangeven, deze methode wordt aangesporken als er geen route wordt eengegeven "/" of als de de route "/list" wordt gegeven in de URL
    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String start(ModelMap model) {
 
        return "index"; // JSP Pagina pointer
    }
}
