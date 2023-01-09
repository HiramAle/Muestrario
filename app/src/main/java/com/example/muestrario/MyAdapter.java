package com.example.muestrario;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<Muestra> list;

    public MyAdapter(Context context, ArrayList<Muestra> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.muestra, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        Muestra muestra = list.get(position);
        holder.name.setText(muestra.name);
        holder.id.setText(String.valueOf(muestra.id));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView id;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.cv_name);
            id = itemView.findViewById(R.id.cv_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println(name.getText());
                    System.out.println(id.getText());


                    Bundle bundle = new Bundle();
                    bundle.putString("id", id.getText().toString());

                    DataFragment fragment = new DataFragment();
                    fragment.setArguments(bundle);


                    ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.body_container,
                            fragment).commit();


                }
            });


        }
    }


}
