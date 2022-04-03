package controllers;

import entities.Tube;
import org.softuni.broccolina.solet.HttpSoletRequest;
import org.softuni.summer.api.Controller;
import org.softuni.summer.api.GetMapping;
import org.softuni.summer.api.Model;
import org.softuni.summer.api.PreAuthorize;
import repositories.TubeRepository;

import java.util.List;

@Controller
public class HomeController extends BaseController {
    private TubeRepository tubeRepository;

    public HomeController() {
        this.tubeRepository = new TubeRepository();
    }

    @GetMapping(route = "/")
    @PreAuthorize
    public String index(HttpSoletRequest request, Model model) {
        return super.view(request,model, "index");
    }

    @GetMapping(route = "/home")
    @PreAuthorize(loggedin = true)
    public String home(HttpSoletRequest request, Model model) {
        String userId = String.valueOf(request.getSession().getAttributes().get("user-id"));

        model.addAttribute("username", request.getSession().getAttributes().get("username"));
        model.addAttribute("id", userId);

        //<div class="row mb-2 col-md-10 d-flex justify-content-around">

        StringBuilder result = new StringBuilder();
        List<Tube> allTubes = this.tubeRepository.findAllTubes();

        for (int i = 0; i < allTubes.size(); i++) {
            Tube currentTube = allTubes.get(i);

            if (i == 0) {
                result.append("<div class=\"row mb-2 col-md-10 d-flex justify-content-around\">");
            } else if (i % 3 == 0) {
                result.append("</div>")
                        .append("<div class=\"row mb-2 col-md-10 d-flex justify-content-around\">");
            }
            result.append(currentTube.getTubeThumbnailView());
        }

        if (!allTubes.isEmpty()) result.append("</div>");

        model.addAttribute("allTubes", result.toString());

        return super.view(request,model, "home");
    }
}
