package org.pursuit.postmortemunit04assessment.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.pursuit.postmortemunit04assessment.MainNavigatorInterface;
import org.pursuit.postmortemunit04assessment.R;
import org.pursuit.postmortemunit04assessment.model.Animal;

public class AnimalViewHolder extends RecyclerView.ViewHolder {

    private final MainNavigatorInterface navigator;

    private ImageView imageView;
    private TextView textView;

    //Pass in interface of changing fragments or activities.
    //Save it as a field for the onBind method.
    public AnimalViewHolder(@NonNull View itemView, @NonNull MainNavigatorInterface navigator) {
        super(itemView);
        this.navigator = navigator;

        imageView = itemView.findViewById(R.id.animal_image_image_view);
        textView = itemView.findViewById(R.id.animal_title_text_view);
    }

    public void onBind(final Animal animal) {
        textView.setText(animal.getName());
        Picasso.get().load(animal.getImage()).into(imageView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("onClick", "isWorking" + animal.getName() + animal.getImage() + animal.getWiki());

                navigator.toSecondActivity(animal);
            }
        });
    }
}
