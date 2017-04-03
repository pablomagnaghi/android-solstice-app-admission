package com.contacts.ui.adapters;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.subjects.PublishSubject;


public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactViewHolder> {

    private List<Contact> mContacts;

    private Context mContext;

    private PublishSubject<Contact> mViewClickSubject = PublishSubject.create();

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
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemListView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacts, parent, false);
        return new ContactViewHolder(itemListView);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        holder.onBindView(mContext, mContacts.get(position), mViewClickSubject);
    }

    @Override
    public int getItemCount() {
        return mContacts != null ? mContacts.size() : 0;
    }

    public Observable<Contact> getViewClickedObservable() {
        return mViewClickSubject.asObservable();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_user_photo)
        ImageView userPhotoImageView;
        @BindView(R.id.tv_user_name)
        TextView userNameTextView;
        @BindView(R.id.tv_phone)
        TextView phoneTextView;

        public ContactViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBindView(Context context, final Contact contact, PublishSubject<Contact> subject){
            Glide.with(context)
                    .load(contact.getSmallImageURL())
                    .placeholder(R.drawable.default_user_photo)
                    .error(R.drawable.default_user_photo)
                    .fitCenter()
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(userPhotoImageView);

            userNameTextView.setText(contact.getName());
            if (contact.hasPhone()) {
                phoneTextView.setText(contact.getDefaultPhone());
            }

            itemView.setOnClickListener(v -> mViewClickSubject.onNext(contact));
        }
    }
}
