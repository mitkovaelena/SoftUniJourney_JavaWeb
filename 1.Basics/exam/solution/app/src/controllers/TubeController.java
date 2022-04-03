package controllers;

import bindingModels.TubeAddBindingModel;
import entities.Tube;
import org.softuni.broccolina.solet.HttpSoletRequest;
import org.softuni.summer.api.*;
import repositories.TubeRepository;
import repositories.UserRepository;

@Controller
public class TubeController extends BaseController {
    private UserRepository userRepository;
    private TubeRepository tubeRepository;

    public TubeController() {
        this.userRepository = new UserRepository();
        this.tubeRepository = new TubeRepository();
    }

    @GetMapping(route = "/tube/upload")
    @PreAuthorize(loggedin = true)
    public String uploadTube(HttpSoletRequest request, Model model) {
        this.hideNotification(model);
        return super.view(request, model, "tube-upload");
    }

    @PostMapping(route = "/tube/upload")
    @PreAuthorize(loggedin = true)
    public String uploadTubeConfirm(HttpSoletRequest request, Model model, TubeAddBindingModel tubeAddBindingModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            this.showNotification(model,  bindingResult.getErrors().get(0).getMessage(), "danger");
            return super.view(request,model, "/tube/upload");
        }

        String currentUserId = request.getSession().getAttributes().get("user-id").toString();

        if(tubeAddBindingModel == null || tubeAddBindingModel.getTitle() == null
                || tubeAddBindingModel.getAuthor() == null
                || tubeAddBindingModel.getDescription() == null
                ||tubeAddBindingModel.getYoutubeId() == null ) {
            this.showNotification(model, "Please fill in the form.", "danger");
            return super.redirect(request,model, "/tube/upload");
        }

        Tube tube = new Tube();

        tube.setTitle(tubeAddBindingModel.getTitle());
        tube.setAuthor(tubeAddBindingModel.getAuthor());
        tube.setYoutubeId(tubeAddBindingModel.getYoutubeId());
        tube.setDescription(tubeAddBindingModel.getDescription());
        tube.setViews(0);
        tube.setUploader(this.userRepository.findById(currentUserId));

        this.tubeRepository.createTube(tube);

        return super.redirect(request, model, "home");
    }

    @GetMapping(route = "/tube/details/{id}")
    @PreAuthorize(loggedin = true)
    public String tubeDetails(@PathVariable String id,  HttpSoletRequest request, Model model) {

        this.tubeRepository.incrementViews(id);
        Tube tube = this.tubeRepository.findById(id);

        if(tube == null) {
            return super.redirect(request,model, "home");
        }

        model.addAttribute("title", tube.getTitle());
        model.addAttribute("author", tube.getAuthor());
        model.addAttribute("views", tube.getViews());
        model.addAttribute("iFrameUrl", tube.getIFrameUrl());
        model.addAttribute("description", tube.getDescription());


        return super.view(request, model, "tube-details");
    }
}
