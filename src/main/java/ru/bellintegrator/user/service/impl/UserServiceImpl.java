package ru.bellintegrator.user.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.optional.ServiceException;
import ru.bellintegrator.user.dao.UserDAO;
import ru.bellintegrator.user.model.User;
import ru.bellintegrator.user.service.UserService;
import ru.bellintegrator.user.view.UserView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDAO dao;

    @Autowired
    public UserServiceImpl(UserDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public void add(UserView view) {
        User user = new User(view.login, view.password, view.name, view.email);
        dao.save(user);
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
            view.code = p.getCode();
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
    public Boolean login(UserView userView) {
        User user = dao.loadByLogin(userView.login, userView.password);

        if (user==null){
            throw new ServiceException("Сочетание login/password не найдены");
        };

        return true;
    }

    @Override
    @Transactional
    public Boolean activation(String code) {
       return dao.updateCode(code);
    }

}
