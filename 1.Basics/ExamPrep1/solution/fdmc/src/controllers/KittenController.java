package controllers;

import bindingModels.KittenAddBindingModel;
import entities.Kitten;
import entities.KittenBreed;
import org.softuni.broccolina.solet.HttpSoletRequest;
import org.softuni.summer.api.*;
import repositories.KittenRepository;

@Controller
public class KittenController {
    private KittenRepository kittenRepository;

    public KittenController() {
        this.kittenRepository = new KittenRepository();
    }

    @GetMapping(route = "/kittens/all")
    @PreAuthorize(loggedin = true)
    public String allKittens(HttpSoletRequest request, Model model) {
        Kitten[] allKittens = this.kittenRepository.findAllKittens();

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < allKittens.length; i++) {
            Kitten currentKitten = allKittens[i];

            if(i == 0) {
                result
                        .append("<div class=\"row\">")
                        .append(currentKitten.toString());
            } else if(i % 3 == 0) {
                result
                        .append("</div>")
                        .append("<div class=\"row\">")
                        .append(currentKitten.toString());
            } else {
                result.append(currentKitten.toString());
            }
        }

        if(allKittens.length % 3 != 0) {
            result.append("</div>");
        }

        model.addAttribute("allKittens", result.toString());

        return "template:all-kittens";
    }

    @GetMapping(route = "/kittens/add")
    @PreAuthorize(loggedin = true)
    public String addKitten() {
        return "template:add-kitten";
    }

    @PostMapping(route = "/kittens/add")
    @PreAuthorize(loggedin = true)
    public String addKittenConfirm(KittenAddBindingModel kittenAddBindingModel) {
        Kitten kitten = new Kitten();

        kitten.setName(kittenAddBindingModel.getName());
        kitten.setAge(kittenAddBindingModel.getAge());
        kitten.setBreed(KittenBreed.parseValue(kittenAddBindingModel.getBreed()));

        this.kittenRepository.createKitten(kitten);

        return "redirect:/kittens/all";
    }
}
