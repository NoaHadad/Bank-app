package com.example.noa.arielibank;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AccountList extends ArrayAdapter<Account> {

    private Activity context;
    private List<Account> accountList;

    AccountList(Activity context, List<Account> accountList){
        super(context, R.layout.list_layout,accountList);
        this.context=context;
        this.accountList=accountList;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem=inflater.inflate(R.layout.list_layout,null,true);

        TextView account2=(TextView) listViewItem.findViewById(R.id.id12);
        TextView balance2=(TextView) listViewItem.findViewById(R.id.sum12);

        Account account =accountList.get(position);

        account2.setText(Integer.toString(account.getID()));
        balance2.setText(Double.toString(account.getBalance()));

        return listViewItem;

    }




}
