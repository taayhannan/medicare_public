package com.medicare.sisgninnotwork4;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.firestore.auth.User;
import com.medicare.sisgninnotwork4.ui.main.Notificationsforstudents;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHoder>{

    List<Notificationsforstudents> list;
    Context context;

    public RecyclerAdapter(List<Notificationsforstudents> list,Context context) {
        this.list = list;
        this.context=context;

    }

    @Override
    public MyHoder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.card_view,parent,false);
        MyHoder myHoder = new MyHoder(view);


        return myHoder;
    }

    @Override
    public void onBindViewHolder(MyHoder holder, int position) {
        Notificationsforstudents mylist = list.get(position);
        holder.patient.setText(mylist.getmPatient());
        holder.bloodgroup.setText(mylist.getmBg());
        holder.date.setText(String.valueOf(mylist.getmDate()));
        holder.time.setText(String.valueOf(mylist.getmTime()));
    }

    @Override
    public int getItemCount() {

        int arr = 0;

        try{
            if(list.size()==0){

                arr = 0;

            }
            else{

                arr=list.size();
            }



        }catch (Exception e){



        }

        return arr;

    }
//    @Override
//    public User getItem(int position) {
//        return getItem(getItemCount() - 1 - position);
//    }

    class MyHoder extends RecyclerView.ViewHolder{
        TextView patient,bloodgroup,date,time;


        public MyHoder(View itemView) {
            super(itemView);
            patient = (TextView) itemView.findViewById(R.id.patient_name);
            bloodgroup= (TextView) itemView.findViewById(R.id.req_bg);
            date= (TextView) itemView.findViewById(R.id.req_date);
            time= (TextView) itemView.findViewById(R.id.req_time);

        }
    }

}
