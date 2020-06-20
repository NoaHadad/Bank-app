package com.example.noa.arielibank;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SavingList extends ArrayAdapter<SavingIdSum> {

    private Activity context;
    private List<SavingIdSum> savingList;

    SavingList(Activity context, List<SavingIdSum> savingList){
        super(context, R.layout.saving_layout,savingList);
        this.context=context;
        this.savingList=savingList;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem=inflater.inflate(R.layout.saving_layout,null,true);

        TextView saving2=(TextView) listViewItem.findViewById(R.id.savingNum);
        TextView sum2=(TextView) listViewItem.findViewById(R.id.savingSum);

        SavingIdSum saving =savingList.get(position);

        saving2.setText(Integer.toString(saving.getId()));
        sum2.setText(Double.toString(saving.getSum()));

        return listViewItem;

    }


}
