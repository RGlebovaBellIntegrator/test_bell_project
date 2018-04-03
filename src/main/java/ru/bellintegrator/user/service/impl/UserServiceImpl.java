package ru.bellintegrator.user.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.optional.NoFoundException;
import ru.bellintegrator.user.dao.UserDAO;
import ru.bellintegrator.user.model.User;
import ru.bellintegrator.user.service.HashService;
import ru.bellintegrator.user.service.UserService;
import ru.bellintegrator.user.view.UserView;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDAO dao;

    private final HashService hashService;

    @Autowired
    public UserServiceImpl(UserDAO dao, HashService hashService) {
        this.dao = dao;
        this.hashService = hashService;
    }

    @Override
    @Transactional
    public void add(UserView view) {
        User user = new User(view.login, view.password, view.name, view.email);
        dao.save(user);
        user.setCode(hashService.getHashByCode(user.getEmail()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserView> user() {
        List<User> all = dao.all();

        Function<User, UserView> mapUsers = p -> {
            UserView view = new UserView();
            view.id = String.valueOf(p.getId());
            view.login = p.getLogin();
            view.name = p.getName();
            view.email = p.getEmail();
            view.isActive = p.getIsActive();

            log.info(view.toString());

            return view;
        };

        return all.stream()
                .map(mapUsers)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional(readOnly = true)
    public void login(UserView userView) {
        if (userView.login==null||userView.login.isEmpty())
            throw new NoFoundException("Не задан login");
        if (userView.password==null||userView.password.isEmpty())
            throw new NoFoundException("Не задан password");

        User user = dao.loadByLogin(userView.login, userView.password);
    }

    @Override
    @Transactional
    public void activation(String code) {
        if (code==null||code.isEmpty())
            throw new NoFoundException("Не задан activateCode");

        User user = dao.loadByCode(code);
        user.setIsActive(true);
    }

    @Override
    @Transactional
    public UserView getCode(Long id) {
        try {
            User user = dao.loadById(id);
            UserView u = new UserView();
            u.code = hashService.getHashByCode(user.getEmail());
            u.isActive = user.getIsActive();
            return u;
        } catch (NullPointerException ex) {
            throw new NoFoundException("Пользователь не найден", ex);
        }
    }

}
