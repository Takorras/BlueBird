package kotako.java.info.bluebird.presentation.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import butterknife.ButterKnife;
import kotako.java.info.bluebird.R;

public class AuthorizationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_auhorization);
    }

    @Override
    public void onDestroy(){
        finish();
    }

}
