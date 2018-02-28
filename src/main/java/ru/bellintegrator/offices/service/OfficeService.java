package ru.bellintegrator.offices.service;

import ru.bellintegrator.offices.view.OfficeView;

import java.util.List;

public interface OfficeService {

    /**
     *
     * @param offices
     */
    void add(OfficeView offices);

    /**
     * Office service method
     * @return {@Offices}
     */
    List<OfficeView> offices();
}
