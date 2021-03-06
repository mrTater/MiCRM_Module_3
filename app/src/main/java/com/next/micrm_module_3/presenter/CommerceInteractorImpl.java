package com.next.micrm_module_3.presenter;

import android.text.TextUtils;

import com.next.micrm_module_3.model.Commerce;
import com.next.micrm_module_3.model.Organization;
import com.next.micrm_module_3.model.People;
import com.next.micrm_module_3.model.interfaces.Entidad;
import com.next.micrm_module_3.model.interfaces.ModelInteractor;
import com.next.micrm_module_3.presenter.interfaces.CommerceInteractor;

/**
 * This class is el interactor to CommercePresenter to Model
 */
public class CommerceInteractorImpl implements CommerceInteractor {
    ModelInteractor mModel = ModelPresenter.getInstances();
    @Override
    public boolean createCommerce(String t, String d, double v, String s, String date, onCommerce listener, People pe, Organization or) {
        boolean error = false;
        if (TextUtils.isEmpty(t)){
            listener.onErrorTitle();
            error = true;
        }
        if (TextUtils.isEmpty(d)){
            listener.onErrorDescription();
            error = true;
        }
        if (v == 0){
            listener.onErrorValue();
            error = true;
        }
        if (TextUtils.isEmpty(s)){
            listener.onErrorStatus();
            error = true;
        }
        if (TextUtils.isEmpty(date)){
            listener.onErrorDate();
            error = true;
        }
        if (pe == null || or == null ){
            listener.onErrorAsign();
            error = true;
        }

        if (!error){
            Commerce come = new Commerce();
            come.setTitle(t);
            come.setDescription(d);
            come.setDateFinish(date);
            come.setStatus(s);
            come.setValue(v);
            if(pe != null)
                come.setPeoples(pe);
            if(or != null)
                come.setOrg(or);
            mModel.setCommerce(come);
        }
        listener.onCreate(!error);
        return error;
    }

    @Override
    public Entidad getCommerceChange(int i) {
        return mModel.getOrganization(i);
    }

    @Override
    public void delteCommerce(int i) {
        mModel.getCommerces().remove(i);
    }


}
