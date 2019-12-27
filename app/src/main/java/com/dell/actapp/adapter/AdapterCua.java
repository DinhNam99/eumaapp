package com.dell.actapp.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dell.actapp.R;
import com.dell.actapp.model.Cua;
import com.dell.actapp.ui.cua.DetailCuaActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterCua extends RecyclerView.Adapter<AdapterCua.HolderCua> {
    ArrayList<Cua> cuaArrayList;
    Context context;
    LayoutInflater inflater;
    public AdapterCua(Context context, ArrayList<Cua> cuaArrayList){
        this.context = context;
        this.cuaArrayList = cuaArrayList;
        inflater = inflater.from(context);
    }

    @NonNull
    @Override
    public HolderCua onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_cua, parent, false);
        return new HolderCua(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderCua holder, int position) {
        Cua cua = cuaArrayList.get(position);

        holder.tvTencua.setText(cua.getTencua()+"");
//        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(R.drawable.cua);
//        requestOptions.error(R.drawable.cua);

//        Glide.with(context)
//                .load(cua.getAnhcua()).into(holder.image_cua);
        byte[] decodedString = Base64.decode(cua.getAnhcua(), Base64.DEFAULT);
        Bitmap imgBitMap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        holder.image_cua.setImageBitmap(imgBitMap);

        holder.layout_itemcua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailCuaActivity.class);
                intent.putExtra("cua",cua);
                intent.putExtra("c",1);
                intent.putExtra("id_ct",cua.getIdCt());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cuaArrayList.size();
    }

    public static class HolderCua extends RecyclerView.ViewHolder{

        @BindView(R.id.layout_itemcua)
        LinearLayout layout_itemcua;
        @BindView(R.id.item_image_cua)
        ImageView image_cua;
        @BindView(R.id.item_tencua)
        TextView tvTencua;
        public HolderCua(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
