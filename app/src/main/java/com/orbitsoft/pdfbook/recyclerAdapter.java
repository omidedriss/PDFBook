package com.orbitsoft.pdfbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;


public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.myViewHolder> {
private Context context;
private ArrayList<File> arrayList;
private recyclerInterface recyclerInterface;

    public recyclerAdapter(Context context, ArrayList<File> arrayList, recyclerInterface recyclerInterface) {
        this.context = context;
        this.arrayList = arrayList;
        this.recyclerInterface=recyclerInterface;
    }

    @NonNull
    @Override
    public recyclerAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.pdf_library,parent,false);

       return new myViewHolder(v,recyclerInterface) ;

    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.myViewHolder holder, int position) {
        holder.txt_pdfName.setText(arrayList.get(position).getName());
       // holder.txt_pdfName.setSelected(true);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }



    public static class myViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        private TextView txt_pdfName;
        public myViewHolder(@NonNull View itemView,recyclerInterface recyclerInterface) {
            super(itemView);
            cardView=itemView.findViewById(R.id.cardView);
            txt_pdfName=itemView.findViewById(R.id.txt_pdfName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerInterface !=null){
                        int pos=getAdapterPosition();
                        if (pos !=RecyclerView.NO_POSITION){
                            recyclerInterface.onItemClick(pos);
                        }
                    }
                }
            });


        }
    }
}
