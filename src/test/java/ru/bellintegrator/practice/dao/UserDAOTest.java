package ru.bellintegrator.practice.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.ApplicationCatalog;
import ru.bellintegrator.offices.model.Office;
import ru.bellintegrator.user.dao.UserDAO;
import ru.bellintegrator.user.model.User;
import ru.bellintegrator.user.service.HashService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApplicationCatalog.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext

public class UserDAOTest {
    @Autowired
    private UserDAO dao;
    private HashService hash;

    private User user;
    private Office office;



    @Before
    public void initTest() {
        user = new User();

        user.setName("Пользователь");
        user.setLogin("User_123");
        user.setPassword("0101010101");
        user.setEmail("010101010@mail.ru");
        user.setIsActive(false);
        user.setCode(hash.getHashByCode("010101010@mail.ru"));
    }

    @Test
    public void testLoadById() {
        dao.save(user);

        Long id = user.getId();
        String name = user.getName();
        String login = user.getLogin();
        String password = user.getPassword();
        String email = user.getEmail();
        String code = user.getCode();
        Boolean isActive = user.getIsActive();

        Assert.assertNotNull(dao.loadById(id));
        Assert.assertEquals(name, dao.loadById(id).getName());
        Assert.assertEquals(login, dao.loadById(id).getLogin());
        Assert.assertEquals(password, dao.loadById(id).getPassword());
        Assert.assertEquals(email, dao.loadById(id).getEmail());
        Assert.assertEquals(code, dao.loadById(id).getCode());
    }
}
