package com.example.myapp1;

import static androidx.core.app.ActivityCompat.startActivityForResult;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Zadanie> listaZadan;
    private AdapterZadan adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaZadan = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        adapter = new AdapterZadan(listaZadan, pozycja -> {
            Intent intent = new Intent(MainActivity.this, SzczegolyZadania.class);
            intent.putExtra("pozycja", pozycja);
            intent.putExtra("nazwa", listaZadan.get(pozycja).getNazwa());
            intent.putExtra("opis", listaZadan.get(pozycja).getOpis());
            startActivityForResult(intent, 2);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        FloatingActionButton fabDodajZadanie = findViewById(R.id.fab_dodaj_zadanie);
        fabDodajZadanie.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DodajZadanie.class);
            startActivityForResult(intent, 1);
        });
    }

    @Override
    protected void onActivityResult(int kodZadania, int kodWyniku, @Nullable Intent dane) {
        super.onActivityResult(kodZadania, kodWyniku, dane);
        if (kodZadania == 1 && kodWyniku == RESULT_OK && dane != null) {
            String nazwa = dane.getStringExtra("nazwa");
            String opis = dane.getStringExtra("opis");
            listaZadan.add(new Zadanie(nazwa, opis));
            adapter.notifyDataSetChanged();
        } else if (kodZadania == 2 && kodWyniku == RESULT_OK && dane != null) {
            int pozycja = dane.getIntExtra("pozycja", -1);
            if (pozycja != -1) {
                String nazwa = dane.getStringExtra("nazwa");
                String opis = dane.getStringExtra("opis");
                listaZadan.get(pozycja).setNazwa(nazwa);
                listaZadan.get(pozycja).setOpis(opis);
                adapter.notifyItemChanged(pozycja);
            }
        }
    }
}
