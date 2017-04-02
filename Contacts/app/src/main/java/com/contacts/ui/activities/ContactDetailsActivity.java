package com.contacts.ui.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.contacts.R;
import com.contacts.model.Contact;
import com.contacts.presenter.ContactDetailsPresenter;
import com.contacts.utils.DialogFactory;
import com.contacts.view.ContactDetailsMvpView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactDetailsActivity extends BaseActivity implements ContactDetailsMvpView {

    @Inject
    ContactDetailsPresenter mContactDetailsPresenter;

    @BindView(R.id.iv_user_photo)
    ImageView userPhotoImageView;
    @BindView(R.id.tv_user_name)
    TextView userNameTextView;
    @BindView(R.id.tv_company)
    TextView companyTextView;
    @BindView(R.id.tv_mobile_phone)
    TextView mobilePhoneTextView;
    @BindView(R.id.tv_mobile_phone_label)
    TextView mobilePhoneLabelTextView;
    @BindView(R.id.tv_work_phone)
    TextView mobileWorkTextView;
    @BindView(R.id.tv_work_phone_label)
    TextView mobileWorkLabelTextView;
    @BindView(R.id.tv_home_phone)
    TextView mobileHomeTextView;
    @BindView(R.id.tv_home_phone_label)
    TextView mobileHomeLabelTextView;
    @BindView(R.id.tv_address)
    TextView addressTextView;
    @BindView(R.id.tv_birthday)
    TextView birthdayTextView;
    @BindView(R.id.tv_email)
    TextView emailTextView;
    @BindView(R.id.tv_website)
    TextView websiteTextView;


    Menu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        ButterKnife.bind(this);
        getApplicationComponent().inject(this);

        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mContactDetailsPresenter.attachView(this);
        mContactDetailsPresenter.loadContactDetails(getIntent());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
                return true;
        }
        return (super.onOptionsItemSelected(menuItem));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mMenu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contact_details, menu);
        mContactDetailsPresenter.loadFavorite();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

    /** MVP View methods implementation **/

    @Override
    public void showContactDetails(Contact contact) {
        Glide.with(this)
                .load(contact.getLargeImageURL())
                .placeholder(R.drawable.default_user_photo)
                .error(R.drawable.default_user_photo)
                .fitCenter()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(userPhotoImageView);

        userNameTextView.setText(contact.getName());
        companyTextView.setText(contact.getCompany());
        if (contact.hasMobilePhone()) {
            mobilePhoneTextView.setText(contact.getMobilePhone());
        }
        else {
            mobilePhoneTextView.setVisibility(View.GONE);
            mobilePhoneLabelTextView.setVisibility(View.GONE);
        }
        if (contact.hasWorkPhone()) {
            mobileWorkTextView.setText(contact.getWorkPhone());
        }
        else
        {
            mobileWorkTextView.setVisibility(View.GONE);
            mobileWorkLabelTextView.setVisibility(View.GONE);
        }
        if (contact.hasHomePhone()) {
            mobileHomeTextView.setText(contact.getHomePhone());
        }
        else {
            mobileHomeTextView.setVisibility(View.GONE);
            mobileHomeLabelTextView.setVisibility(View.GONE);
        }
        addressTextView.setText(contact.getAddressWithFormat());
        birthdayTextView.setText(contact.getBirthdateWithFormat());
        emailTextView.setText(contact.getEmail());
        websiteTextView.setText(contact.getWebsite());
        websiteTextView.setLinksClickable(true);
        websiteTextView.setLinkTextColor(Color.BLUE);
    }

    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_contact_details))
                .show();
    }

    @Override
    public void showFavorite() {
        if (mMenu != null) {
            MenuItem favoriteIcon = mMenu.findItem(R.id.i_fav_on);
            favoriteIcon.setVisible(true);
        }
    }
}
