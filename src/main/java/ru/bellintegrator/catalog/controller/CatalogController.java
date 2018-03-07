package ru.bellintegrator.catalog.controller;

import org.springframework.web.bind.annotation.RequestBody;
import ru.bellintegrator.catalog.view.CountryView;
import ru.bellintegrator.catalog.view.DocView;

import java.util.List;

public interface CatalogController {

    /**
     * Add doc
     * @param doc
     */
    void doc(@RequestBody DocView doc);

    /**
     * Add country
     * @param country
     */
    void country(@RequestBody CountryView country);

    /**
     * Get all doc
     * @return JSON doc value
     */
    List<DocView> doc();

    /**
     * Get all country
     * @return JSON country value
     */
    List<CountryView> country();
}
