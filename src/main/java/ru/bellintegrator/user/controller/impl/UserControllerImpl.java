package ru.bellintegrator.user.controller.impl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.optional.Data;
import ru.bellintegrator.optional.ResultResponse;
import ru.bellintegrator.user.controller.UserController;
import ru.bellintegrator.user.service.UserService;
import ru.bellintegrator.user.view.UserView;

import java.util.Dictionary;
import java.util.List;
import java.util.Map;

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
            return new ResponseEntity<>(new Data(new ResultResponse("success")), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Data(ex.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @ApiOperation(value = "getUser", nickname = "getUser", httpMethod = "GET")
    @RequestMapping(value = "/user", method = {GET})
    public List<UserView> user() {
        return userService.user();
    }

    @Override
    @ApiOperation(value = "getLogin", nickname = "getLogin", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/login", method = {POST})
    public ResponseEntity<?> login(@RequestBody Map<String,String> body) {
        try {
            if(userService.login(body.get("login"),body.get("password")))
                return new ResponseEntity<>(new Data(new ResultResponse("success")), HttpStatus.OK);
            else
                return new ResponseEntity<>(new Data("login/password не найдены"), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Data(ex.toString()), HttpStatus.BAD_REQUEST);
        }
    }
}
