package com.dell.actapp.ui.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dell.actapp.R;
import com.dell.actapp.fragment.congtrinh.Fragment_ds_congtrinh;
import com.dell.actapp.model.User;
import com.dell.actapp.network.source.AuthData;
import com.dell.actapp.ui.login.LoginActivity;
import com.dell.actapp.utils.ShareReferenceUtils;
import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawermain)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigationViewMain)
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    CircleImageView imageUser;
    TextView tvUN;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }
    public void init(){

        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("");
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        actionbar.setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);
        User user = AuthData.getInstance().getUser();

        View viewHearder = navigationView.inflateHeaderView(R.layout.navigation_layout);
        tvUN = navigationView.getHeaderView(0).findViewById(R.id.tvUN);
        imageUser = navigationView.getHeaderView(0).findViewById(R.id.imageUser);
        tvUN.setText(user.getUsername()+"");

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_account);
        requestOptions.error(R.drawable.ic_account);

        Glide.with(getApplicationContext())
                .setDefaultRequestOptions(requestOptions)
                .load(user.getThumnail()).into(imageUser);

        displaySelectItemNavigation(R.id.list);


    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        menuItem.setChecked(true);
        displaySelectItemNavigation(menuItem.getItemId());
        return true;
    }

    public void displaySelectItemNavigation(int itemId) {
        Fragment fragment = null;

        switch (itemId) {
            case R.id.list:
                fragment = new Fragment_ds_congtrinh();
                break;
            case R.id.logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Bạn có chắc muốn đăng xuất không?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        ShareReferenceUtils.removeKey(AuthData.KEY_USER);
                        startActivity(intent);
                        finish();
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
                break;
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame_layout, fragment);
            ft.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawermain);
        drawer.closeDrawer(GravityCompat.START);
    }
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Nhập thêm lần nữa để thoát!", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
