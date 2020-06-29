package com.example.messagesender.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messagesender.ChatActivity;
import com.example.messagesender.Conversa;
import com.example.messagesender.R;
import com.example.messagesender.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;
import com.xwray.groupie.OnItemClickListener;
import com.xwray.groupie.ViewHolder;

import java.util.List;

public class ConversasFragment extends Fragment {

    RecyclerView recyclerView;
    GroupAdapter groupAdapter;

    public ConversasFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_conversas, container, false);

        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Linha divisoria do recycler view de contatos
        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        groupAdapter = new GroupAdapter();
        recyclerView.setAdapter(groupAdapter);

        fetchLastMessage();

        return view;
    }

    private void fetchLastMessage() {
        String uid = FirebaseAuth.getInstance().getUid();

        FirebaseFirestore.getInstance().collection("/last-messages")
                .document(uid)
                .collection("contacts")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        List<DocumentChange> documentChanges = queryDocumentSnapshots.getDocumentChanges();
                        if (documentChanges != null) {
                            for (DocumentChange doc : documentChanges) {
                                if (doc.getType() == DocumentChange.Type.ADDED) {
                                    Conversa conversa = doc.getDocument().toObject(Conversa.class);
                                    groupAdapter.add(new ConversaItem(conversa));
                                }
                            }
                        }
                    }
                });
    }


    private class ConversaItem extends Item<ViewHolder> {

        private final Conversa conversa;

        private ConversaItem(Conversa conversa) {
            this.conversa = conversa;
        }

        @Override
        public void bind(@NonNull ViewHolder viewHolder, int position) {
            TextView username = viewHolder.itemView.findViewById(R.id.txtUsernameConversa);
            TextView lastmsg = viewHolder.itemView.findViewById(R.id.txtLastMsg);
            ImageView imguser = viewHolder.itemView.findViewById(R.id.imgUser);


            username.setText(conversa.getUsername());
            lastmsg.setText(conversa.getLastmessage());
            Picasso.get()
                    .load(conversa.getPhotoUrl())
                    .into(imguser);
        }

        @Override
        public int getLayout() {
            return R.layout.item_user_message;
        }
    }
}