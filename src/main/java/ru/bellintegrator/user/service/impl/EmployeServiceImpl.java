package ru.bellintegrator.user.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.user.dao.EmployeDAO;

import ru.bellintegrator.user.model.Employe;
import ru.bellintegrator.user.service.EmployeService;
import ru.bellintegrator.user.view.EmployeView;

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
        Employe employe = new Employe(view.firstName, view.secondName, view.middleName, view.position, view.phone,
                view.isIdentified);
        employe.setDoc(dao.findDocId(view.docCode));
        employe.setDocDate(view.docDate);
        employe.setDocNumber(view.docNumber);
        employe.setCountry(dao.findCountryId(view.countryCode));
        employe.setOffice(dao.findOfficeById(view.officeId));
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
            view.secondName = p.getSecondName();
            view.middleName = p.getMiddlename();
            view.position = p.getStatement();
            view.phone = p.getPhone();
            view.docCode = p.getDoc().getCode();
            view.countryCode = p.getCountry().getCode();

            log.info(view.toString());

            return view;
        };

        return all.stream()
                .map(mapEmploye)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeView> employeFilter(String firstname, String secondname, String middlename,
                                               String position, String doc_code, String office_id, String country_code) {

        List<Employe> all = dao.filter(firstname,secondname,middlename,position,doc_code,office_id,country_code);

        Function<Employe, EmployeView> mapEmploye = p -> {
            EmployeView view = new EmployeView();
            view.id = String.valueOf(p.getId());
            view.firstName = p.getFirstName();
            view.secondName = p.getSecondName();
            view.middleName = p.getMiddlename();
            view.position = p.getStatement();

            log.info(view.toString());

            return view;
        };

        return all.stream()
                .map(mapEmploye)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void update(Long id, String firstname, String secondname, String middlename,
                       String position, String phone, String doc_name, String doc_number,
                       String doc_date, String country_name, String country_code, Boolean isIdentified) {
        dao.update(id, firstname,secondname,middlename,position, phone, doc_name, doc_number,doc_date,country_name,country_code,isIdentified);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        dao.delete(id);
    }
}
