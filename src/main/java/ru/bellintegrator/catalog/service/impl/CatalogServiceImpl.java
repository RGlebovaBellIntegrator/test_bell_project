package ru.bellintegrator.catalog.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.catalog.dao.CountrieDAO;
import ru.bellintegrator.catalog.dao.DocDAO;
import ru.bellintegrator.catalog.model.Countrie;
import ru.bellintegrator.catalog.model.Doc;
import ru.bellintegrator.catalog.service.CatalogService;
import ru.bellintegrator.catalog.view.CountrieView;
import ru.bellintegrator.catalog.view.DocView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class CatalogServiceImpl implements CatalogService{
    private final Logger log = LoggerFactory.getLogger(CatalogServiceImpl.class);

    private final CountrieDAO countrieDAO;
    private final DocDAO docDAO;

    @Autowired
    public  CatalogServiceImpl(CountrieDAO countrieDAO, DocDAO docDAO)
    {
        this.countrieDAO = countrieDAO;
        this.docDAO = docDAO;
    }

    @Override
    @Transactional
    public void add(CountrieView view) {
        Countrie countrie = new Countrie(view.code, view.name);
        countrieDAO.save(countrie);
    }

    @Override
    @Transactional
    public void add(DocView view) {
        Doc doc = new Doc(view.code, view.name);
        docDAO.save(doc);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CountrieView> countrie() {
        List<Countrie> all = countrieDAO.all();

        Function<Countrie, CountrieView> mapContries = p -> {
            CountrieView view = new CountrieView();
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
