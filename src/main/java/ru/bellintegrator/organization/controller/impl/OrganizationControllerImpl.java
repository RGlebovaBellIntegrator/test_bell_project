package ru.bellintegrator.organization.controller.impl;

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
import ru.bellintegrator.organization.controller.OrganizationController;
import ru.bellintegrator.organization.service.OrganizationService;
import ru.bellintegrator.organization.view.OrganizationView;

import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "api/organization", produces = APPLICATION_JSON_VALUE)
public class OrganizationControllerImpl implements OrganizationController {
    private final OrganizationService organizationService;

    @Autowired
    public OrganizationControllerImpl(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }


    @Override
    @ApiOperation(value = "addOrganization", nickname = "addOrganization", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/save", method = {POST})
    public ResponseEntity<?> save(@RequestBody OrganizationView organization) {
        try {
            organizationService.add(organization);
            return new ResponseEntity<>(new Data(new ResultResponse("success")), HttpStatus.OK);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(new Data(ex.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @ApiOperation(value = "getList", nickname = "getList", httpMethod = "GET")
    @RequestMapping(value = "/all", method = {GET})
    public ResponseEntity<?> all() {
        try {
            return new ResponseEntity<>(new Data(organizationService.organization()), HttpStatus.OK);
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
    public ResponseEntity<?> list(@RequestBody OrganizationView organizationView) {
        try {
            return new ResponseEntity<>(new Data(organizationService.list(organizationView)), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Data(ex.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @ApiOperation(value = "getOrganization", nickname = "getOrganization", httpMethod = "GET")
    @RequestMapping(value = "/organization", method = {GET})
    public ResponseEntity<?> organization() {
        try {
            return new ResponseEntity<>(new Data(organizationService.organization()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Data(ex.toString()), HttpStatus.BAD_REQUEST);
        }
    }


    @Override
    @ApiOperation(value = "updateOrganization", nickname = "updateOrganization", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/update", method = {POST})
    public ResponseEntity<?> update(@RequestBody OrganizationView organizationView) {
        try {
            organizationService.update(organizationView);
            return new ResponseEntity<>(new Data(new ResultResponse("success")), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Data(ex.toString()), HttpStatus.BAD_REQUEST);
        }
    }


    @Override
    @ApiOperation(value = "deleteOrganization", nickname = "deleteOrganization", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/delete", method = {POST})
    public ResponseEntity<?> delete(@RequestBody OrganizationView organizationView) {
        try {
            organizationService.delete(organizationView.id);
            return new ResponseEntity<>(new Data(new ResultResponse("success")), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Data(ex.toString()), HttpStatus.BAD_REQUEST);
        }
    }
}
