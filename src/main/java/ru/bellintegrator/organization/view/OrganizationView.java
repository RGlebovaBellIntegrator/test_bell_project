package ru.bellintegrator.organization.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrganizationView {
    @ApiModelProperty(hidden = true)
    public Long id;

    public String name;

    public String fullName;

    public String inn;
    public String kpp;

    public String address;
    public String phone;

    public Boolean isActive;

    public OrganizationView() {

    }

    public OrganizationView(String name, String fullname, String inn, String kpp, String address, String phone, Boolean isActive) {
        this.name=name;
        this.fullName =fullname;
        this.inn=inn;
        this.kpp=kpp;
        this.address=address;
        this.phone = phone;
        this.isActive = isActive;
    }
}
