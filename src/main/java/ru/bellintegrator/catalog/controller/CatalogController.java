package ru.bellintegrator.catalog.controller;

import org.springframework.web.bind.annotation.RequestBody;
import ru.bellintegrator.catalog.view.CountriesView;
import ru.bellintegrator.catalog.view.DocsView;

import java.util.List;

public interface CatalogController {

    /**
     * Add person
     * @param docs

    void docs(@RequestBody DocsView docs);*/

    /**
     * Add person
     * @param countries

    void countries(@RequestBody CountriesView countries);*/

    /**
     * Get all persons
     * @return JSON docs value
     */
    List<DocsView> docs();

    /**
     * Get all persons
     * @return JSON docs value
     */
    List<CountriesView> countries();
}
