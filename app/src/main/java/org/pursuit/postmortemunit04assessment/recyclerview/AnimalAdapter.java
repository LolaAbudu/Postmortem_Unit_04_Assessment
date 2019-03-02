package org.pursuit.postmortemunit04assessment.recyclerview;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.pursuit.postmortemunit04assessment.MainNavigatorInterface;
import org.pursuit.postmortemunit04assessment.R;
import org.pursuit.postmortemunit04assessment.model.Animal;

import java.util.List;

public class AnimalAdapter  extends RecyclerView.Adapter<AnimalViewHolder>{

    //These fields can all be final IF they're being instantiated in the constructor
    private List<Animal> animalList;
    private final MainNavigatorInterface navigator;

    //Pass in interface through adapter constructor if ViewHolder needs to change fragments or activities.
    //Save it as a field to pass into the ViewHolder's constructor later on.
    public AnimalAdapter(@Nullable List<Animal> animalList, @NonNull MainNavigatorInterface navigator) {
        this.animalList = animalList;
        this.navigator = navigator;
    }

    @NonNull
    @Override
    public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        final View childview = LayoutInflater.from(parent.getContext()).inflate(R.layout.animal_itemview, parent, false);
        return new AnimalViewHolder(childview, navigator);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalViewHolder animalViewHolder, int position) {
        //Pass in model instead of specific properties of the model.
        animalViewHolder.onBind(animalList.get(position));
    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }
}
