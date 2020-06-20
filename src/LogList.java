package com.example.noa.arielibank;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class LogList extends ArrayAdapter<log> {

        private Activity context;
        private List<log> logList;

       LogList(Activity context, List<log> logList){
            super(context, R.layout.saving_layout,logList);
            this.context=context;
            this.logList=logList;
        }

        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater=context.getLayoutInflater();
            View listViewItem=inflater.inflate(R.layout.log_list,null,true);

            TextView log1=(TextView) listViewItem.findViewById(R.id.log1);


            log log =logList.get(position);

            log1.setText(Double.toString(log.movement));

            return listViewItem;

        }


    }