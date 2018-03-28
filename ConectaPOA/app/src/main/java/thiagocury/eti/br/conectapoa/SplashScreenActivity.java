package thiagocury.eti.br.conectapoa;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                mostrarMainActivity();
            }
        }, 1500);
    }

    private void mostrarMainActivity(){
        Intent it = new Intent(SplashScreenActivity.this,MainActivity.class);
        startActivity(it);
        finish();
    }
}
