package com.next.micrm_module_3.presenter;

import android.text.TextUtils;

import com.next.micrm_module_3.model.People;
import com.next.micrm_module_3.model.interfaces.ModelInteractor;
import com.next.micrm_module_3.presenter.interfaces.PeopleInteractor;

/**
 * This class is el interactor to PeoplePresenter to Model
 */
public class PeopleInteractorImpl implements PeopleInteractor {
    ModelInteractor mModel = ModelPresenter.getInstances();

    @Override
    public boolean createPeople(String name, String email, String tel, onPeople listener) {
        boolean error = false;
        if (TextUtils.isEmpty(name)){
            listener.onErrorName();
            error = true;
        }
        if (TextUtils.isEmpty(email)){
            listener.onErrorEmail();
            error = true;
        }
        if (TextUtils.isEmpty(tel)){
            listener.onErrorTel();
            error = true;
        }
        if (!error){
            People p = new People();
            p.setName(name);
            p.setEmail(email);
            p.setTel(tel);
            mModel.setPeople(p);

        }
        listener.onCreate(!error);
        return error;

    }

    @Override
    public People getChangePeople(int i) {
        return mModel.getPeople(i);
    }

    @Override
    public void deletePoeple(int i) {
        mModel.getPeoples().remove(i);
    }


}
