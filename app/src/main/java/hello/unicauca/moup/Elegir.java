package hello.unicauca.moup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Elegir extends AppCompatActivity {

    private EditText elegirdestino, elegirtarifa;
    private Button aceptar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elegir);

        elegirdestino = (EditText) findViewById(R.id.textdestino);
        elegirtarifa = (EditText) findViewById(R.id.texttarifa);
        aceptar = (Button) findViewById(R.id.button4);
        aceptar.setOnClickListener(this::onClick);
    }

    public void onClick(View v) {
        Intent intent = new Intent(Elegir.this, Lista.class);
        startActivity(intent);
    }

}