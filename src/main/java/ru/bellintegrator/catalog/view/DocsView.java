package ru.bellintegrator.catalog.view;

import io.swagger.annotations.ApiModelProperty;

import javax.print.Doc;

public class DocsView {
    @ApiModelProperty(hidden = true)
    public int code;

    public String name;

    public DocsView() {

    }

    public DocsView(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
