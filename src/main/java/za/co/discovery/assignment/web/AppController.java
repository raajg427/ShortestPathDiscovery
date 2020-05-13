package za.co.discovery.assignment.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class AppController {

    @GetMapping("/")
    public String getHome(Model model) {
        return "home";
    }
}
