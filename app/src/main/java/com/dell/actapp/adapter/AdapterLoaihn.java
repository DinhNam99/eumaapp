package com.dell.actapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dell.actapp.R;
import com.dell.actapp.model.Henhom;
import com.dell.actapp.model.Loaihn;
import com.dell.actapp.ui.henhom.DetailLoaihnActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterLoaihn extends RecyclerView.Adapter<AdapterLoaihn.HolderLoaiHn> {

    Context context;
    ArrayList<Loaihn> loaihnArrayList;
    LayoutInflater inflater;
    String id_ct;

    public AdapterLoaihn(Context context, ArrayList<Loaihn> loaihnArrayList,String id_ct){
        this.context = context;
        this.loaihnArrayList = loaihnArrayList;
        this.id_ct = id_ct;
        inflater = inflater.from(context);
    }

    @NonNull
    @Override
    public HolderLoaiHn onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_loaihn, parent, false);
        return new HolderLoaiHn(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderLoaiHn holder, int position) {
        Loaihn loaihn = loaihnArrayList.get(position);
        holder.tvTen.setText(loaihn.getTenLoaihn());

        holder.layout_hn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailLoaihnActivity.class);
                intent.putExtra("id_henhom",loaihn.getIdHenhom());
                intent.putExtra("id_loaihn",loaihn.getId_loaihn());
                intent.putExtra("id_ct",id_ct);
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return loaihnArrayList.size();
    }

    public class HolderLoaiHn extends RecyclerView.ViewHolder{

        @BindView(R.id.item_tenloaihn)
        TextView tvTen;
        @BindView(R.id.layout_item_loaihn)
        RelativeLayout layout_hn;
        public HolderLoaiHn(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

