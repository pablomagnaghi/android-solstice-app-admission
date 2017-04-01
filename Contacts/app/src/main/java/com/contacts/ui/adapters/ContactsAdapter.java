package com.contacts.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.contacts.R;
import com.contacts.model.Contact;
import com.contacts.ui.activities.ContactDetailsActivity;
import com.contacts.ui.listener.RecyclerOnItemClickListener;
import com.contacts.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ContactsAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements RecyclerOnItemClickListener {

    private List<Contact> mContacts;

    private Context mContext;

    public ContactsAdapter(Context context){
        mContext = context;
        mContacts = new ArrayList<>();
    }

    public void setContacts(List<Contact> contacts) {
        mContacts = contacts;
    }

    public List<Contact> getContacts() {
        return mContacts;
    }

    public void clear() {
        mContacts.clear();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemListView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacts, parent, false);
        return new ContactViewHolder(itemListView, this);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final Contact contact = mContacts.get(position);
        ContactViewHolder contactViewHolder = (ContactViewHolder)holder;
        Glide.with(mContext)
                .load(contact.getSmallImageURL())
                .placeholder(R.drawable.default_user_photo)
                .error(R.drawable.default_user_photo)
                .fitCenter()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(contactViewHolder.userPhotoImageView);

        contactViewHolder.userNameTextView.setText(contact.getName());
        if (contact.hasPhone()) {
            contactViewHolder.phoneTextView.setText(contact.getDefaultPhone());
        }
    }

    @Override
    public int getItemCount() {
        return mContacts != null ? mContacts.size() : 0;
    }

    @Override
    public void onItemClicked(View view, int position) {

        if (mContacts.size() > position) {
            Contact contact = mContacts.get(position);
            Intent intent = new Intent(mContext, ContactDetailsActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Constants.CONTACT_INTENT, contact);
            mContext.startActivity(intent);
        }
    }

    class PhotoGridViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_user_photo)
        ImageView photoImageView;

        public PhotoGridViewHolder(View itemView, final RecyclerOnItemClickListener recyclerOnItemClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerOnItemClickListener != null) {
                        recyclerOnItemClickListener.onItemClicked(v, getAdapterPosition());
                    }
                }
            });
        }
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_user_photo)
        ImageView userPhotoImageView;
        @BindView(R.id.tv_user_name)
        TextView userNameTextView;
        @BindView(R.id.tv_phone)
        TextView phoneTextView;

        public ContactViewHolder(View itemView, final RecyclerOnItemClickListener recyclerOnItemClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerOnItemClickListener != null) {
                        recyclerOnItemClickListener.onItemClicked(v, getAdapterPosition());
                    }
                }
            });
        }
    }
}
