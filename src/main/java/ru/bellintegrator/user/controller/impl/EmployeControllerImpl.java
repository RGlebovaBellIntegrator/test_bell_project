package ru.bellintegrator.user.controller.impl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.optional.Data;
import ru.bellintegrator.user.controller.EmployeController;
import ru.bellintegrator.user.service.EmployeService;
import ru.bellintegrator.user.view.EmployeView;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
@RequestMapping(value = "api/employe", produces = APPLICATION_JSON_VALUE)
public class EmployeControllerImpl implements EmployeController {
    private final EmployeService employeService;

    @Autowired
    public EmployeControllerImpl(EmployeService employeService) {
        this.employeService = employeService;
    }


    @Override
    @ApiOperation(value = "createEmploye", nickname = "createEmploye", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/create", method = {POST})
    public Data save(@RequestBody EmployeView employe) {
            return new Data(employeService.add(employe));
    }

    @Override
    @ApiOperation(value = "getAll", nickname = "getAll", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/all", method = {GET})
    public Data all() {
        return new Data(employeService.employe());
    }

    @Override
    @ApiOperation(value = "getEmploye", nickname = "getEmploye", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Data.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/{id}", method = {GET})
    public Data employe(@PathVariable Long id) {
        return new Data(employeService.find(id));
    }


    @Override
    @ApiOperation(value = "getList", nickname = "getList", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/list", method = {POST})
    public Data employe(@RequestBody EmployeView employeView) {
        return new Data(employeService.employeFilter(employeView));
    }

    @Override
    @ApiOperation(value = "updateEmploye", nickname = "updateEmploye", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/update", method = {POST})
    public Data  update(@RequestBody EmployeView employeView) {
        employeService.update(employeView);
        return new Data();
    }

    @Override
    @ApiOperation(value = "deleteEmploye", nickname = "deleteEmploye", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/delete", method = {POST})
    public Data delete(@RequestBody EmployeView body) {
        employeService.delete(body.id);
        return new Data();
    }
}
