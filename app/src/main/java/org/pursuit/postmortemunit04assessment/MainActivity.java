package org.pursuit.postmortemunit04assessment;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.pursuit.postmortemunit04assessment.detail.SecondActivity;
import org.pursuit.postmortemunit04assessment.model.Animal;
import org.pursuit.postmortemunit04assessment.model.EchinodermAnimals;
import org.pursuit.postmortemunit04assessment.network.AnimalService;
import org.pursuit.postmortemunit04assessment.network.RetrofitSingleton;
import org.pursuit.postmortemunit04assessment.recyclerview.AnimalAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements MainNavigatorInterface{

    public static final String NAME_KEY = "name";
    public static final String IMAGE_KEY = "image";
    public static final String WIKI_KEY = "wiki";

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setViews();

        retrofitEchinodermCall();

    }

    //Sets the views separately from network call response.
    //This way we only set them once instead of each time the network call finishes.
    private void setViews() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void retrofitEchinodermCall() {
        //Casting the activity as an interface to pass into the activity since it's inside an anonymous class.
        final MainNavigatorInterface navigator = this;

        final Retrofit retrofit = RetrofitSingleton.getInstance();
        final AnimalService animalService = retrofit.create(AnimalService.class);
        animalService.getAnimals().enqueue(new Callback<EchinodermAnimals>() {
            @Override
            public void onResponse(Call<EchinodermAnimals> call, Response<EchinodermAnimals> response) {
                List<Animal> animalList = response.body().getMessage();
                Log.d("TAG", "onResponse" + animalList.get(0));

                final AnimalAdapter adapter = new AnimalAdapter(animalList, navigator);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<EchinodermAnimals> call, Throwable t) {
                Log.d("TAG", "onFailure" + t.getMessage());
            }
        });

    }

    //All intent/activity related code should be placed inside of activities and not ViewHolders/fragments.
    @Override
    public void toSecondActivity(@Nullable Animal animal) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(NAME_KEY, animal.getName());
        intent.putExtra(IMAGE_KEY, animal.getImage());
        intent.putExtra(WIKI_KEY, animal.getWiki());
        startActivity(intent);

    }
}
