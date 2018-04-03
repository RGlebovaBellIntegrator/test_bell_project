package ru.bellintegrator.user.controller;

import org.eclipse.jetty.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.bellintegrator.optional.Data;
import ru.bellintegrator.user.view.UserView;

import javax.xml.ws.Response;
import java.util.List;
import java.util.Map;

public interface UserController {
    /**
     * Add register
     * @param users
     */
    void register(@RequestBody UserView users);

    /**
     * Get all register
     * @return JSON register value
     */
    Object all();

    void login(@RequestBody UserView userView);

    void activation(@RequestParam("code") String code);

    Object  activation(@PathVariable Long id);
}
