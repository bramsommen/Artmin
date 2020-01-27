package artmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/artmin")
public class UserController {

    @RequestMapping(value = {"", "/login"}, method = RequestMethod.GET)
    public String loginUser(ModelMap model) {

        // verzamelen gegevens voor het login venster
        return "login"; // JSP Pagina pointer
    }

    @RequestMapping(value = {"/registratie"}, method = RequestMethod.GET)
    public String addUser(ModelMap model) {

        // verzamelen gegevens voor het login venster
        return "registratie"; // JSP Pagina pointer
    }
}
