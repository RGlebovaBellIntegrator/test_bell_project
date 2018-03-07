package ru.bellintegrator.catalog.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.catalog.dao.CountryDAO;
import ru.bellintegrator.catalog.dao.DocDAO;
import ru.bellintegrator.catalog.model.Country;
import ru.bellintegrator.catalog.model.Doc;
import ru.bellintegrator.catalog.service.CatalogService;
import ru.bellintegrator.catalog.view.CountryView;
import ru.bellintegrator.catalog.view.DocView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class CatalogServiceImpl implements CatalogService{
    private final Logger log = LoggerFactory.getLogger(CatalogServiceImpl.class);

    private final CountryDAO countryDAO;
    private final DocDAO docDAO;

    @Autowired
    public  CatalogServiceImpl(CountryDAO countryDAO, DocDAO docDAO)
    {
        this.countryDAO = countryDAO;
        this.docDAO = docDAO;
    }

    @Override
    @Transactional
    public void add(CountryView view) {
        Country country = new Country(view.code, view.name);
        countryDAO.save(country);
    }

    @Override
    @Transactional
    public void add(DocView view) {
        Doc doc = new Doc(view.code, view.name);
        docDAO.save(doc);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CountryView> country() {
        List<Country> all = countryDAO.all();

        Function<Country, CountryView> mapContries = p -> {
            CountryView view = new CountryView();
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
    public List<DocView> docs() {
        List<Doc> all = docDAO.all();

        Function<Doc, DocView> mapDocs = p -> {
            DocView view = new DocView();
            view.code = p.getCode();
            view.name = p.getName();
            return view;
        };

        return all.stream()
                .map(mapDocs)
                .collect(Collectors.toList());
    }
}
