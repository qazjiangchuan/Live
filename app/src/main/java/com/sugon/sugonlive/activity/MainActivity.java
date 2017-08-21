package com.sugon.sugonlive.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sugon.sugonlive.R;
import com.sugon.sugonlive.adapter.FragAdapter;
import com.sugon.sugonlive.fragment.LiveFragment;
import com.sugon.sugonlive.fragment.LocalFragment;
import com.sugon.sugonlive.fragment.RecordFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private LiveFragment mLiveFragment;

    private RecordFragment mRecordFragment;

    private LocalFragment mLocalFragment;

    private TabLayout mTabLayout;

    private ViewPager mViewPager;

    private DrawerLayout mDrawerLayout;

    private NavigationView mNavigationView;

    private View mHeaderView;
    private ImageView mImageAvatar;
    private TextView mTextUserName;
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initData();
    }

    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mLiveFragment = new LiveFragment();
        mRecordFragment = new RecordFragment();
        mLocalFragment = new LocalFragment();

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        FragAdapter adapter = new FragAdapter(getSupportFragmentManager());
        adapter.addFragment(mLiveFragment, "直播");
        adapter.addFragment(mRecordFragment, "录播");
        adapter.addFragment(mLocalFragment, "本地");
        mViewPager.setAdapter(adapter);

        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        mTabLayout.addTab(mTabLayout.newTab().setText("直播"));
        mTabLayout.addTab(mTabLayout.newTab().setText("录像"));
        mTabLayout.addTab(mTabLayout.newTab().setText("本地"));
        mTabLayout.setupWithViewPager(mViewPager);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        mHeaderView = mNavigationView.getHeaderView(0);
        mImageAvatar = (ImageView) mHeaderView.findViewById(R.id.img_avatar);
        mTextUserName = (TextView) mHeaderView.findViewById(R.id.txt_username);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PushActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initData() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //按两次退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
            } else if (mLocalFragment.isVisible()) {
                mLocalFragment.onBackPressed();
            } else {
                exit();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_update:
                break;
            case R.id.nav_exit:
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
