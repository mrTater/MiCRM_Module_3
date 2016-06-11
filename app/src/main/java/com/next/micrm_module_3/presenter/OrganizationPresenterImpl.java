package com.next.micrm_module_3.presenter;

import com.next.micrm_module_3.model.Organization;
import com.next.micrm_module_3.presenter.interfaces.OrganizationInteractor;
import com.next.micrm_module_3.presenter.interfaces.OrganizationPresenter;
import com.next.micrm_module_3.view.interfaces.OrganizationFragmentView;

/**
 * Created by Wally1 on 11/06/2016.
 */
public class OrganizationPresenterImpl implements OrganizationPresenter, OrganizationInteractor.onOrganization {
    OrganizationFragmentView mView;
    OrganizationInteractor mInteractor;
    public OrganizationPresenterImpl(OrganizationFragmentView view) {
        mView = view;
        mInteractor = new OrganizationInteractorImpl();
    }

    @Override
    public void addOrganization(String name, String tel, String addres) {
       if(mView != null)
            mInteractor.createOrganization(name,tel,addres,this);
    }

    @Override
    public Organization getChangeOrganization(int i) {
        return mInteractor.getOrganiation(i);
    }

    @Override
    public void onDestroy() {
        mView = null;
    }

    @Override
    public void onDelete(int i) {
        mInteractor.deleteOrganization(i);
    }

    @Override
    public void onErrorName() {
        if(mView !=null){
            mView.setErrorName();
        }
    }

    @Override
    public void onErrorTel() {
        if(mView !=null){
            mView.setErrorTel();

        }
    }

    @Override
    public void onErrorAddres() {
        if(mView !=null){
            mView.setErrorAddres();
        }
    }
}
