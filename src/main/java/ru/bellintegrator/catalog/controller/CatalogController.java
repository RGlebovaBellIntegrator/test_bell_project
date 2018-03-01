package ru.bellintegrator.catalog.controller;

import org.springframework.web.bind.annotation.RequestBody;
import ru.bellintegrator.catalog.view.CountriesView;
import ru.bellintegrator.catalog.view.DocsView;

import java.util.List;

public interface CatalogController {

    /**
     * Add docs
     * @param docs
     */
    void docs(@RequestBody DocsView docs);

    /**
     * Add countries
     * @param countries
     */
    void countries(@RequestBody CountriesView countries);

    /**
     * Get all docs
     * @return JSON docs value
     */
    List<DocsView> docs();

    /**
     * Get all countries
     * @return JSON docs value
     */
    List<CountriesView> countries();
}
