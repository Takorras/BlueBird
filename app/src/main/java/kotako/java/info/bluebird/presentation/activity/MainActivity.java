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
import android.widget.Toast;
import kotako.java.info.bluebird.R;
import kotako.java.info.bluebird.model.event.ObtainTwitterToken;
import kotako.java.info.bluebird.model.event.MakeToast;
import kotako.java.info.bluebird.presentation.fragment.DirectMessageFragment;
import kotako.java.info.bluebird.presentation.fragment.ProfileFragment;
import kotako.java.info.bluebird.presentation.fragment.SettingsFragment;
import kotako.java.info.bluebird.presentation.fragment.TimeLineFragment;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private NavigationView navigationView;
    private FragmentManager manager;
    private DrawerLayout drawer;

    private int checkedItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 通知用レシーバの登録
        EventBus.getDefault().register(this);

        // Toolbarのセット
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        toolbar.setNavigationIcon(R.mipmap.menu_white);
        toolbar.setNavigationOnClickListener((view) -> {
            drawer.openDrawer(GravityCompat.START);
        });
        toolbar.inflateMenu(R.menu.toolbar_main);

//      メニューのクリックイベント
        toolbar.setOnMenuItemClickListener((item) -> {
            startActivity(new Intent(getApplicationContext(), PostActivity.class));
            return true;
        });

        // メニューのタッチイベント
        drawer = (DrawerLayout) findViewById(R.id.layout_drawer);
        navigationView = (NavigationView) findViewById(R.id.view_navigation);
        navigationView.setNavigationItemSelectedListener(this);
        if (checkedItemId == 0) checkedItemId = R.id.nav_timeline_home;
        navigationView.setCheckedItem(checkedItemId);

        // fragmentManagerの登録
        if (savedInstanceState == null) {
            manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.container_fragment, TimeLineFragment.newInstance()).commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == checkedItemId) return false;
        switch (item.getItemId()) {
            case R.id.nav_timeline_home:
                toolbar.setTitle("Home");
                navigationView.setCheckedItem(R.id.nav_timeline_home);
                manager.beginTransaction().replace(R.id.container_fragment, TimeLineFragment.newInstance()).commit();
                break;
            case R.id.nav_notification:
                toolbar.setTitle("Notifications");
                navigationView.setCheckedItem(R.id.nav_notification);
                break;
            case R.id.nav_directMessage:
                toolbar.setTitle("Messages");
                navigationView.setCheckedItem(R.id.nav_directMessage);
                manager.beginTransaction().replace(R.id.container_fragment, DirectMessageFragment.newInstance()).commit();
                break;
            case R.id.nav_account:
                toolbar.setTitle("Account");
                navigationView.setCheckedItem(R.id.nav_account);
                manager.beginTransaction().replace(R.id.container_fragment, ProfileFragment.newInstance()).commit();
                break;
            case R.id.nav_favorite:
                toolbar.setTitle("Favorite");
                navigationView.setCheckedItem(R.id.nav_favorite);
                break;
            case R.id.nav_archive:
                toolbar.setTitle("Archives");
                navigationView.setCheckedItem(R.id.nav_archive);
                break;
            case R.id.nav_settings:
                toolbar.setTitle("Settings");
                navigationView.setCheckedItem(R.id.nav_settings);
                manager.beginTransaction().replace(R.id.container_fragment, SettingsFragment.newInstance()).commit();
                break;
            default:
                break;
        }
        checkedItemId = item.getItemId();
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void ObtainTwitterToken(ObtainTwitterToken event) {
        startActivity(new Intent(getApplication(), LoginWithTwitterActivity.class));
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onToastEvent(MakeToast event) {
        final String massage = event.getMessage();
        runOnUiThread(() -> {
            Toast.makeText(getApplicationContext(), massage, Toast.LENGTH_SHORT).show();
        });
    }
}
