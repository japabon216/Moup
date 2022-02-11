package hello.unicauca.moup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class Login extends AppCompatActivity {

    private Button BotonTelefono, BotonGoogle;
   // Animation AnimateTelefono, AnimateGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        BotonTelefono = (Button) findViewById(R.id.button2);
        BotonGoogle = (Button) findViewById(R.id.button);

      //  AnimateTelefono = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.);

    }

    public void TelefonoLoginClick(View view){
        Intent intent = new Intent(Login.this, TelefonoLogin.class);
        startActivity(intent);
        //Animatoo.animateSlideUp(this);
    }
}