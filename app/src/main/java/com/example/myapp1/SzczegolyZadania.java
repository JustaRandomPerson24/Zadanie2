package com.example.myapp1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SzczegolyZadania extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_szczegoly_zadania);

        EditText poleNazwa = findViewById(R.id.pole_nazwa_zadania);
        EditText poleOpis = findViewById(R.id.pole_opis_zadania);
        Button przyciskEdytuj = findViewById(R.id.przycisk_edytuj);

        Intent dane = getIntent();
        int pozycja = dane.getIntExtra("pozycja", -1);
        poleNazwa.setText(dane.getStringExtra("nazwa"));
        poleOpis.setText(dane.getStringExtra("opis"));

        przyciskEdytuj.setOnClickListener(v -> {
            Intent wynik = new Intent();
            wynik.putExtra("pozycja", pozycja);
            wynik.putExtra("nazwa", poleNazwa.getText().toString());
            wynik.putExtra("opis", poleOpis.getText().toString());
            setResult(RESULT_OK, wynik);
            finish();
        });
    }
}