package ru.bellintegrator.offices.controller.impl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.offices.controller.OfficeController;
import ru.bellintegrator.offices.service.OfficeService;
import ru.bellintegrator.offices.view.OfficeView;
import ru.bellintegrator.optional.Data;
import ru.bellintegrator.optional.ResultResponse;

import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "api/office", produces = APPLICATION_JSON_VALUE)
public class OfficeControllerImpl implements OfficeController {

    private final OfficeService officeService;

    @Autowired
    public OfficeControllerImpl(OfficeService officeService) {
        this.officeService = officeService;
    }

    @Override
    @ApiOperation(value = "addOffice", nickname = "addOffice", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/save", method = {POST})
    public ResponseEntity<?> office(@RequestBody OfficeView office) {
        try {
            officeService.add(office);
            return new ResponseEntity<>(new Data(new ResultResponse("success")), HttpStatus.OK);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(new Data(ex.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @ApiOperation(value = "getOffice", nickname = "getOffice", httpMethod = "GET")
    @RequestMapping(value = "/all", method = {GET})
    public ResponseEntity<?> office() {
        try {
            return new ResponseEntity<>(new Data(officeService.office()), HttpStatus.OK);
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
    public ResponseEntity<?> list(@RequestBody OfficeView office) {
        try {
            return new ResponseEntity<>(new Data(officeService.list(office)), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Data(ex.toString()), HttpStatus.BAD_REQUEST);
        }
    }


    @Override
    @ApiOperation(value = "updateOffice", nickname = "updateOffice", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/update", method = {POST})
    public ResponseEntity<?> update(@RequestBody OfficeView office) {
        try {
            officeService.update(office);
            return new ResponseEntity<>(new Data(new ResultResponse("success")), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Data(ex.toString()), HttpStatus.BAD_REQUEST);
        }
    }


    @Override
    @ApiOperation(value = "deleteOffice", nickname = "deleteOffice", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/delete", method = {POST})
    public ResponseEntity<?> delete(@RequestBody OfficeView office) {
        try {
            officeService.delete(office.id);
            return new ResponseEntity<>(new Data(new ResultResponse("success")), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Data(ex.toString()), HttpStatus.BAD_REQUEST);
        }
    }
}
