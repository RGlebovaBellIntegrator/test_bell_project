package ru.bellintegrator.users.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.users.dao.UsersDAO;
import ru.bellintegrator.users.model.Users;
import ru.bellintegrator.users.service.UsersService;
import ru.bellintegrator.users.view.UsersView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class UsersServiceImpl implements UsersService{
    private final Logger log = LoggerFactory.getLogger(UsersServiceImpl.class);

    private final UsersDAO dao;

    @Autowired
    public UsersServiceImpl(UsersDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public void add(UsersView view) {
        Users users = new Users(view.firstName, view.secondName, view.middleName, view.login, view.password, view.statement, view.phone,
                view.isIdentified);
        dao.save(users);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsersView> users() {
        List<Users> all = dao.all();

        Function<Users, UsersView> mapUsers = p -> {
            UsersView view = new UsersView();
            view.id = String.valueOf(p.getId());
            view.firstName = p.getFirstName();

            log.info(view.toString());

            return view;
        };

        return all.stream()
                .map(mapUsers)
                .collect(Collectors.toList());
    }
}
