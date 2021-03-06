package com.next.micrm_module_3.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.next.micrm_module_3.R;
import com.next.micrm_module_3.constant.ConstantGeneral;
import com.next.micrm_module_3.model.Commerce;
import com.next.micrm_module_3.model.Organization;
import com.next.micrm_module_3.model.People;
import com.next.micrm_module_3.presenter.CommercePresenterImpl;
import com.next.micrm_module_3.presenter.interfaces.CommercePresenter;
import com.next.micrm_module_3.view.interfaces.CommerceCreateFragmentView;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the create new Commerce, and add to list
 */
public class FragmentCommerceCreate extends Fragment implements CommerceCreateFragmentView, View.OnClickListener{

    Button cPeople,cOrganization,ok,cancel;
    Spinner lPeoples,lOrganization;
    EditText tTitle,tDescription,tValue,tDate,tStatus;
    CommercePresenter pCommerce;
    People selectPeople = null;
    Organization selectOrganization = null;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_commerce_create, container,false);
        pCommerce = new CommercePresenterImpl(this);
        initializeView(rootView);
        setOnListener();
        evalAsign();



        return rootView;
    }

    @Override
    public void onDestroy() {
        pCommerce.onDestroy();        
        super.onDestroy();
    }

    @Override
    public void setErrorTitle() {
        tTitle.setError(getActivity().getString(R.string.error_title));
    }

    @Override
    public void setErrorDescription() {
        tDescription.setError(getActivity().getString(R.string.error_description));
    }

    @Override
    public void setErrorDate() {
        tDate.setError(getActivity().getString(R.string.error_date));
    }

    @Override
    public void setErrorStatus() {
        tStatus.setError(getActivity().getString(R.string.error_status));
    }

    @Override
    public void setErrorValue() {
        tValue.setError(getActivity().getString(R.string.error_value));
    }

    @Override
    public void setErrorAsign() {
        Toast.makeText(getActivity(),R.string.error_asign,Toast.LENGTH_LONG).show();
    }

    @Override
    public void evalAsign() {
       if(pCommerce.getListPoples() != null)
           loadAsign(lPeoples,pCommerce.getListPoples(),1);
       else{
           lPeoples.setVisibility(View.GONE);
           cPeople.setVisibility(View.VISIBLE);
       }
        if(pCommerce.getListOrganization() != null)
            loadAsign(lOrganization,pCommerce.getListOrganization(),2);
        else{
            lOrganization.setVisibility(View.GONE);
            cOrganization.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void loadAsign(Spinner v, List<?> list, int as) {
        List<String> l = new ArrayList<String>();
        for(int i=0;i< list.size();i++){
            if(as == 1)
                l.add(((People) list.get(i)).getName());
            else
                l.add(((Organization) list.get(i)).getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,l);
        v.setAdapter(adapter);
    }

    @Override
    public void actionCreatePeople() {
        FragmentTransaction fragmentTransaction=  getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_section, new FragmentPeopleCreate()).commit();
    }

    @Override
    public void actionCreateOrganization() {
        FragmentTransaction fragmentTransaction=  getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_section, new FragmentCommerceCreate()).commit();
    }

    @Override
    public void actionCancel() {
        backFragment();
    }

    @Override
    public void actionOk() {
        Double aux= new Double(0);
        if (!tValue.getText().toString().equals(""))
            aux=Double.parseDouble(tValue.getText().toString());

        if(! pCommerce.addCommerce(tTitle.getText().toString(),
                tDescription.getText().toString(),
                aux,
                tStatus.getText().toString(),
                tDate.getText().toString(),
                selectPeople,
                selectOrganization))
            backFragment();
    }

    @Override
    public void change(int i) {
        Commerce c = pCommerce.getCommerceChange(i);
        tTitle.setText(c.getTitle());
        tDescription.setText(c.getDescription());
        tValue.setText(Double.toString(c.getValue()));
        tStatus.setText(c.getStatus());
        tDate.setText(c.getDateFinish());


    }

    @Override
    public void notificationCreate(boolean s) {
        if(s)
            Toast.makeText(getActivity(),"Se a creado Correctamente", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getActivity(),"No se pudo Crear Correctamente", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        //Lineas para ocultar el teclado virtual (Hide keyboard)
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(tStatus.getWindowToken(), 0);
        switch (v.getId()){
            case R.id.okCommerce:
                actionOk();
                break;
            case R.id.cancelCommerce:
                actionCancel();
                break;
            case R.id.createPeople:
                actionCreatePeople();
                break;
            case R.id.createOrganization:
                actionCreateOrganization();
                break;
        }

    }


    private void initializeView(View rootView) {
        lPeoples = (Spinner) rootView.findViewById(R.id.spinnerPeople);

        lOrganization = (Spinner) rootView.findViewById(R.id.spinnerOrganization);
        tTitle = (EditText) rootView.findViewById(R.id.titleCommerce);
        tDescription = (EditText) rootView.findViewById(R.id.descriptionCommerce);
        tValue = (EditText) rootView.findViewById(R.id.valorCommerce);
        tDate = (EditText) rootView.findViewById(R.id.dateFinishCommerce);
        tStatus = (EditText) rootView.findViewById(R.id.statusCommerce);
        cPeople = (Button) rootView.findViewById(R.id.createPeople);
        cOrganization = (Button) rootView.findViewById(R.id.createOrganization);
        cancel= (Button) rootView.findViewById(R.id.cancelCommerce);
        ok = (Button) rootView.findViewById(R.id.okCommerce);

    }

    private void backFragment() {
        FragmentTransaction ft =  getActivity().getSupportFragmentManager().beginTransaction();
        Fragment fragment = new FragmentListEntidades();
        Bundle arg = new Bundle();
        arg.putInt(ConstantGeneral.SELECTED_ITEM_MENU,ConstantGeneral.ITEM_MENU_COMMERCE);
        fragment.setArguments(arg);
        ft.replace(R.id.fragment_section, fragment)
                .commit();
    }

    private void setOnListener() {
        cPeople.setOnClickListener(this);
        cOrganization.setOnClickListener(this);
        ok.setOnClickListener(this);
        cancel.setOnClickListener(this);
        lPeoples.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectOrganization = pCommerce.getListOrganization().get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        lOrganization.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectPeople = pCommerce.getListPoples().get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
