package com.example.messagesender;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

public class ConversasFragment extends Fragment {
    ListView listaConversas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_conversas, container, false);

        String lista[] = new String[]{"Alo", "ola"};

        listaConversas = view.findViewById(R.id.listaConversas);
        listaConversas.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, lista));
        return view;
    }
}
