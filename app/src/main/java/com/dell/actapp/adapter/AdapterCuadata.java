package com.dell.actapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dell.actapp.R;
import com.dell.actapp.model.Cua;
import com.dell.actapp.model.Cuadata;
import com.dell.actapp.ui.cua.DetailCuaActivity;

import java.util.ArrayList;

public class AdapterCuadata extends RecyclerView.Adapter<AdapterCua.HolderCua> {
    ArrayList<Cuadata> cuadataArrayList;
    Context context;
    LayoutInflater inflater;
    String id_ct;

    public AdapterCuadata(Context context, ArrayList<Cuadata> cuadataArrayList,String id_ct){
        this.context = context;
        this.cuadataArrayList = cuadataArrayList;
        this.id_ct = id_ct;
        inflater = inflater.from(context);
    }

    @NonNull
    @Override
    public AdapterCua.HolderCua onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_cua, parent, false);
        AdapterCua.HolderCua holderCua = new AdapterCua.HolderCua(view);
        return holderCua;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCua.HolderCua holder, int position) {
        Cuadata cuadata = cuadataArrayList.get(position);
        holder.tvTencua.setText(cuadata.getTencuaData());
        byte[] decodedString = Base64.decode(cuadata.getAnhcuadata(), Base64.DEFAULT);
        Bitmap imgBitMap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        holder.image_cua.setImageBitmap(imgBitMap);

        holder.layout_itemcua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailCuaActivity.class);
                intent.putExtra("cuadata",cuadata);
                intent.putExtra("c",2);
                intent.putExtra("id_ct",id_ct);
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cuadataArrayList.size();
    }
}
