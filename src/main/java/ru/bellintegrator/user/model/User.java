package ru.bellintegrator.user.model;

import ru.bellintegrator.catalog.model.Countrie;
import ru.bellintegrator.catalog.model.Doc;
import ru.bellintegrator.offices.model.Office;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue
    //@Column(name = "Id")
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * Логин
     */
    @Basic
    private String login;

    /**
     * Пароль
     */
    @Basic
    private String password;

    @OneToOne(
            mappedBy="user",
            fetch = FetchType.LAZY,
            cascade=CascadeType.ALL,
            optional=false
    )
    private Employe employe;

    public User(){

    }

    public User(String login, String password) {
        this.login=login;
        this.password=password;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }
    public void  setLogin(String login) {this.login = login;}
    public String getPassword() {
        return password;
    }
    public void  setPassword(String password) {this.password = password;}


    public Employe getEmploye() {
        return employe;
    }
    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

}
