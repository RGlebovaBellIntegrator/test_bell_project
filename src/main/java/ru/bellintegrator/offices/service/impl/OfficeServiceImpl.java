package ru.bellintegrator.offices.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.offices.dao.OfficesDAO;
import ru.bellintegrator.offices.model.Offices;
import ru.bellintegrator.offices.service.OfficeService;
import ru.bellintegrator.offices.view.OfficeView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class OfficeServiceImpl implements OfficeService{
    private final Logger log = LoggerFactory.getLogger(OfficeServiceImpl.class);

    private final OfficesDAO dao;

    @Autowired
    public OfficeServiceImpl(OfficesDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public void add(OfficeView view) {
        Offices offices = new Offices(view.name, view.address, view.phone, view.isActive);
        dao.save(offices);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OfficeView> offices() {
        List<Offices> all = dao.all();

        Function<Offices, OfficeView> mapOffices = p -> {
            OfficeView view = new OfficeView();
            view.id = String.valueOf(p.getId());
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
}
