package ru.bellintegrator.user.service.impl;

import org.hibernate.service.spi.InjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.offices.service.OfficeService;
import ru.bellintegrator.user.dao.EmployeDAO;

import ru.bellintegrator.user.model.Employe;
import ru.bellintegrator.user.service.EmployeService;
import ru.bellintegrator.user.view.EmployeView;

import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EmployeServiceImpl implements EmployeService {
    private final Logger log = LoggerFactory.getLogger(EmployeServiceImpl.class);

    private final EmployeDAO dao;

    @Autowired
    public EmployeServiceImpl(EmployeDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public EmployeView add(EmployeView view) {
        Employe employe = new Employe(view.firstName, view.secondName, view.middleName, view.position, view.phone,
                view.isIdentified);
        employe.setDoc(dao.findDocId(view.docCode));
        employe.setDocDate(view.docDate);
        employe.setDocNumber(view.docNumber);
        employe.setCountry(dao.findCountryId(view.citizenshipCode));
        employe.setOffice(dao.findOfficeById(view.officeId));

        EmployeView e = new EmployeView();
        e.id = dao.save(employe);
        return e;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeView> employe() {
        List<Employe> all = dao.all();

        Function<Employe, EmployeView> mapEmploye = p -> {
            EmployeView view = new EmployeView();
            view.id = p.getId();
            view.firstName = p.getFirstName();
            view.secondName = p.getSecondName();
            view.middleName = p.getMiddlename();
            view.position = p.getStatement();
            view.phone = p.getPhone();
            view.docCode = p.getDoc().getCode();
            view.citizenshipCode = p.getCountry().getCode();

            log.info(view.toString());

            return view;
        };

        return all.stream()
                .map(mapEmploye)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeView> employeFilter(EmployeView employeView) {

        List<Employe> all = dao.filter(employeView);

        Function<Employe, EmployeView> mapEmploye = p -> {
            EmployeView view = new EmployeView();
            view.id = p.getId();
            view.fullName = p.getFirstName() + " " + p.getSecondName() + " " + p.getMiddlename();
            view.position = p.getStatement();
            view.officeName = p.getOffice().getName();

            log.info(view.toString());

            return view;
        };

        return all.stream()
                .map(mapEmploye)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void update(EmployeView employeView) {
        dao.update(employeView);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    @Transactional
    public EmployeView find(Long id) {
        Employe p = dao.loadById(id);

        EmployeView view = new EmployeView();
        view.id = p.getId();
        view.firstName = p.getFirstName();
        view.secondName = p.getSecondName();
        view.middleName = p.getMiddlename();
        view.position = p.getStatement();
        view.phone = p.getPhone();
        view.docCode = p.getDoc().getCode();
        view.docName = p.getDoc().getName();
        view.docNumber = p.getDocNumber();
        view.docDate = p.getDocDate();
        view.citizenshipCode = p.getCountry().getCode();
        view.citizenshipName = p.getCountry().getName();
        view.officeId = p.getOffice().getId();

        return  view;
    }
}
