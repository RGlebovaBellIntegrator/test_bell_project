package ru.bellintegrator.catalog.controller.impl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.catalog.controller.CatalogController;
import ru.bellintegrator.catalog.service.CatalogService;
import ru.bellintegrator.catalog.view.DocsView;
import ru.bellintegrator.catalog.view.CountriesView;
import ru.bellintegrator.practice.view.PersonView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)
public class CatalogControllerImpl implements CatalogController {

    private final CatalogService catalogService;

    @Autowired
    public CatalogControllerImpl(CatalogService catalogService) {
        this.catalogService = catalogService;
    }


   @Override
    @ApiOperation(value = "addCountries", nickname = "addCountries", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/countries", method = {POST})
    public void countries(@RequestBody CountriesView countries) {
        catalogService.add(countries);
    }

    @Override
    @ApiOperation(value = "addDocs", nickname = "addDocs", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/docs", method = {POST})
    public void docs(@RequestBody DocsView docs) {
        catalogService.add(docs);
    }/* */

    @Override
    @ApiOperation(value = "getDocs", nickname = "getDocs", httpMethod = "GET")
    @RequestMapping(value = "/docs", method = {GET})
    public List<DocsView> docs() {
        return catalogService.docs();
    }

    @Override
    @ApiOperation(value = "getCountries", nickname = "getCountries", httpMethod = "GET")
    @RequestMapping(value = "/countries", method = {GET})
    public List<CountriesView> countries() {
        return catalogService.countries();
    }
}
