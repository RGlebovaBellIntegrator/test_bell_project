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
import ru.bellintegrator.catalog.view.DocView;
import ru.bellintegrator.catalog.view.CountrieView;

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
    @ApiOperation(value = "addCountrie", nickname = "addCountrie", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/countrie", method = {POST})
    public void countrie(@RequestBody CountrieView countrie) {
        catalogService.add(countrie);
    }

    @Override
    @ApiOperation(value = "addDoc", nickname = "addDoc", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/doc", method = {POST})
    public void doc(@RequestBody DocView doc) {
        catalogService.add(doc);
    }/* */

    @Override
    @ApiOperation(value = "getDoc", nickname = "getDoc", httpMethod = "GET")
    @RequestMapping(value = "/doc", method = {GET})
    public List<DocView> doc() {
        return catalogService.docs();
    }

    @Override
    @ApiOperation(value = "getCountrie", nickname = "getCountrie", httpMethod = "GET")
    @RequestMapping(value = "/countrie", method = {GET})
    public List<CountrieView> countrie() {
        return catalogService.countrie();
    }
}
