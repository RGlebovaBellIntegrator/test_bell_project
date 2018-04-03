package ru.bellintegrator.organization.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.optional.NoFoundException;
import ru.bellintegrator.organization.dao.OrganizationDAO;
import ru.bellintegrator.organization.model.Organization;
import ru.bellintegrator.organization.service.OrganizationService;
import ru.bellintegrator.organization.view.OrganizationView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements OrganizationService{
    private final Logger log = LoggerFactory.getLogger(OrganizationServiceImpl.class);

    private final OrganizationDAO dao;

    @Autowired
    public OrganizationServiceImpl(OrganizationDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public OrganizationView add(OrganizationView organization) {
        if (organization.fullName == null)
            throw new NoFoundException("Не задан fullName");
        if (organization.inn == null)
            throw new NoFoundException("Не задан inn");
        if (organization.kpp == null)
            throw new NoFoundException("Не задан kpp");
        if (organization.address == null)
            throw new NoFoundException("Не задан address");

        Organization organizations =
                new Organization(organization.name, organization.fullName, organization.inn, organization.kpp, organization.address, organization.phone, organization.isActive);
        OrganizationView view = new OrganizationView();
        view.id = dao.save(organizations);

        return view;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrganizationView> organization() {
        List<Organization> all = dao.all();

        Function<Organization, OrganizationView> mapOrganizations = p -> {
            OrganizationView view = new OrganizationView();
            view.id = p.getId();
            view.name = p.getName();
            view.inn = p.getInn();
            view.kpp = p.getKpp();
            view.fullName = p.getFullname();
            view.address = p.getAddress();
            view.phone = p.getPhone();
            view.isActive = p.getIsActive();

            log.info(view.toString());

            return view;
        };

        return all.stream()
                .map(mapOrganizations)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrganizationView> list(OrganizationView organizationView) {
        if (organizationView.name == null)
            throw new NoFoundException("name не инициализирован");

        List<Organization> all = dao.filter(organizationView.name,organizationView.inn,organizationView.isActive);

        Function<Organization, OrganizationView> mapEmploye = p -> {
            OrganizationView view = new OrganizationView();
            view.id = p.getId();
            view.name = p.getName();
            view.isActive = p.getIsActive();
            view.officesCount = p.getOffices().size();

            log.info(view.toString());

            return view;
        };

        return all.stream()
                .map(mapEmploye)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void update(OrganizationView organizationView) {
        if (organizationView.id == null)
            throw new NoFoundException("Не задан id");
        dao.update(organizationView);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (id == null)
            throw new NoFoundException("Не задан id");
        dao.delete(id);
    }

    @Override
    @Transactional
    public OrganizationView find(Long id) {
        Organization o = dao.loadById(id);
        if (o==null)
            throw new NoFoundException("Организация не найдена");

        OrganizationView view = new OrganizationView();
        view.id = o.getId();
        view.name = o.getName();
        view.fullName = o.getFullname();
        view.isActive = o.getIsActive();
        view.inn = o.getInn();
        view.kpp = o.getKpp();
        view.address = o.getAddress();
        view.phone = o.getPhone();
        return  view;
    }
}
