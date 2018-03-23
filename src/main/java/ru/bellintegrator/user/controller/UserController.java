package ru.bellintegrator.user.controller;

import org.eclipse.jetty.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.bellintegrator.optional.Data;
import ru.bellintegrator.user.view.UserView;

import java.util.List;
import java.util.Map;

public interface UserController {
    /**
     * Add user
     * @param users
     */
    ResponseEntity<?> user(@RequestBody UserView users);

    /**
     * Get all user
     * @return JSON user value
     */
    ResponseEntity<?> user();

    ResponseEntity<?> login(@RequestBody UserView userView);

    ResponseEntity<?> activation(@RequestParam("code") String code);
}
