package com.example.animations;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class SecondFragment extends Fragment {

    private ImageView playPauseButton;
    private Animation playPauseAnimation;
    private boolean slShowOn = false;

    private ImageView slideShowImage;
    private AnimationDrawable slideShowAnimation;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        playPauseButton = view.findViewById(R.id.playPause);
        playPauseAnimation = AnimationUtils.loadAnimation(this.getContext(), R.anim.play_pause);

        slideShowImage = view.findViewById(R.id.slideImage);
        slideShowImage.setBackgroundResource(R.drawable.slideshow);
        slideShowAnimation = (AnimationDrawable) slideShowImage.getBackground();

        playPauseAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (slShowOn) {
                    playPauseButton.setImageResource(android.R.drawable.ic_media_play);
                    slShowOn = false;
                    // pause the slide show
                    slideShowAnimation.stop();
                } else {
                    playPauseButton.setImageResource(android.R.drawable.ic_media_pause);
                    slShowOn = true;
                    // start the slide show
                    slideShowAnimation.start();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        playPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playPauseButton.startAnimation(playPauseAnimation);
            }
        });

        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }
}