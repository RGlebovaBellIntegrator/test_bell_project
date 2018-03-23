package ru.bellintegrator.catalog.controller.impl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.catalog.controller.CatalogController;
import ru.bellintegrator.catalog.service.CatalogService;
import ru.bellintegrator.catalog.view.DocView;
import ru.bellintegrator.catalog.view.CountryView;
import ru.bellintegrator.optional.Data;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "api/", produces = APPLICATION_JSON_VALUE)
public class CatalogControllerImpl implements CatalogController {

    private final CatalogService catalogService;

    @Autowired
    public CatalogControllerImpl(CatalogService catalogService) {
        this.catalogService = catalogService;
    }


    @Override
    @ApiOperation(value = "addCountry", nickname = "addCountry", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/countries", method = {POST})
    public ResponseEntity<?> country(@RequestBody CountryView country) {

       try {
           catalogService.add(country);
           return new ResponseEntity<>(new Data(), HttpStatus.OK);
       }
       catch (Exception ex) {
           return new ResponseEntity<>(new Data(ex.toString()), HttpStatus.BAD_REQUEST);
       }
    }

    @Override
    @ApiOperation(value = "addDoc", nickname = "addDoc", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/docs", method = {POST})
    public ResponseEntity<?> doc(@RequestBody DocView doc) {
        try {
            catalogService.add(doc);
            return new ResponseEntity<>(new Data(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Data(ex.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @ApiOperation(value = "getDoc", nickname = "getDoc", httpMethod = "GET")
    @RequestMapping(value = "/docs", method = {GET})
    public ResponseEntity<?> doc() {
        try {
            return new ResponseEntity<>(new Data(catalogService.docs()), HttpStatus.OK);
    } catch (Exception ex) {
        return new ResponseEntity<>(new Data(ex.toString()), HttpStatus.BAD_REQUEST);
    }
    }

    @Override
    @ApiOperation(value = "getCountry", nickname = "getCountry", httpMethod = "GET")
    @RequestMapping(value = "/countries", method = {GET})
    public ResponseEntity<?> country() {
        try {
            return new ResponseEntity<>(new Data(catalogService.country()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Data(ex.toString()), HttpStatus.BAD_REQUEST);
        }
    }
}
