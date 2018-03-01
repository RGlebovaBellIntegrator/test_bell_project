package ru.bellintegrator.users.model;

import ru.bellintegrator.catalog.model.Countries;
import ru.bellintegrator.catalog.model.Docs;
import ru.bellintegrator.offices.model.Offices;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Users")
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
    @Column(name = "firstname")
    private String firstname;

    @Basic(optional = false)
    @Column(name = "secondname")
    private String secondname;

    @Basic
    @Column(name = "middlename")
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
    @Column(name = "is_identified")
    private byte isIdentified;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id")
    private Offices offices;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_code")
    private Docs docs;

    /**
     * Данные по документу
     */
    @Basic(optional = false)
    @Column(name = "doc_number")
    private String docNumber;

    /**
     * Дата документа
     */
    @Basic(optional = false)
    @Column(name = "doc_date")
    @Temporal(TemporalType.DATE)
    private Date docDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "countries_code")
    private Countries countries;

    public Users(){

    }

    public Users(String firstname, String secondname, String middleName, String login, String password, String statement, String phone,
                 byte isIdentified) {
        this.firstname=firstname;
        this.secondname = secondname;
        this.middleName=middleName;
        this.login=login;
        this.password=password;
        this.statement = statement;
        this.phone = phone;
        this.isIdentified = isIdentified;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return String.format("Имя: %s Фамилия: %s Отчество: %s", firstname, secondname, middleName);
    }

    public String getFirstName() {
        return firstname;
    }
    public void  setFirstname(String firstname) {this.firstname = firstname;}
    public String getSecondName() {
        return secondname;
    }
    public void setSecondname(String secondname) {this.secondname = secondname;}
    public String getMiddleName() {
        return middleName;
    }
    public void  setMiddleName(String middleName) {this.middleName = middleName;}

    public String getLogin() {
        return login;
    }
    public void  setLogin(String login) {this.login = login;}
    public String getPassword() {
        return password;
    }
    public void  setPassword(String password) {this.password = password;}

    public String getStatement() {
        return statement;
    }
    public void  setStatement(String statement) {this.statement = statement;}

    public String getPhone() {return phone;}
    public void  setPhone(String phone){this.phone=phone;}

    public byte getIsIdentified() {return isIdentified;}

    public void  setIsIdentified(byte isIdentified){this.isIdentified=isIdentified;}

    public Offices getOffice() {
        return offices;
    }
    public void setOffice(Offices offices) {
        this.offices = offices;
    }

}
