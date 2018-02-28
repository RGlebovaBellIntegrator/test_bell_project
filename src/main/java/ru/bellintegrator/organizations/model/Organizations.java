package ru.bellintegrator.organizations.model;

import ru.bellintegrator.offices.model.Offices;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Organizations")
public class Organizations {
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
    //@Column(name = "name")
    private String name;

    /**
     * Полное иия
     */
    @Basic(optional = false)
    //@Column(name = "fullname")
    private String fullname;

    /**
     * ИНН
     */
    @Basic(optional = false)
    //@Column(name = "inn")
    private String inn;

    /**
     * КПП
     */
    @Basic(optional = false)
    //@Column(name = "kpp")
    private String kpp;

    /**
     * Адрес
     */
    @Basic(optional = false)
    //@Column(name = "address")
    private String address;

    /**
     * Телефон
     */
    @Basic
    //@Column(name = "phone")
    private String phone;

    @Basic(optional = false)
    //@Column(name = "isActive")
    private byte isActive;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "offices", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Offices> offices;

    public Organizations(){

    }

    public Organizations(String name, String fullname, String inn, String kpp, String address, String phone, byte isActive) {
        this.name=name;
        this.fullname=fullname;
        this.inn=inn;
        this.kpp=kpp;
        this.address=address;
        this.phone = phone;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Offices> getOffices() {
        return offices;
    }

    public void setOffices(List<Offices> offices) {
        this.offices = offices;
    }

}
