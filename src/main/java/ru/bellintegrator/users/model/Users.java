package ru.bellintegrator.users.model;

import ru.bellintegrator.catalog.model.Countries;
import ru.bellintegrator.catalog.model.Docs;
import ru.bellintegrator.offices.model.Offices;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class Users {

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
     * Иия
     */
    @Basic(optional = false)
    //@Column(name = "firstname")
    private String firstname;

    @Basic(optional = false)
    //@Column(name = "secondtname")
    private String secondtname;

    @Basic
    //@Column(name = "middleName")
    private String middleName;

    /**
     * Логин
     */
    @Basic
    //@Column(name = "login")
    private String login;

    /**
     * Пароль
     */
    @Basic
    //@Column(name = "password")
    private String password;

    /**
     * Должность
     */
    @Basic(optional = false)
    @Column(name = "position_statement")
    private String statement;

    /**
     * Телефон
     */
    @Basic
    //@Column(name = "phone")
    private String phone;

    @Basic(optional = false)
    //@Column(name = "isIdentified")
    private byte isIdentified;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "officeId")
    private Offices offices;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "docCode")
    private Docs docs;

    /**
     * Данные по документу
     */
    @Basic(optional = false)
    //@Column(name = "docNumber")
    private String docNumber;

    /**
     * Дата документа
     */
    @Basic(optional = false)
    //@Column(name = "docDate")
    private Date docDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "citizenshipCode")
    private Countries countries;

}
