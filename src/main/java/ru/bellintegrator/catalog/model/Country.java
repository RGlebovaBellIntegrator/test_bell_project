package ru.bellintegrator.catalog.model;

import ru.bellintegrator.user.model.Employe;
import ru.bellintegrator.user.model.User;

import javax.persistence.*;
import java.util.List;

/*
* Спарвочник документов
*/
@Entity
@Table(name = "Country")
public class Country {

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    @Basic(optional = false)
    @Column(name = "code")
    private String code;
    /**
     * Иия
     */
    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country", cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE})
    private List<Employe> employe;

    public Country(){

    }

    public Country(String code, String name){
        this.code=code;
        this.name=name;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getCode() {
        return code;
    }

}
