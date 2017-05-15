package kotako.java.info.bluebird.presentation.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import kotako.java.info.bluebird.R;
import kotako.java.info.bluebird.presentation.fragment.DirectMessageFragment;
import kotako.java.info.bluebird.presentation.fragment.ProfileFragment;
import kotako.java.info.bluebird.presentation.fragment.SettingsFragment;
import kotako.java.info.bluebird.presentation.fragment.TimeLineFragment;
import org.greenrobot.eventbus.EventBus;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private Toolbar toolbar;
    private NavigationView nav;
    private FragmentManager manager;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 通知用レシーバの登録
        //EventBus.getDefault().register(this);

        // Toolbarのセット
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        toolbar.setNavigationIcon(R.mipmap.menu_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
        toolbar.inflateMenu(R.menu.toolbar_main);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                startActivity(new Intent(getApplicationContext(),PostActivity.class));
                return true;
            }
        });

        // メニューのタッチイベント
        drawer = (DrawerLayout) findViewById(R.id.layout_drawer);
        nav = (NavigationView) findViewById(R.id.view_navigation);
        nav.setNavigationItemSelectedListener(this);
        nav.setCheckedItem(R.id.nav_timeline_home);

        // fragmentManagerの登録
        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.container_fragment, TimeLineFragment.newInstance()).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_timeline_home:
                toolbar.setTitle("TimeLineEvent");
                nav.setCheckedItem(R.id.nav_timeline_home);
                break;
            case R.id.nav_notification:
                toolbar.setTitle("Notifications");
                nav.setCheckedItem(R.id.nav_notification);
                break;
            case R.id.nav_directMessage:
                toolbar.setTitle("Messages");
                nav.setCheckedItem(R.id.nav_directMessage);
                manager.beginTransaction().replace(R.id.container_fragment, DirectMessageFragment.newInstance()).commit();
                break;
            case R.id.nav_account:
                toolbar.setTitle("Account");
                nav.setCheckedItem(R.id.nav_account);
                manager.beginTransaction().replace(R.id.container_fragment, ProfileFragment.newInstance()).commit();
                break;
            case R.id.nav_favorite:
                toolbar.setTitle("Favorite");
                nav.setCheckedItem(R.id.nav_favorite);
                break;
            case R.id.nav_archive:
                toolbar.setTitle("Archives");
                nav.setCheckedItem(R.id.nav_archive);
                break;
            case R.id.nav_settings:
                toolbar.setTitle("Settings");
                nav.setCheckedItem(R.id.nav_settings);
                manager.beginTransaction().replace(R.id.container_fragment, SettingsFragment.newInstance()).commit();
                break;
            default:
                Toast.makeText(getApplicationContext(), "そんなものはない", Toast.LENGTH_SHORT).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
