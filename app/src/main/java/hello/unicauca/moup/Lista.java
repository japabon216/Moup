package hello.unicauca.moup;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Lista extends AppCompatActivity {

    ArrayList<Usuario> listaUsuarios;
    RecyclerView recyclerUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        listaUsuarios = new ArrayList<>();
        recyclerUsuarios = findViewById(R.id.idRecyclerView);
        recyclerUsuarios.setLayoutManager(new LinearLayoutManager(this));

        llenarUsuarios();

        AdaptadorUsuarios apapter = new AdaptadorUsuarios(listaUsuarios);
        recyclerUsuarios.setAdapter(apapter);

    }

    private void llenarUsuarios() {
        listaUsuarios.add(new Usuario("Maria","A 5 min", "Ofrece 4000", "TXT-343", R.drawable.img1));
        listaUsuarios.add(new Usuario( "Juan", "A 3 min", "Ofrece 3000", "HTY-643", R.drawable.img2));
        listaUsuarios.add(new Usuario( "Gustavo",  "A 4 min", "Ofrece 2000", "LOT-567", R.drawable.img3));
        listaUsuarios.add(new Usuario( "Bolivar",  "A 7 min", "Ofrece 4000", "BNG-567", R.drawable.img4));
        listaUsuarios.add(new Usuario( "Teresa",  "A 6 min", "Ofrece 5000", "TTY-789", R.drawable.img5));
        listaUsuarios.add(new Usuario( "Milena",  "A 5 min", "Ofrece 3000", "VGT-654", R.drawable.img6));
        listaUsuarios.add(new Usuario( "Camila",  "A 4 min", "Ofrece 3000", "ERT-678", R.drawable.img7));
        listaUsuarios.add(new Usuario( "Anyi",  "A 2 min", "Ofrece 3500", "WWE-123", R.drawable.img8));
        listaUsuarios.add(new Usuario( "Rosa",  "A 1 min", "Ofrece 2700", "XDF-675", R.drawable.img9));
        listaUsuarios.add(new Usuario( "Goku",  "A 10 min", "Ofrece 4000", "OIP-712", R.drawable.img10));
    }
}