package ru.bellintegrator.offices.controller.impl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.offices.controller.OfficesController;
import ru.bellintegrator.offices.service.OfficeService;
import ru.bellintegrator.offices.view.OfficeView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)
public class OfficesControllerImpl implements OfficesController{

    private final OfficeService officeService;

    @Autowired
    public OfficesControllerImpl(OfficeService officeService) {
        this.officeService = officeService;
    }

    @Override
    @ApiOperation(value = "addOffices", nickname = "addOffices", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/offices", method = {POST})
    public void offices(@RequestBody OfficeView offices) {
        officeService.add(offices);
    }

    @Override
    @ApiOperation(value = "getOffices", nickname = "getOffices", httpMethod = "GET")
    @RequestMapping(value = "/offices", method = {GET})
    public List<OfficeView> offices() {
        return officeService.offices();
    }
}
