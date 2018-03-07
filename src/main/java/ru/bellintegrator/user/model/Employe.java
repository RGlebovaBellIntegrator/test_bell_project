package ru.bellintegrator.user.model;

import ru.bellintegrator.catalog.model.Country;
import ru.bellintegrator.catalog.model.Doc;
import ru.bellintegrator.offices.model.Office;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Employe")
public class Employe {

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
    private String middlename;


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
    private Boolean isIdentified;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id")
    private Office office;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_id")
    private Doc doc;

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
    @JoinColumn(name = "countrie_id")
    private Country country;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Employe(){

    }

    public Employe(String firstname, String secondname, String middlename, String statement, String phone,
                   Boolean isIdentified) {
        this.firstname=firstname;
        this.secondname = secondname;
        this.middlename = middlename;
        this.statement = statement;
        this.phone = phone;
        this.isIdentified = isIdentified;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return String.format("Имя: %s Фамилия: %s Отчество: %s", firstname, secondname, middlename);
    }

    public String getFirstName() {
        return firstname;
    }
    public void  setFirstname(String firstname) {this.firstname = firstname;}
    public String getSecondName() {
        return secondname;
    }
    public void setSecondname(String secondname) {this.secondname = secondname;}
    public String getMiddlename() {
        return middlename;
    }
    public void setMiddlename(String middlename) {this.middlename = middlename;}

    public String getStatement() {
        return statement;
    }
    public void  setStatement(String statement) {this.statement = statement;}

    public String getPhone() {return phone;}
    public void  setPhone(String phone){this.phone=phone;}

    public Boolean getIsIdentified() {return isIdentified;}

    public void  setIsIdentified(Boolean isIdentified){this.isIdentified=isIdentified;}

    public Office getOffice() {
        return office;
    }
    public void setOffice(Office office) {
        this.office = office;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

}
