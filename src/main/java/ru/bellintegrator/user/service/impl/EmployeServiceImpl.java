package ru.bellintegrator.user.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.offices.service.OfficeService;
import ru.bellintegrator.optional.NoFoundException;
import ru.bellintegrator.optional.PersistException;
import ru.bellintegrator.user.dao.EmployeDAO;

import ru.bellintegrator.user.model.Employe;
import ru.bellintegrator.user.service.EmployeService;
import ru.bellintegrator.user.view.EmployeView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EmployeServiceImpl implements EmployeService {
    private final Logger log = LoggerFactory.getLogger(EmployeServiceImpl.class);

    private final EmployeDAO dao;
    private final OfficeService officeService;

    @Autowired
    public EmployeServiceImpl(EmployeDAO dao, OfficeService officeService) {
        this.dao = dao;
        this.officeService = officeService;
    }

    @Override
    @Transactional
    public EmployeView add(EmployeView view) {
        if (view.firstName == null)
            throw new NoFoundException("Не задан firstName");
        if (view.secondName == null)
            throw new NoFoundException("Не задан secondName");
        if (view.docCode == null)
            throw new NoFoundException("Не задан docCode");
        if (view.docNumber == null)
            throw new NoFoundException("Не задан docNumber");
        if (view.docDate == null)
            throw new NoFoundException("Не задан docDate");
        if (view.officeId == null)
            throw new NoFoundException("Не задан officeId");

        Employe employe = new Employe(view.firstName, view.secondName, view.middleName, view.position, view.phone,
                view.isIdentified);
        employe.setDoc(dao.findDocId(view.docCode));
        employe.setDocDate(view.docDate);
        employe.setDocNumber(view.docNumber);
        employe.setCountry(dao.findCountryId(view.citizenshipCode));
        employe.setOffice(dao.findOfficeId(view.officeId));
        dao.findOfficeId(view.officeId).addEmploye(employe);

        EmployeView e = new EmployeView();
        try {
            e.id = dao.save(employe);
            return e;
        }
        catch (Exception ex) {
            throw new PersistException("Не удалось сохранить сотрудника", ex);
        }
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
            if (p.getOffice()!=null)
                view.officeName = p.getOffice().getName();

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
        if (employeView.officeId == null)
            throw new NoFoundException("Не задан office_id");

        List<Employe> all = dao.filter(employeView);

        Function<Employe, EmployeView> mapEmploye = p -> {
            EmployeView view = new EmployeView();
            view.id = p.getId();
            view.fullName = p.getFirstName() + " " + p.getSecondName() + " " + p.getMiddlename();
            view.position = p.getStatement();
            if (p.getOffice()!=null)
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
        if (employeView.id == null)
            throw new NoFoundException("Не задан id");
        dao.update(employeView);
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
    public EmployeView find(Long id) {
        Employe p = dao.loadById(id);
        if (p==null)
            throw new NoFoundException("Сотрудник не найден");

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
        if (p.getOffice()!=null)
            view.officeId = p.getOffice().getId();

        return view;
    }
}
