package ru.bellintegrator.user.controller.impl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import ru.bellintegrator.user.controller.EmployeController;
import ru.bellintegrator.user.service.EmployeService;
import ru.bellintegrator.user.view.EmployeView;

import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
@RequestMapping(value = "/user", produces = APPLICATION_JSON_VALUE)
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
    @RequestMapping(value = "/save", method = {POST})
    public void employe(@RequestBody EmployeView employe) {
        employeService.add(employe);
    }

    @Override
    @ApiOperation(value = "getEmploye", nickname = "getEmploye", httpMethod = "GET")
    @RequestMapping(value = "/employe", method = {GET})
    public  @ResponseBody List<EmployeView> employe() {
        return employeService.employe();
    }


    @Override
    @ApiOperation(value = "getList", nickname = "getList", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/list", method = {POST})
    public List<EmployeView> employe(@RequestBody Map<String,String> body) {
        return employeService.employeFilter(body.get("firstname"), body.get("secondname"), body.get("middlename"),
                body.get("position"), body.get("doc_code"), body.get("office_id"), body.get("country_code")) ;
    }

    @Override
    @ApiOperation(value = "updateEmploye", nickname = "updateEmploye", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/update", method = {POST})
    public void update(@RequestBody Map<String,String> body) {
        employeService.update( Long.parseLong(body.get("id")),
                body.get("firstname"),
                body.get("secondname"),
                body.get("middlename"),
                body.get("position"),
                body.get("phone"),
                body.get("doc_name"),
                body.get("doc_number"),
                body.get("doc_date"),
                body.get("country_name"),
                body.get("country_code"),
                Boolean.parseBoolean(body.get("isIdentified")));
    }

    @Override
    @ApiOperation(value = "deleteEmploye", nickname = "deleteEmployeEmploye", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/delete", method = {POST})
    public void delete(@RequestBody Map<String,String> body) {
        employeService.delete( Long.parseLong(body.get("id")));
    }
}
