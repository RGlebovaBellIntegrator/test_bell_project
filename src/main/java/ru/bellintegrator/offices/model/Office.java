package ru.bellintegrator.offices.model;

import ru.bellintegrator.organization.model.Organization;
import ru.bellintegrator.user.model.Employe;
import ru.bellintegrator.user.model.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Office")
public class Office {
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
    private boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    private Organization organization;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "office", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Employe> employe;


    public Office(){

    }

    public Office(String name, String address, String phone, Organization organization, boolean isActive) {
        this.name=name;
        this.address=address;
        this.phone = phone;
        this.isActive = isActive;
        this.organization = organization;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {return phone;}

    public void  setPhone(String phone){this.phone=phone;}

    public boolean getIsActive() {return isActive;}

    public void  setIsActive(boolean isActive){this.isActive=isActive;}

    public Organization getOrganization() { return organization; }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public List<Employe> getEmploye() {
        return employe;
    }

    public void setEmploye(List<Employe> employe) {
        this.employe = employe;
    }
}
