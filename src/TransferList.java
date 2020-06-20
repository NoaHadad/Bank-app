package com.example.noa.arielibank;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class TransferList extends ArrayAdapter<TransferToSum> {

    private Activity context;
    private List<TransferToSum> transferList;

    TransferList(Activity context, List<TransferToSum> transferList){
        super(context, R.layout.transfer_layout,transferList);
        this.context=context;
        this.transferList=transferList;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem=inflater.inflate(R.layout.transfer_layout,null,true);

        TextView receivingAccount=(TextView) listViewItem.findViewById(R.id.receiveACCOUNT);
        TextView sum=(TextView) listViewItem.findViewById(R.id.receivedSUM);

        TransferToSum transfer =transferList.get(position);

        receivingAccount.setText(Integer.toString(transfer.getTo()));
        sum.setText(Double.toString(transfer.getSum()));

        return listViewItem;

    }


}