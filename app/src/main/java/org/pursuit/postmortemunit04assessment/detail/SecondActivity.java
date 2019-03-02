package org.pursuit.postmortemunit04assessment.detail;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.pursuit.postmortemunit04assessment.R;

public class SecondActivity extends AppCompatActivity implements DetailFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String name = intent.getStringExtra("animal");
        String image = intent.getStringExtra("image");
        String wiki = intent.getStringExtra("wiki");

        //Passing in data from activity to fragment through its static method.
        final DetailFragment detailFragment = DetailFragment.newInstance(name, image, wiki);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_container, detailFragment)
                .commit();
    }

    @Override
    public void onFragmentInteraction(String website) {
        Uri uri = Uri.parse(website);
        final Intent webIntent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(webIntent);
    }
}
