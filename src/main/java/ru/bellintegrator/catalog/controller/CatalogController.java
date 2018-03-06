package ru.bellintegrator.catalog.controller;

import org.springframework.web.bind.annotation.RequestBody;
import ru.bellintegrator.catalog.view.CountrieView;
import ru.bellintegrator.catalog.view.DocView;

import java.util.List;

public interface CatalogController {

    /**
     * Add doc
     * @param doc
     */
    void doc(@RequestBody DocView doc);

    /**
     * Add countrie
     * @param countrie
     */
    void countrie(@RequestBody CountrieView countrie);

    /**
     * Get all doc
     * @return JSON doc value
     */
    List<DocView> doc();

    /**
     * Get all countrie
     * @return JSON doc value
     */
    List<CountrieView> countrie();
}
