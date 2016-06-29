package com.next.micrm_module_3.presenter;

import android.text.TextUtils;

import com.next.micrm_module_3.model.ActivityAction;
import com.next.micrm_module_3.model.interfaces.Entidad;
import com.next.micrm_module_3.model.interfaces.ModelInteractor;
import com.next.micrm_module_3.presenter.interfaces.ActivityInteractor;

/**
 * Created by wcamaly on 29/06/2016.
 */
public class ActivityInteractorImpl implements ActivityInteractor {
    ModelInteractor mModel = ModelPresenter.getInstances();

    @Override
    public void createNewActivity(String t, String d, Entidad entidad, String date, String hour, onActivityAction listener) {
        boolean error = false;
        if(TextUtils.isEmpty(t)){
            listener.onErrorType();
            error= true;
        }
        if(TextUtils.isEmpty(d)){
            listener.onErrorDescription();
            error = true;

        }
        if(entidad != null){
            listener.onErrorAsign();
            error = true;
        }
        if(TextUtils.isEmpty(date)){
            listener.onErrorDate();
            error = true;
        }
        if(TextUtils.isEmpty(hour)){
            listener.onErrorHour();
            error = true;
        }
        if(!error){
            ActivityAction a = new ActivityAction();
            a.setType(t);
            a.setDescription(d);
            a.setDate(date);
            a.setHour(hour);
            a.asignActivity(entidad);
            mModel.setActivity(a);
        }

    }

    @Override
    public ActivityAction getActivityChange(int i) {
        return mModel.getActivityAction(i);
    }

    @Override
    public void deleteActivity(int i) {
        mModel.deleteActivity(i);

    }
}
