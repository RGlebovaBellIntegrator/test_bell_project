package ru.bellintegrator.user.controller;

import org.eclipse.jetty.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.bellintegrator.optional.Data;
import ru.bellintegrator.user.view.UserView;

import java.util.List;
import java.util.Map;

public interface UserController {
    /**
     * Add register
     * @param users
     */
    Data register(@RequestBody UserView users);

    /**
     * Get all register
     * @return JSON register value
     */
    Data all();

    Data login(@RequestBody UserView userView);

    Data activation(@RequestParam("code") String code);

    Data activation(@PathVariable Long id);
}
