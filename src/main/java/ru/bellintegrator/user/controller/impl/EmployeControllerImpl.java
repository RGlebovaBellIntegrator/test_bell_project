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
import ru.bellintegrator.optional.ResultResponse;
import ru.bellintegrator.user.controller.EmployeController;
import ru.bellintegrator.user.service.EmployeService;
import ru.bellintegrator.user.view.EmployeView;

import java.util.List;
import java.util.Map;

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
    @ApiOperation(value = "addEmploye", nickname = "addEmploye", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/create", method = {POST})
    public ResponseEntity<?> save(@RequestBody EmployeView employe) {
        try{
            return new ResponseEntity<>(new Data(employeService.add(employe)), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(new Data(ex.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @ApiOperation(value = "getAll", nickname = "getAll", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/all", method = {GET})
    public ResponseEntity<?> all() {
        try{
            return new ResponseEntity<>(new Data(employeService.employe()), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(new Data(ex.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @ApiOperation(value = "getEmploye", nickname = "getEmploye", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Data.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/{id}", method = {GET})
    public ResponseEntity<?> employe(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(new Data(employeService.find(id)), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Data(ex.toString()), HttpStatus.BAD_REQUEST);
        }
    }


    @Override
    @ApiOperation(value = "getList", nickname = "getList", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/list", method = {POST})
    public ResponseEntity<?> employe(@RequestBody EmployeView employeView) {
        try{
            return new ResponseEntity<>(new Data(employeService.employeFilter(employeView)), HttpStatus.OK);
        }
        catch(Exception ex){
            return new ResponseEntity<>(new Data(ex.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @ApiOperation(value = "updateEmploye", nickname = "updateEmploye", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/update", method = {POST})
    public ResponseEntity<?>  update(@RequestBody EmployeView employeView) {
        try {
            employeService.update(employeView);
            return new ResponseEntity<>(new Data(new ResultResponse("success")), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Data(ex.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @ApiOperation(value = "deleteEmploye", nickname = "deleteEmploye", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/delete", method = {POST})
    public ResponseEntity<?>  delete(@RequestBody EmployeView body) {
        try {
            employeService.delete(body.id);
            return new ResponseEntity<>(new Data(new ResultResponse("success")), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Data(ex.toString()), HttpStatus.BAD_REQUEST);
        }
    }
}
