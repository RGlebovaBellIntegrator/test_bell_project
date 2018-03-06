package ru.bellintegrator.offices.service.impl;

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
    public void add(OfficeView view) {
        Office office = new Office(view.name, view.address, view.phone, view.isActive);
        dao.save(office);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OfficeView> office() {
        List<Office> all = dao.all();

        Function<Office, OfficeView> mapOffices = p -> {
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
