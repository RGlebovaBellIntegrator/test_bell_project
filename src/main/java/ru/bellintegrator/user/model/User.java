package ru.bellintegrator.user.model;

import javax.persistence.*;

@Entity
@Table(name = "User")
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

    @Basic
    private String name;

    @Basic(optional = false)
    @Column(name = "is_active")
    private boolean isActive;


    public User(){

    }

    public User(String login, String password, String name) {
        this.login=login;
        this.password=password;
        this.name=name;
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

    public String getName() {
        return name;
    }
    public void  setName(String name) {this.name = name;}

    public boolean getIsActive() {
        return isActive;
    }
    public void  setIsActiv(boolean isActive) {this.isActive = isActive;}

}
