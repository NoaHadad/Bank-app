package com.example.noa.arielibank;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class LoanList extends ArrayAdapter<LoanIdFund> {

    private Activity context;
    private List<LoanIdFund> loanList;

    LoanList(Activity context, List<LoanIdFund> loanList){
        super(context, R.layout.loan_layout,loanList);
        this.context=context;
        this.loanList=loanList;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem=inflater.inflate(R.layout.loan_layout,null,true);

        TextView loan2=(TextView) listViewItem.findViewById(R.id.loanNum);
        TextView remainingFund2=(TextView) listViewItem.findViewById(R.id.fund);

        LoanIdFund loan =loanList.get(position);

        loan2.setText(Integer.toString(loan.getId()));
        remainingFund2.setText(Double.toString(loan.getFund()));

        return listViewItem;

    }


}
