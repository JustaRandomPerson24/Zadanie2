package com.example.myapp1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterZadan extends RecyclerView.Adapter<AdapterZadan.WidokZadania> {
    private ArrayList<Zadanie> listaZadan;
    private OnZadanieKlik listener;

    public interface OnZadanieKlik {
        void klikZadanie(int pozycja);
    }

    public AdapterZadan(ArrayList<Zadanie> listaZadan, OnZadanieKlik listener) {
        this.listaZadan = listaZadan;
        this.listener = listener;
    }

    @NonNull
    @Override
    public WidokZadania onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View widok = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_zadanie, parent, false);
        return new WidokZadania(widok, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull WidokZadania holder, int pozycja) {
        Zadanie zadanie = listaZadan.get(pozycja);
        holder.nazwa.setText(zadanie.getNazwa());
        holder.opis.setText(zadanie.getOpis());
    }

    @Override
    public int getItemCount() {
        return listaZadan.size();
    }

    public static class WidokZadania extends RecyclerView.ViewHolder {
        TextView nazwa, opis;

        public WidokZadania(@NonNull View itemView, OnZadanieKlik listener) {
            super(itemView);
            nazwa = itemView.findViewById(R.id.text_nazwa_zadania);
            opis = itemView.findViewById(R.id.text_opis_zadania);

            itemView.setOnClickListener(v -> listener.klikZadanie(getAdapterPosition()));
        }
    }
}

