package ru.bellintegrator.organization.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.organization.dao.OrganizationDAO;
import ru.bellintegrator.organization.model.Organization;
import ru.bellintegrator.organization.service.OrganizationService;
import ru.bellintegrator.organization.view.OrganizationView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class OrganizationServiceImpl implements OrganizationService{
    private final Logger log = LoggerFactory.getLogger(OrganizationServiceImpl.class);

    private final OrganizationDAO dao;

    @Autowired
    public OrganizationServiceImpl(OrganizationDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public void add(OrganizationView organization) {
        Organization organizations = new Organization(organization.name, organization.fullname, organization.inn, organization.kpp, organization.address, organization.phone, organization.isActive);
        dao.save(organizations);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrganizationView> organization() {
        List<Organization> all = dao.all();

        Function<Organization, OrganizationView> mapOrganizations = p -> {
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

    @Override
    @Transactional(readOnly = true)
    public List<OrganizationView> list(String name,String inn, Boolean isActive) {

        List<Organization> all = dao.filter(name,inn,isActive);

        Function<Organization, OrganizationView> mapEmploye = p -> {
            OrganizationView view = new OrganizationView();
            view.id = String.valueOf(p.getId());
            view.name = p.getName();
            view.isActive = p.getIsActive();

            log.info(view.toString());

            return view;
        };

        return all.stream()
                .map(mapEmploye)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void update(Long id, String name, String fullName, String inn,
                       String kpp, String address, String phone, Boolean isActive) {
        dao.update(id, name, fullName, inn, kpp, address, phone, isActive);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        dao.delete(id);
    }
}
