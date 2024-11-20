package com.example.myapp1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class DodajZadanie extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_zadanie);

        EditText poleNazwa = findViewById(R.id.pole_nazwa_zadania);
        EditText poleOpis = findViewById(R.id.pole_opis_zadania);
        Button przyciskZapisz = findViewById(R.id.przycisk_zapisz);

        przyciskZapisz.setOnClickListener(v -> {
            String nazwa = poleNazwa.getText().toString();
            String opis = poleOpis.getText().toString();

            Intent wynik = new Intent();
            wynik.putExtra("nazwa", nazwa);
            wynik.putExtra("opis", opis);
            setResult(RESULT_OK, wynik);
            finish();
        });
    }
}


