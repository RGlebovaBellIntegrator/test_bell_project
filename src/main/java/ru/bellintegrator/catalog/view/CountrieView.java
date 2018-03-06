package ru.bellintegrator.catalog.view;

import io.swagger.annotations.ApiModelProperty;

import javax.print.Doc;

public class CountrieView {
    @ApiModelProperty(hidden = true)
    public int id;

    public int code;

    public String name;

    public CountrieView() {

    }

    public CountrieView(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
