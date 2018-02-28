package ru.bellintegrator.offices.model;

import ru.bellintegrator.organizations.model.Organizations;
import ru.bellintegrator.users.model.Users;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Offices")
public class Offices {
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
    //@Column(name = "isActive")
    private byte isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordId")
    private Organizations organizations;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "users", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Users> users;


    public Offices(){

    }

    public Offices(String name, String address, String phone, byte isActive) {
        this.name=name;
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

    public Organizations getOrganization() { return organizations; }

    public void setOrganization(Organizations organizations) {
        this.organizations = organizations;
    }
}
