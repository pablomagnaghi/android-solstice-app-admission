package com.contacts.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.contacts.R;
import com.contacts.model.Contact;
import com.contacts.presenter.MainPresenter;
import com.contacts.ui.adapters.ContactsAdapter;
import com.contacts.utils.Constants;
import com.contacts.utils.DialogFactory;
import com.contacts.view.MainMvpView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject
    MainPresenter mMainPresenter;
    @Inject
    ContactsAdapter mContactsAdapter;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getApplicationComponent().inject(this);

        mContactsAdapter.getViewClickedObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(contact -> navigateToDetails(contact))
                .subscribe();

        mRecyclerView.setAdapter(mContactsAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new SlideInUpAnimator());

        mMainPresenter.attachView(this);
        mMainPresenter.restoreState(savedInstanceState);

        mSwipeRefresh.setOnRefreshListener(() -> mMainPresenter.loadContactsApi());
    }

    private void navigateToDetails(Contact contact)
    {
        Intent intent = new Intent(this, ContactDetailsActivity.class);
        intent.putExtra(Constants.CONTACT_INTENT, contact);
        startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMainPresenter.saveState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.detachView();
    }

    /** MVP View methods implementation **/

    @Override
    public void showContacts(List<Contact> contacts) {
        mSwipeRefresh.setRefreshing(false);
        mContactsAdapter.setContacts(contacts);
        mContactsAdapter.notifyDataSetChanged();
        if (contacts.isEmpty()) {
            Toast.makeText(this, R.string.empty_contacts, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showError() {
        mSwipeRefresh.setRefreshing(false);
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_contacts))
                .show();
    }
}
