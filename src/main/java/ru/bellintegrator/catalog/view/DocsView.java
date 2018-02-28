package ru.bellintegrator.catalog.view;

import io.swagger.annotations.ApiModelProperty;

import javax.print.Doc;

public class DocsView {
    @ApiModelProperty(hidden = true)
    public String code;

    public String name;

    public DocsView() {

    }

    public DocsView(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
