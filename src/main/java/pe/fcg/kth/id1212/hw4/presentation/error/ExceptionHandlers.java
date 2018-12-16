package pe.fcg.kth.id1212.hw4.presentation.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pe.fcg.kth.id1212.hw4.domain.ApplicationException;

@Controller
@ControllerAdvice
public class ExceptionHandlers implements ErrorController {
    private static String ERROR_PATH = "/error";

    @ExceptionHandler(ApplicationException.class)
    @ResponseStatus(HttpStatus.OK)
    public String handleException(ApplicationException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return ERROR_PATH;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception ex, Model model) {
        ex.printStackTrace();
        return ERROR_PATH;
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
