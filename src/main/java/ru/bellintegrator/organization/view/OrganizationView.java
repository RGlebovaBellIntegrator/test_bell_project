package ru.bellintegrator.organization.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrganizationView {
    @ApiModelProperty(hidden = true)
    public String id;

    public String name;

    public String fullname;

    public String inn;
    public String kpp;

    public String address;
    public String phone;

    public Boolean isActive;

    public OrganizationView() {

    }

    public OrganizationView(String name, String fullname, String inn, String kpp, String address, String phone, Boolean isActive) {
        this.name=name;
        this.fullname=fullname;
        this.inn=inn;
        this.kpp=kpp;
        this.address=address;
        this.phone = phone;
        this.isActive = isActive;
    }
}
