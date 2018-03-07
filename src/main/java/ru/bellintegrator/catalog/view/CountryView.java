package ru.bellintegrator.catalog.view;

import io.swagger.annotations.ApiModelProperty;

import javax.print.Doc;

public class CountryView {
    @ApiModelProperty(hidden = true)
    public int id;

    public int code;

    public String name;

    public CountryView() {

    }

    public CountryView(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
