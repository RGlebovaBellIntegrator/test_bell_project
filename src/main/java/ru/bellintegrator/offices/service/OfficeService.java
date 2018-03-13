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

    List<OfficeView> list(Long orgId, String name,String phone, Boolean isActive);

    void update(Long id, String name, String address, String phone, Boolean isActive);

    void delete(Long id);
}
