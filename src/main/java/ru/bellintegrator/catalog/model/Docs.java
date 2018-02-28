package ru.bellintegrator.catalog.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.print.Doc;

/*
* Спарвочник документов
*/
@Entity
@Table(name = "Docs")
public class Docs {

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

    public Docs(){

    }

    public Docs(Integer code, String name){
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
