package hello.unicauca.moup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView marcador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //////esconder barra de estado
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //// Dirigir a la otra pestaña
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
              Intent intent = new Intent(MainActivity.this, Login.class);
              startActivity(intent);
              finish();
            }
        }, 4000);



    }


}