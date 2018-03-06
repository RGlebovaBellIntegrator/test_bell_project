package ru.bellintegrator.catalog.view;

import io.swagger.annotations.ApiModelProperty;

import javax.print.Doc;

public class DocView {
    @ApiModelProperty(hidden = true)
    public int id;

    public int code;

    public String name;

    public DocView() {

    }

    public DocView(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
