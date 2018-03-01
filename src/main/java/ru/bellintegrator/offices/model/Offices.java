package ru.bellintegrator.offices.model;

import ru.bellintegrator.organizations.model.Organizations;
import ru.bellintegrator.users.model.Users;
import org.hibernate.annotations.Type;

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
    @Column(name = "is_active")
    private boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    private Organizations organizations;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "offices", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Users> users;


    public Offices(){

    }

    public Offices(String name, String address, String phone, boolean isActive) {
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

    public Organizations getOrganization() { return organizations; }

    public void setOrganization(Organizations organizations) {
        this.organizations = organizations;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }
}
