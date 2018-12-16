package pe.fcg.kth.id1212.hw4.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RootController {
    @GetMapping("")
    public String getIndex() {
        return "redirect:/currency-conversion";
    }
}
