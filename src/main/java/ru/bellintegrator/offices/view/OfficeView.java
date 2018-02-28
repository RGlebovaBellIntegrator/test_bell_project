package ru.bellintegrator.offices.view;

import io.swagger.annotations.ApiModelProperty;

public class OfficeView {
    @ApiModelProperty(hidden = true)
    public String id;

    public String name;

    public String address;
    public String phone;

    public byte isActive;

    public OfficeView() {

    }

    public OfficeView(String name, String address, String phone, byte isActive) {
        this.name=name;
        this.address=address;
        this.phone = phone;
        this.isActive = isActive;
    }
}
