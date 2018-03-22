package ru.bellintegrator.offices.service.impl;

import com.fasterxml.jackson.annotation.JacksonInject;
import org.hibernate.service.spi.InjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.offices.dao.OfficeDAO;
import ru.bellintegrator.offices.model.Office;
import ru.bellintegrator.offices.service.OfficeService;
import ru.bellintegrator.offices.view.OfficeView;
import ru.bellintegrator.organization.dao.impl.OrganizationDAOImpl;
import ru.bellintegrator.organization.model.Organization;
import ru.bellintegrator.organization.service.OrganizationService;
import ru.bellintegrator.organization.service.impl.OrganizationServiceImpl;
import ru.bellintegrator.organization.view.OrganizationView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class OfficeServiceImpl implements OfficeService{
    private final Logger log = LoggerFactory.getLogger(OfficeServiceImpl.class);

    private final OfficeDAO dao;

    @Autowired
    public OfficeServiceImpl(OfficeDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public OfficeView add(OfficeView view) {
        Office office = new Office(view.name, view.address, view.phone, dao.findOrgById(view.orgId), view.isActive);
        OfficeView o = new OfficeView();
        o.id = dao.save(office);
        return o;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OfficeView> office() {
        List<Office> all = dao.all();

        Function<Office, OfficeView> mapOffices = p -> {
            OfficeView view = new OfficeView();
            view.id = p.getId();
            view.name = p.getName();
            view.phone = p.getPhone();
            view.address = p.getAddress();
            view.isActive = p.getIsActive();

            log.info(view.toString());

            return view;
        };

        return all.stream()
                .map(mapOffices)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OfficeView find(Long id) {
        Office o = dao.loadById(id);

        OfficeView view = new OfficeView();
        view.id = o.getId();
        view.name = o.getName();
        view.isActive = o.getIsActive();
        view.address = o.getAddress();
        view.phone = o.getPhone();
        view.orgId = o.getOrganization().getId();
        view.organizationName = o.getOrganization().getName();
        return view;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OfficeView> list(OfficeView office) {

        List<Office> all = dao.filter(office.orgId, office.name, office.phone,office.isActive);

        Function<Office, OfficeView> mapEmploye = p -> {
            OfficeView view = new OfficeView();
            view.id = p.getId();
            view.name = p.getName();
            view.organizationName = p.getOrganization().getName();
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
    public void update(OfficeView office) {
        dao.update(office.id, office.name, office.address, office.phone, office.isActive);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        dao.delete(id);
    }
}
