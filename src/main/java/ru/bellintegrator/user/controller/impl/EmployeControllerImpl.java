package ru.bellintegrator.user.controller.impl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.user.controller.EmployeController;
import ru.bellintegrator.user.controller.UserController;
import ru.bellintegrator.user.service.EmployeService;
import ru.bellintegrator.user.service.UserService;
import ru.bellintegrator.user.view.EmployeView;
import ru.bellintegrator.user.view.UserView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)
public class EmployeControllerImpl implements EmployeController {
    private final EmployeService employeService;

    @Autowired
    public EmployeControllerImpl(EmployeService employeService) {
        this.employeService = employeService;
    }


    @Override
    @ApiOperation(value = "addEmploye", nickname = "addEmploye", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/employe", method = {POST})
    public void employe(@RequestBody EmployeView employe) {
        employeService.add(employe);
    }

    @Override
    @ApiOperation(value = "getEmploye", nickname = "getEmploye", httpMethod = "GET")
    @RequestMapping(value = "/employe", method = {GET})
    public List<EmployeView> employe() {
        return employeService.employe();
    }
}