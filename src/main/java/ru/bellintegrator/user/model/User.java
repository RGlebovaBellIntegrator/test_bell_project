package ru.bellintegrator.user.model;

import org.hibernate.id.GUIDGenerator;

import javax.persistence.*;
import java.util.UUID;

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

    @Basic
    private String email;

    @Basic(optional = false)
    @Column(name = "is_active")
    private boolean isActive;

    @Basic(optional = false)
    @Column(name = "code")
    private String code;

    public User(){

    }

    public User(String login, String password, String name, String email) {
        this.login=login;
        this.password=password;
        this.name=name;
        this.email=email;
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


    public String getEmail() {
        return email;
    }
    public void  setEmail(String email) {this.email = email;}

    public boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(boolean isActive) {this.isActive = isActive;}


    public String getCode() {return code;}
    public void  setCode(String code) {this.code = code;}

}
