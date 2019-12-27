package com.dell.actapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dell.actapp.R;
import com.dell.actapp.model.Henhom;
import com.dell.actapp.ui.henhom.DetailLoaihnActivity;
import com.dell.actapp.ui.henhom.LoaihnActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterHenhom extends RecyclerView.Adapter<AdapterHenhom.HolderHn> {

    Context context;
    ArrayList<Henhom> henhomArrayList;
    LayoutInflater inflater;
    String id_ct;

    public AdapterHenhom(Context context, ArrayList<Henhom> henhomArrayList,String id_ct){
        this.context = context;
        this.henhomArrayList = henhomArrayList;
        this.id_ct = id_ct;
        inflater = inflater.from(context);
    }

    @NonNull
    @Override
    public HolderHn onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_hn, parent, false);
        return new HolderHn(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderHn holder, int position) {
        Henhom henhom = henhomArrayList.get(position);
        holder.tvTen.setText(henhom.getTenHn());
        holder.tvThongso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.layout_hn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id_henhom = Integer.parseInt(henhom.getIdHenhom());
                if(id_henhom != 2) {
                    Intent intent = new Intent(context, LoaihnActivity.class);
                    intent.putExtra("id_henhom", henhom.getIdHenhom());
                    intent.putExtra("id_ct",id_ct);
                    context.startActivity(intent);
                    ((Activity)context).finish();
                }else if(id_henhom == 2){
                    Intent intent = new Intent(context, DetailLoaihnActivity.class);
                    intent.putExtra("id_henhom",henhom.getIdHenhom());
                    intent.putExtra("id_loaihn","null");
                    intent.putExtra("id_ct",id_ct);
                    context.startActivity(intent);
                    ((Activity)context).finish();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return henhomArrayList.size();
    }

    public class HolderHn extends RecyclerView.ViewHolder{

        @BindView(R.id.item_tenhn)
        TextView tvTen;
        @BindView(R.id.thongso_item)
        TextView tvThongso;
        @BindView(R.id.layout_item_hn)
        LinearLayout layout_hn;
        public HolderHn(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
