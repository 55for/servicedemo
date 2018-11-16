package me.mikasa.musicservice.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import me.mikasa.musicservice.R;
import me.mikasa.musicservice.fragment.RecentMusicFragment;


public class RecentMusicActivity extends PlayBarBaseActivity {

    @Override
    protected int setLayoutResId() {
        return R.layout.activity_recent_music;
    }

    @Override
    protected void initData() {
        mTitle.setText("最近音乐");
    }

    @Override
    protected void initView() {
        RecentMusicFragment fragment=new RecentMusicFragment();
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction tf=manager.beginTransaction();
        tf.replace(R.id.fragment_container,fragment);
        tf.commit();
    }

    @Override
    protected void initListener() {
    }
}
