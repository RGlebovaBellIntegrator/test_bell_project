package ru.bellintegrator.offices.service;

import ru.bellintegrator.offices.view.OfficeView;

import java.util.List;

public interface OfficeService {

    /**
     *
     * @param office
     */
    void add(OfficeView office);

    /**
     * Office service method
     * @return {@Office}
     */
    List<OfficeView> office();
}
