package ru.bellintegrator.catalog.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.catalog.dao.CountriesDAO;
import ru.bellintegrator.catalog.dao.DocsDAO;
import ru.bellintegrator.catalog.service.CatalogService;
import ru.bellintegrator.catalog.view.CountriesView;
import ru.bellintegrator.catalog.model.Countries;
import ru.bellintegrator.catalog.view.DocsView;
import ru.bellintegrator.catalog.model.Docs;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class CatalogServiceImpl implements CatalogService{
    private final Logger log = LoggerFactory.getLogger(CatalogServiceImpl.class);

    private final CountriesDAO countriesDAO;
    private final DocsDAO docsDAO;

    @Autowired
    public  CatalogServiceImpl(CountriesDAO countriesDAO, DocsDAO docsDAO)
    {
        this.countriesDAO=countriesDAO;
        this.docsDAO=docsDAO;
    }

    @Override
    @Transactional
    public void add(CountriesView view) {
        Countries countries = new Countries(view.code, view.name);
        countriesDAO.save(countries);
    }

    @Override
    @Transactional
    public void add(DocsView view) {
        Docs docs = new Docs(view.code, view.name);
        docsDAO.save(docs);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CountriesView> countries() {
        List<Countries> all = countriesDAO.all();

        Function<Countries, CountriesView> mapContries = p -> {
            CountriesView view = new CountriesView();
            view.code = p.getCode();
            view.name = p.getName();
            return view;
        };

        return all.stream()
                .map(mapContries)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocsView> docs() {
        List<Docs> all = docsDAO.all();

        Function<Docs, DocsView> mapDocs = p -> {
            DocsView view = new DocsView();
            view.code = p.getCode();
            view.name = p.getName();
            return view;
        };

        return all.stream()
                .map(mapDocs)
                .collect(Collectors.toList());
    }
}
