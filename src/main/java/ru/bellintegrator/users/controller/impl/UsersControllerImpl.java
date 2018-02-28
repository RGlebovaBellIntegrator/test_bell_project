package ru.bellintegrator.users.controller.impl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.users.controller.UsersController;
import ru.bellintegrator.users.service.UsersService;
import ru.bellintegrator.users.view.UsersView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)
public class UsersControllerImpl implements UsersController{
    private final UsersService usersService;

    @Autowired
    public UsersControllerImpl(UsersService usersService) {
        this.usersService = usersService;
    }


    @Override
    @ApiOperation(value = "addUsers", nickname = "addUsers", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/users", method = {POST})
    public void users(@RequestBody UsersView users) {
        usersService.add(users);
    }

    @Override
    @ApiOperation(value = "getUsers", nickname = "getUsers", httpMethod = "GET")
    @RequestMapping(value = "/users", method = {GET})
    public List<UsersView> users() {
        return usersService.users();
    }
}
