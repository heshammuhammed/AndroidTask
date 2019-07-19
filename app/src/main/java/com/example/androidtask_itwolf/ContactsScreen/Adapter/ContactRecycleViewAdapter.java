package com.example.androidtask_itwolf.ContactsScreen.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.androidtask_itwolf.ContactsScreen.Room.ContactEntity;
import com.example.androidtask_itwolf.R;

import java.util.List;

public class ContactRecycleViewAdapter extends RecyclerView.Adapter<ContactRecycleViewAdapter.ContactsViewHolder>{

    private List<ContactEntity> contactEntityList;
    private Context context;
    private ContactInteractor contactInteractor;

    public ContactRecycleViewAdapter(List<ContactEntity> contactEntityList , Context context , ContactInteractor contactInteractor){
        this.contactEntityList = contactEntityList;
        this.context = context;
        this.contactInteractor = contactInteractor;
    }

    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ContactsViewHolder(LayoutInflater.from(context).inflate(R.layout.contact_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder contactsViewHoler, int i) {
        contactsViewHoler.nameItem.setText(contactEntityList.get(i).getName());
        contactsViewHoler.phoneItem.setText(contactEntityList.get(i).getPhone());
    }

    @Override
    public int getItemCount() {
        return contactEntityList.size();
    }


    class ContactsViewHolder extends RecyclerView.ViewHolder{
        TextView nameItem;
        TextView phoneItem;
        LinearLayout linearLayout;

        public ContactsViewHolder(@NonNull View itemView) {
            super(itemView);
            nameItem = itemView.findViewById(R.id.name_item);
            phoneItem = itemView.findViewById(R.id.phone_item);
            linearLayout = itemView.findViewById(R.id.linearlayout);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    contactInteractor.deleteContact(contactEntityList.get(getLayoutPosition()));
                    return true;
                }
            });
        }
    }
}
