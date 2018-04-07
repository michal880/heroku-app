package pl.sternik.mr.heroku.web.controlers.th;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.sternik.mr.heroku.entities.Movie;
import pl.sternik.mr.heroku.entities.Rodzaj;
import pl.sternik.mr.heroku.services.FilmotekaService;
import pl.sternik.mr.heroku.services.NotificationService;
import org.slf4j.Logger;

@Controller
public class MoviesController {

    @Autowired
    private Logger logger; 
    
    @Autowired
    // @Qualifier("spring-data")
    @Qualifier("tablica")
    // @Qualifier("lista")
    private FilmotekaService filmotekaService;

    @Autowired
    private NotificationService notifyService;

    @ModelAttribute("statusyAll")
    public List<Rodzaj> populateStatusy() {
        return Arrays.asList(Rodzaj.ALL);
    }
    
    @ModelAttribute("MyMessages")
    public List<NotificationService.NotificationMessage> populateMessages() {
        logger.info("Daj messagesy!");
        return notifyService.getNotificationMessages();
    }
    

    @GetMapping(value = "/movies/{id}")
    public String view(@PathVariable("id") Long id, final ModelMap model) {
        Optional<Movie> result;
        result = filmotekaService.findById(id);
        if (result.isPresent()) {
            Movie movie = result.get();
            model.addAttribute("movies", movie);
            return "th/movie";
        } else {
            notifyService.addErrorMessage("Cannot find movies #" + id);
            model.clear();
            return "redirect:/movies";
        }
    }

    @RequestMapping(value = "/movies/{id}/json", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Movie> viewAsJson(@PathVariable("id") Long id, final ModelMap model) {
        Optional<Movie> result;
        result = filmotekaService.findById(id);
        if (result.isPresent()) {
            Movie movie = result.get();
            return new ResponseEntity<Movie>(movie, HttpStatus.OK);
        } else {
            notifyService.addErrorMessage("Cannot find movies #" + id);
            model.clear();
            return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/movices", params = { "save" }, method = RequestMethod.POST)
    public String saveMovie(Movie movie, BindingResult bindingResult, ModelMap model) {
        // @Valid
        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Please fill the form correctly!");
            model.addAttribute("MyMessages",  notifyService.getNotificationMessages());
            return "th/movie";
        }
        

        
        Optional<Movie> result = filmotekaService.edit(movie);
        if (result.isPresent())
            notifyService.addInfoMessage("Zapis udany");
        else
            notifyService.addErrorMessage("Zapis NIE udany");
        model.clear();
        return "redirect:/movies";
    }

    @RequestMapping(value = "/movies", params = { "create" }, method = RequestMethod.POST)
    public String createMovie(Movie movie, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Please fill the form correctly!");
            model.addAttribute("MyMessages",  notifyService.getNotificationMessages());
            return "th/movie";
        }
        filmotekaService.create(movie);
        model.clear();
        notifyService.addInfoMessage("Zapis nowej udany");
        return "redirect:/movies";
    }

    @RequestMapping(value = "/movies", params = { "remove" }, method = RequestMethod.POST)
    public String removeRow(final Movie movie, final BindingResult bindingResult, final HttpServletRequest req, @RequestParam Integer remove) {
//        final Integer rowId = Integer.valueOf(req.getParameter("remove"));
        Optional<Boolean> result = filmotekaService.deleteById(remove.longValue());
        return "redirect:/movies";
    }

    @RequestMapping(value = "/movies/create", method = RequestMethod.GET)
    public String showMainPages(final Movie movie) {
        return "th/movies";
    }
}