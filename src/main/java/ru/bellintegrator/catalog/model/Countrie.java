package ru.bellintegrator.catalog.model;

import ru.bellintegrator.user.model.Employe;
import ru.bellintegrator.user.model.User;

import javax.persistence.*;
import java.util.List;

/*
* Спарвочник документов
*/
@Entity
@Table(name = "Countrie")
public class Countrie {

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
    private Integer code;
    /**
     * Иия
     */
    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "countrie", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Employe> employe;

    public Countrie(){

    }

    public Countrie(Integer code, String name){
        this.code=code;
        this.name=name;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Integer getCode() {
        return code;
    }

}
