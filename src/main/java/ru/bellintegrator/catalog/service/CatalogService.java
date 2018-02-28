package ru.bellintegrator.catalog.service;

import ru.bellintegrator.catalog.view.CountriesView;
import ru.bellintegrator.catalog.view.DocsView;
import ru.bellintegrator.practice.view.PersonView;

import java.util.List;

public interface CatalogService {

    /**
     * Dummy service method
     * @return {@Docs}
     */
    List<DocsView> docs();


    /**
     * Dummy service method
     * @return {@Countries}
     */
    List<CountriesView> countries();
}
