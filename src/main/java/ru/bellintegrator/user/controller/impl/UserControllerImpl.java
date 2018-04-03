package ru.bellintegrator.user.controller.impl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.optional.Data;
import ru.bellintegrator.user.controller.UserController;
import ru.bellintegrator.user.service.UserService;
import ru.bellintegrator.user.view.UserView;

import javax.xml.ws.Response;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "api/", produces = APPLICATION_JSON_VALUE)
public class UserControllerImpl implements UserController {
    private final UserService userService;

    @Autowired
    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }


    @Override
    @ApiOperation(value = "getRegister", nickname = "getRegister", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Data.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/register", method = {POST})
    public void register(@RequestBody UserView user) {
        userService.add(user);
    }

    @Override
    @ApiOperation(value = "getAll", nickname = "getAll", httpMethod = "GET")
    @RequestMapping(value = "/all", method = {GET})
    public Object all() {
        return userService.user();
    }

    @Override
    @ApiOperation(value = "getLogin", nickname = "getLogin", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Data.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/login", method = {POST})
    public void login(@RequestBody UserView userView) {
        userService.login(userView);
    }

    @Override
    @ApiOperation(value = "getActivation", nickname = "getActivation", httpMethod = "GET")
    @RequestMapping(value = "/activation", method = {GET})
    public void activation(@RequestParam("code") String code) {
        userService.activation(code);
    }

    @Override
    @ApiOperation(value = "getActivation", nickname = "getActivation", httpMethod = "GET")
    @RequestMapping(value = "/{id}", method = {GET})
    public Object activation(@PathVariable Long id) {
        return userService.getCode(id);
    }
}
