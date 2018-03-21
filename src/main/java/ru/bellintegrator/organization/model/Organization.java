package ru.bellintegrator.organization.model;

import ru.bellintegrator.offices.model.Office;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Organization")
public class Organization {
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
    //@Column(name = "fullName")
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
    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Office> offices;

    public Organization(){

    }

    public Organization(String name, String fullname, String inn, String kpp, String address, String phone, Boolean isActive) {
        this.name=name;
        this.fullname = fullname;
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

    public List<Office> getOffices() {
        return offices;
    }

    public void setOffices(List<Office> offices) {
        this.offices = offices;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname){
        this.fullname = fullname;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn){
        this.inn= inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp){
        this.kpp= kpp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address){
        this.address= address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone){
        this.phone= phone;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive){
        this.isActive= isActive;
    }
}
