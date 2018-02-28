package ru.bellintegrator.organizations.controller.impl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.organizations.controller.OrganizationsController;
import ru.bellintegrator.organizations.service.OrganizationService;
import ru.bellintegrator.organizations.view.OrganizationView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)
public class OrganizationsControllerImpl implements OrganizationsController{
    private final OrganizationService organizationService;

    @Autowired
    public OrganizationsControllerImpl(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }


    @Override
    @ApiOperation(value = "addOrganizations", nickname = "addOrganizations", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/organizations", method = {POST})
    public void organizations(@RequestBody OrganizationView organizations) {
        organizationService.add(organizations);
    }

    @Override
    @ApiOperation(value = "getOrganizations", nickname = "getOrganizations", httpMethod = "GET")
    @RequestMapping(value = "/organizations", method = {GET})
    public List<OrganizationView> organizations() {
        return organizationService.organizations();
    }
}
