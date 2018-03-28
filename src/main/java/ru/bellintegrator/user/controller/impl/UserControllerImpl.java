package ru.bellintegrator.user.controller.impl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.optional.Data;
import ru.bellintegrator.user.controller.UserController;
import ru.bellintegrator.user.service.UserService;
import ru.bellintegrator.user.view.UserView;

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
    public Data register(@RequestBody UserView user) {

            userService.add(user);
            return new Data();

    }

    @Override
    @ApiOperation(value = "getAll", nickname = "getAll", httpMethod = "GET")
    @RequestMapping(value = "/all", method = {GET})
    public Data all() {
        try {
            return new Data(userService.user());
        }
        catch (Exception ex) {
            return new Data(ex.toString());
        }
    }

    @Override
    @ApiOperation(value = "getLogin", nickname = "getLogin", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Data.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/login", method = {POST})
    public Data login(@RequestBody UserView userView) {
            userService.login(userView);
            return new Data();
    }

    @Override
    @ApiOperation(value = "getActivation", nickname = "getActivation", httpMethod = "GET")
    @RequestMapping(value = "/activation", method = {GET})
    public Data activation(@RequestParam("code") String code) {
        userService.activation(code);
        return new Data();
    }
}
