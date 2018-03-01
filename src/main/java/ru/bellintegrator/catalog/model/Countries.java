package ru.bellintegrator.catalog.model;

import ru.bellintegrator.users.model.Users;

import javax.persistence.*;
import javax.print.Doc;
import java.util.List;

/*
* Спарвочник документов
*/
@Entity
@Table(name = "Countries")
public class Countries {

    @Id
    @Column(name = "code")
    private Integer code;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * Иия
     */
    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "countries", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Users> users;

    public Countries(){

    }

    public Countries(Integer code, String name){
        this.code=code;
        this.name=name;
    }

    public String getName() {
        return name;
    }
    public Integer getCode() {
        return code;
    }

}
