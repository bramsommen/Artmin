/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmin.controller;

import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import artmin.model.Artist;
import artmin.model.User;
import artmin.service.ArtistService;
import artmin.service.UserService;

@Controller
@RequestMapping("/artists")
public class ArtistController {

    @Autowired
    ArtistService artistService;

    @Autowired
    UserService userService;

    @RequestMapping(value = {"/new"}, method = RequestMethod.GET)
    public String newArtist(ModelMap model) {
        Artist artist = new Artist();
        model.addAttribute("artist", artist);
        model.addAttribute("edit", false);
        return "artistnew"; //view r-team
    }

    @Transactional
    @RequestMapping(value = {"/new"}, method = RequestMethod.POST)
    public String saveArtist(Artist artist, BindingResult result, ModelMap model) {

        //First check if user already excists
        List<Artist> lstArtists = artistService.findAllArtists();

        for (Artist artists : lstArtists) {
            String artistOutOfList = artists.getName();
            String artistNewinList = artist.getName();
            //&& artists.getDescription() == artist.getDescription() 
            if (artistOutOfList.equals(artistNewinList)) {
                model.addAttribute("message", "Artist already excists");
                return "artistnew";
            }
        }

        //Save user to DB
        artistService.saveArtist(artist);

        //Redirect user to success page
        model.addAttribute("success", "Artist " + artist.getName() + " registered successfully");
        return this.listArtist(model); // Return overzicht events
    }

    @Transactional
    @RequestMapping(value = {"/edit-{id}-artist"}, method = RequestMethod.GET)
    public String editArtist(@PathVariable Long id, ModelMap model) {
        Artist artist = artistService.findById(id);
        Set<User> users = artistService.getAllMatchingUsers(id);
        model.addAttribute("artist", artist);
        model.addAttribute("edit", true);
        model.addAttribute("users", users);
        model.addAttribute("user", new User());
        return "artistnew";
    }

    @RequestMapping(value = {"/edit-{id}-artist"}, method = RequestMethod.POST)
    public String updateArtist(Artist artist, User user, BindingResult result, ModelMap model, @PathVariable int id) {
        artistService.updateArtist(artist);
        model.addAttribute("success", "Artist " + artist.getName() + " registered successfully");
        return this.listArtist(model); // Return overzicht events
    }

    // Deze methode wordt enkel aangesproken als je op de Add User knop duwt.
    @RequestMapping(value = {"/edit-{id}-artist"}, method = RequestMethod.POST, params = "addUser")
    public String addUserToArtist(User user, BindingResult result, ModelMap model, @PathVariable Long id) {
        Artist artist = artistService.findById(id);
        model.addAttribute("artist", artist);
        model.addAttribute("edit", true);
        
        User userFromDb = userService.findByEmail(user.getEmail());
        if (userFromDb != null && !artist.getUsers().contains(userFromDb)) {
            artist.addUser(userFromDb);
            artistService.saveOrUpdate(artist);
        }
        else {
            System.out.println("Sending email to " +  user.getEmail());
        }

        return this.listArtist(model);
    }

    // Deze methode wordt enkel aangesproken als je op de Remove User knop duwt.
    @RequestMapping(value = {"/edit-{id}-artist"}, method = RequestMethod.POST, params = "removeUser")
    public String removeUserFromArtist(ModelMap model, @RequestParam(value="removeUser") Long userId,  @PathVariable Long id) {
        Artist artist = artistService.findById(id);
        User userToDelete = userService.findById(userId);

        model.addAttribute("user", userToDelete);
        model.addAttribute("artist", artist);
        model.addAttribute("edit", true);

        artist.removeUser(userToDelete);
        userToDelete.getArtists().remove(artist);
        artistService.saveOrUpdate(artist);

        return this.listArtist(model);
    }

    @RequestMapping(value = {"/delete-{id}-artist"}, method = RequestMethod.GET)
    public String deleteArtist(@PathVariable Long id, ModelMap model) {
        int p = JOptionPane.showConfirmDialog(null,"Are you really really really sure???","Delete artist",JOptionPane.YES_NO_OPTION);
        
        if (p==0) {
            artistService.deleteArtistById(id);
        }
        return this.listArtist(model); // Return overzicht events
    }

    @Transactional
    @RequestMapping(value = {"", "/list"}, method = RequestMethod.GET)
    public String listArtist(ModelMap model) {
        List<Artist> lstArtists = artistService.findAllArtists(); // ophalen gegevens uit database
        model.addAttribute("artists", lstArtists); //Attribute aan "pagina" model toevoegen naam: users, data= List<Users> met naam lstUsers

        return "artistsall"; // JSP Pagina pointer
    }

}
