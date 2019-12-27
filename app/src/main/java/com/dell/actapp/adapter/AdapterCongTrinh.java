package com.dell.actapp.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dell.actapp.R;
import com.dell.actapp.model.CongTrinh;
import com.dell.actapp.model.Message;
import com.dell.actapp.network.api.APICongtrinh;
import com.dell.actapp.ui.cua.CuaActivity;
import com.dell.actapp.ui.kinh.KinhActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdapterCongTrinh extends RecyclerView.Adapter<AdapterCongTrinh.HolderCT> {

    ArrayList<CongTrinh> congTrinhList;
    Context context;
    LayoutInflater inflater;
    public String url = "http://namnam.atwebpages.com/";

    public AdapterCongTrinh(Context context, ArrayList<CongTrinh> congTrinhList){
        this.congTrinhList = congTrinhList;
        this.context = context;
        inflater = inflater.from(context);
    }

    @NonNull
    @Override
    public HolderCT onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_congtrinh, parent, false);
        return new HolderCT(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderCT holder, int position) {

        CongTrinh congTrinh = congTrinhList.get(position);
        holder.tenCT.setText(congTrinh.getTenct()+"");
        holder.tenCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CuaActivity.class);
                intent.putExtra("id_ct",congTrinh.getIdCt());
                intent.putExtra("tenct",congTrinh.getTenct());
                Log.e("ten",congTrinh.getTenct());
                context.startActivity(intent);
            }
        });

        holder.cua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CuaActivity.class);
                intent.putExtra("id_ct",congTrinh.getIdCt());
                intent.putExtra("tenct",congTrinh.getTenct());
                Log.e("ten",congTrinh.getTenct());
                context.startActivity(intent);
            }
        });
        holder.kinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, KinhActivity.class);
                context.startActivity(intent);
            }
        });
        holder.nhom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View customLayout = inflater.inflate(R.layout.dialog_add, null);
                builder.setView(customLayout);
                builder.setTitle("Kính thước cây nhôm");
                EditText edTen = (EditText) customLayout.findViewById(R.id.tenctrinh);
                edTen.setHint("Kích thước (mm)");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alert1 = builder.create();
                alert1.show();
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Bạn có chắc muốn xóa công trình không?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteCt(congTrinh.getIdCt());
                        congTrinhList.remove(position);
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alert1 = builder.create();
                alert1.show();
            }
        });
    }

    public void deleteCt(String id){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        APICongtrinh apiCongtrinh = retrofit.create(APICongtrinh.class);
        Call<List<Message>> call = apiCongtrinh.deleteProduct(id);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                List<Message> messageList = response.body();
                for (int i = 0; i < messageList.size(); i++) {
                    if (messageList.get(i).getMessage().equals("success")) {
                        Toast.makeText(context,"Đã xóa!",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context,"Xóa thất bại!",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return congTrinhList.size();
    }

    public class HolderCT extends RecyclerView.ViewHolder{

        @BindView(R.id.item_tenctrinh)
        TextView tenCT;
        @BindView(R.id.nhom)
        LinearLayout nhom;
        @BindView(R.id.kinh)
        LinearLayout kinh;
        @BindView(R.id.cua)
        LinearLayout cua;
        @BindView(R.id.deleteCT)
        ImageView delete;

        public HolderCT(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
