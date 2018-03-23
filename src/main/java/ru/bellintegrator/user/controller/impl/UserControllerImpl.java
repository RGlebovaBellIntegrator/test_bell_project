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
import ru.bellintegrator.optional.Result;
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
    @ApiOperation(value = "addUser", nickname = "addUser", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/register", method = {POST})
    public ResponseEntity<?> user(@RequestBody UserView user) {
        try {
            userService.add(user);
            return new ResponseEntity<>(new Data(new Result("success")), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Data(ex.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @ApiOperation(value = "getUser", nickname = "getUser", httpMethod = "GET")
    @RequestMapping(value = "/all", method = {GET})
    public ResponseEntity<?> user() {
        try {
            return new ResponseEntity<>(new Data(userService.user()), HttpStatus.OK);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(new Data(ex.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @ApiOperation(value = "getLogin", nickname = "getLogin", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/login", method = {POST})
    public Boolean login(@RequestBody UserView userView) {
            return userService.login(userView);
    }

    @Override
    @ApiOperation(value = "getUser", nickname = "getUser", httpMethod = "GET")
    @RequestMapping(value = "/activation", method = {GET})
    public ResponseEntity<?> activation(@RequestParam("code") String code) {
        try {
            if (userService.activation(code))
                return new ResponseEntity<>(new Data(new Result("success")), HttpStatus.OK);
            else
                return new ResponseEntity<>(new Data("Не найден код"), HttpStatus.BAD_REQUEST);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(new Data(ex.toString()), HttpStatus.BAD_REQUEST);
        }
    }
}
