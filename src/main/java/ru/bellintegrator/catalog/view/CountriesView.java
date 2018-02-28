package ru.bellintegrator.catalog.view;

import io.swagger.annotations.ApiModelProperty;

import javax.print.Doc;

public class CountriesView {
    @ApiModelProperty(hidden = true)
    public String code;

    public String name;

    public CountriesView() {

    }

    public CountriesView(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
