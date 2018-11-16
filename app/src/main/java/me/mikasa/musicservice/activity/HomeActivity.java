package me.mikasa.musicservice.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import me.mikasa.musicservice.R;
import me.mikasa.musicservice.fragment.MineFragment;
import me.mikasa.musicservice.fragment.PlayBarFragment;
import woo.mikasa.lib.base.BaseActivity;

public class HomeActivity extends BaseActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private static long lastPressed=0;
    private MineFragment mineFragment;

    @Override
    protected int setLayoutResId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initData() {
        mineFragment=new MineFragment();
    }

    @Override
    protected void initView() {
        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.navView);
        initToolbar();
        initFragment();
    }
    private void initToolbar(){
        Toolbar toolbar=findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
    }
    private void initFragment(){
        PlayBarFragment playBarFragment=new PlayBarFragment();
        mineFragment=new MineFragment();
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.fragment_play_bar,playBarFragment);
        transaction.replace(R.id.home_fl,mineFragment);//??
        transaction.commit();
    }

    @Override
    protected void initListener() {
        findViewById(R.id.toolbar_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mineFragment.loadMusicCount();
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_about_me:
                        showToast("待开发");
                        break;
                    case R.id.nav_logout:
                        showToast("待开发");
                        break;
                }
                closeDrawer();
                return true;
            }
        });
        findViewById(R.id.drawer_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
    private void closeDrawer(){
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        long current=System.currentTimeMillis();
        if ((current-lastPressed)>2000){
            showToast("再点击一次退出程序");
            lastPressed=current;
        }else {
            finish();
        }
    }
}
