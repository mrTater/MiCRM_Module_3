package com.next.micrm_module_3.view.interfaces;

/**
 * Created by Wally1 on 11/06/2016.
 */
public interface OrganizationCreateFragmentView {
    void setErrorName();
    void setErrorAddres();
    void setErrorTel();
    void actionCancel();
    void actionOk();
    void notificationCreate(boolean s);
}
