package pl.sternik.mr.heroku.web.controlers.th;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.sternik.mr.heroku.entities.Movie;
import pl.sternik.mr.heroku.services.FilmotekaService;
import pl.sternik.mr.heroku.services.NotificationService;


@Controller
public class FilmotekaController {

    @Autowired
//    @Qualifier("spring-data")
 //   @Qualifier("tablica")
//    @Qualifier("lista")
    private FilmotekaService filmotekaService;

    @Autowired
    private NotificationService notificationService;

//    @ModelAttribute("statusyAll")
//    public List<Status> populateStatusy() {
//        return Arrays.asList(Status.ALL);
//    }

    @ModelAttribute("moviesAll")
    public List<Movie> populateCoins() {
        return this.filmotekaService.findAll();
    }

    @ModelAttribute("moviesToSell")
    public List<Movie> populateMoviesToSell() {
        return this.filmotekaService.findAllToSell();
    }


    @RequestMapping({ "/", "/index" })
    public String index(Model model) {
        return "th/index";
    }

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public String showMainPage(Model model) {
        model.addAttribute("MyMessages",  notificationService.getNotificationMessages());
        return "th/filmoteka";
    }

    @RequestMapping("/tosell")
    public String showToSellPage() {
        return "th/tosell";
    }

}
