package ru.bellintegrator.user.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.user.dao.EmployeDAO;
import ru.bellintegrator.user.dao.UserDAO;

import ru.bellintegrator.user.model.Employe;
import ru.bellintegrator.user.service.EmployeService;
import ru.bellintegrator.user.view.EmployeView;
import ru.bellintegrator.user.view.UserView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class EmployeServiceImpl implements EmployeService {
    private final Logger log = LoggerFactory.getLogger(EmployeServiceImpl.class);

    private final EmployeDAO dao;

    @Autowired
    public EmployeServiceImpl(EmployeDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public void add(EmployeView view) {
        Employe employe = new Employe(view.firstName, view.secondName, view.middleName, view.statement, view.phone,
                view.isIdentified);
        dao.save(employe);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeView> employe() {
        List<Employe> all = dao.all();

        Function<Employe, EmployeView> mapEmploye = p -> {
            EmployeView view = new EmployeView();
            view.id = String.valueOf(p.getId());
            view.firstName = p.getFirstName();

            log.info(view.toString());

            return view;
        };

        return all.stream()
                .map(mapEmploye)
                .collect(Collectors.toList());
    }
}
