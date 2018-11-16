package me.mikasa.musicservice.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import me.mikasa.musicservice.R;
import me.mikasa.musicservice.activity.FavorMusicActivity;
import me.mikasa.musicservice.activity.LocalMusicActivity;
import me.mikasa.musicservice.activity.RecentMusicActivity;
import me.mikasa.musicservice.database.DbManager;
import me.mikasa.musicservice.util.Constant;
import woo.mikasa.lib.base.BaseFragment;

/**
 * Created by mikasa on 2018/11/14.
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {
    private DbManager manager;
    private TextView localMusicCountTv,recentPlayCountTv,favorCountTv;
    private static boolean isNull=false;
    @Override
    protected int setLayoutResId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initData(Bundle bundle) {
        manager=DbManager.getInstance(mBaseActivity);
    }

    @Override
    protected void initView() {
        localMusicCountTv =mRootView.findViewById(R.id.home_local_music_count_tv);
        recentPlayCountTv =mRootView.findViewById(R.id.home_recent_music_count_tv);
        favorCountTv =mRootView.findViewById(R.id.home_favor_music_count_tv);
        loadMusicCount();
    }
    public void loadMusicCount(){
        int count;
        count = manager.getMusicCount(Constant.ALLMUSIC);
        if (count==0){
            isNull=true;
        }
        localMusicCountTv.setText(String.valueOf(count));
        count = manager.getMusicCount(Constant.RECENTPLAY);
        recentPlayCountTv.setText(String.valueOf(count));
        count = manager.getMusicCount(Constant.MYLOVE);
        favorCountTv.setText(String.valueOf(count));
    }
    @Override
    protected void setListener() {
        mRootView.findViewById(R.id.home_local_music_ll).setOnClickListener(this);
        mRootView.findViewById(R.id.home_recent_music_ll).setOnClickListener(this);
        mRootView.findViewById(R.id.home_favor_music_ll).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()){
            case R.id.home_local_music_ll:
                intent.setClass(mBaseActivity,LocalMusicActivity.class);
                intent.putExtra("isnull",isNull);
                break;
            case R.id.home_recent_music_ll:
                intent.setClass(mBaseActivity,RecentMusicActivity.class);
                break;
            case R.id.home_favor_music_ll:
                intent.setClass(mBaseActivity,FavorMusicActivity.class);
                break;
        }
        startActivity(intent);
    }
}
