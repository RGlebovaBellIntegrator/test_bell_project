package ru.bellintegrator.catalog.service;

import ru.bellintegrator.catalog.view.CountrieView;
import ru.bellintegrator.catalog.view.DocView;

import java.util.List;

public interface CatalogService {

    /**
     *
     * @param docs
     */
    void add(DocView docs);
    /**
     *
     * @param countries
     */
    void add(CountrieView countries);
    /**
     * Catalog service method
     * @return {@Docs}
     */
    List<DocView> docs();


    /**
     * Catalog service method
     * @return {@Countries}
     */
    List<CountrieView> countrie();
}
