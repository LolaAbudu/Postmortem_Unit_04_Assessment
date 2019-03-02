package org.pursuit.postmortemunit04assessment.detail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.pursuit.postmortemunit04assessment.R;

public class DetailFragment extends Fragment {

    private static final String NAME_KEY = "name";
    private static final String IMAGE_KEY = "image";
    private static final String WIKI_KEY = "wiki";

    private String name;
    private String image;
    private String wiki;

    private OnFragmentInteractionListener fragmentInterface;

    public DetailFragment() {
        // Required empty public constructor
    }

    //Passing in data to Fragment through its static method parameters.
    public static DetailFragment newInstance(@Nullable String name,
                                             @Nullable String image,
                                             @Nullable String wiki) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(NAME_KEY, name);
        args.putString(IMAGE_KEY, image);
        args.putString(WIKI_KEY, wiki);
        fragment.setArguments(args);
        return fragment;
    }

    // Get interface/listener from parent Activity in onAttach, cast it from Context to listener class
    // and save it as a field.
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof DetailFragment.OnFragmentInteractionListener) {
            fragmentInterface = (DetailFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    // Retrieving data that was passed in within the OnCreate
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(NAME_KEY);
            image = getArguments().getString(IMAGE_KEY);
            wiki = getArguments().getString(WIKI_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final TextView nameTextView = view.findViewById(R.id.display_text_view);
        final ImageView imageView = view.findViewById(R.id.display_image_view);
        final Button sendButton = view.findViewById(R.id.display_button);

        nameTextView.setText(name);
        Log.d("tag", "works" + name + wiki);
        Picasso.get().load(image).into(imageView);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentInterface.onFragmentInteraction(wiki);
            }
        });

    }

    // Nullify the listener on detach to prevent memory leaks
    @Override
    public void onDetach() {
        super.onDetach();
        fragmentInterface = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String website);
    }
}


//model, network and rv goes inside list