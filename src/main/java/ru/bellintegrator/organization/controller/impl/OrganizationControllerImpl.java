package ru.bellintegrator.organization.controller.impl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public void save(@RequestBody OrganizationView organization) {
        organizationService.add(organization);
    }

    @Override
    @ApiOperation(value = "getList", nickname = "getList", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/list", method = {POST})
    public List<OrganizationView> list(@RequestBody Map<String,String> body) {
        return organizationService.list(body.get("name"), body.get("inn"), Boolean.parseBoolean(body.get("isActive")));
    }

    @Override
    @ApiOperation(value = "getOrganization", nickname = "getOrganization", httpMethod = "GET")
    @RequestMapping(value = "/organization", method = {GET})
    public List<OrganizationView> organization() {
        return organizationService.organization();
    }


    @Override
    @ApiOperation(value = "updateOrganization", nickname = "updateOrganization", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/update", method = {POST})
    public void update(@RequestBody Map<String,String> body) {
        organizationService.update( Long.parseLong(body.get("id")),
                body.get("name"),
                body.get("fullName"),
                body.get("inn"),
                body.get("kpp"),
                body.get("address"),
                body.get("phone"),
                Boolean.parseBoolean(body.get("isActive")));
    }


    @Override
    @ApiOperation(value = "deleteOrganization", nickname = "deleteOrganization", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/delete", method = {POST})
    public void delete(@RequestBody Map<String,String> body) {
        organizationService.delete( Long.parseLong(body.get("id")));
    }
}
