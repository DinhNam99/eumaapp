package com.dell.actapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.dell.actapp.R;
import com.dell.actapp.model.Loaicua;
import com.dell.actapp.model.Loaihn;
import com.dell.actapp.ui.cua.CuaActivity;
import com.dell.actapp.ui.cua.DataCuaActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterDetailLoaihn extends  Adapter<AdapterDetailLoaihn.HolderLoaiCua>{
    Context context;
    ArrayList<Loaicua> loaicuaList;
    LayoutInflater inflater;
    String id_ct;

    public AdapterDetailLoaihn(Context context, ArrayList<Loaicua> loaicuaList,String id_ct) {
        this.context = context;
        this.loaicuaList = loaicuaList;
        this.id_ct = id_ct;
        inflater = inflater.from(context);
    }

    @NonNull
    @Override
    public HolderLoaiCua onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_loaihn, parent, false);
        return new HolderLoaiCua(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderLoaiCua holder, int position) {
        Loaicua loaicua = loaicuaList.get(position);
        holder.tvTen.setText(loaicua.getLoaicua());

        holder.layout_hn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DataCuaActivity.class);
                intent.putExtra("id_henhom", loaicua.getId_henhom());
                intent.putExtra("loaihenhom",loaicua.getLoaihenhom());
                intent.putExtra("loaicua",loaicua.getLoaicua());
                intent.putExtra("id_ct",id_ct);
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return loaicuaList.size();
    }

    public class HolderLoaiCua extends RecyclerView.ViewHolder {

        @BindView(R.id.item_tenloaihn)
        TextView tvTen;
        @BindView(R.id.layout_item_loaihn)
        RelativeLayout layout_hn;

        public HolderLoaiCua(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}