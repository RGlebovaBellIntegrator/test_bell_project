package ru.bellintegrator.organizations.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.organizations.dao.OrganizationsDAO;
import ru.bellintegrator.organizations.model.Organizations;
import ru.bellintegrator.organizations.service.OrganizationService;
import ru.bellintegrator.organizations.view.OrganizationView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class OrganizationServiceImpl implements OrganizationService{
    private final Logger log = LoggerFactory.getLogger(OrganizationServiceImpl.class);

    private final OrganizationsDAO dao;

    @Autowired
    public OrganizationServiceImpl(OrganizationsDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public void add(OrganizationView view) {
        Organizations organizations = new Organizations(view.name, view.fullname, view.inn, view.kpp, view.address, view.phone, view.isActive);
        dao.save(organizations);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrganizationView> organizations() {
        List<Organizations> all = dao.all();

        Function<Organizations, OrganizationView> mapOrganizations = p -> {
            OrganizationView view = new OrganizationView();
            view.id = String.valueOf(p.getId());
            view.name = p.getName();

            log.info(view.toString());

            return view;
        };

        return all.stream()
                .map(mapOrganizations)
                .collect(Collectors.toList());
    }
}
