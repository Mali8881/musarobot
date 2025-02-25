package com.example.musarobots.vivable;

import android.content.Context;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.musarobots.R;
import com.example.musarobots.databinding.ActivityMainBinding;
import com.example.musarobots.robots.Yellow_roboy;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    Integer length;
    int result = 0;
    boolean isHiddenD = false;
    boolean isEnergiesZero = false;
    int b_fight = 0;

    private MediaPlayer.OnCompletionListener mCompletitionListener = new MediaPlayer.OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                mediaPlayer.pause();
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                length = mediaPlayer.getCurrentPosition();
            }
        }
    };

    LottieAnimationView lotty_robot1, lotty_robot2;
    LottieAnimationView lotty_robot2d;
    Random rn1 = new Random();
    int ener1 = rn1.nextInt(7800 + 200);
    Random ls1 = new Random();
    int las1 = ls1.nextInt(50 + 70);
    Random rn2 = new Random();
    int ener2 = rn2.nextInt(7800 + 200);
    Random ls2 = new Random();
    int las2 = ls2.nextInt(50 + 70);

    Red_robot red1 = new Red_robot();
    Yellow_roboy yellow2 = new Yellow_roboy();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            result = audioManager.requestAudioFocus(new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN).build());
        } else {
            result = audioManager.requestAudioFocus(
                    onAudioFocusChangeListener, AudioManager.STREAM_MUSIC,
                    AudioManager.AUDIOFOCUS_GAIN);
        }

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        lotty_roboy1 = findViewById(R.id.lottty_drive1);
        lotty_robot2 = findViewById(R.id.lottty_drive2);
        lotty_robot2d = findViewById(R.id.lottty_drive2d);

        lotty_roboy1.setAnimation(R.raw.red_robot);
        lotty_robot2.setAnimation(R.raw.y_robot);
        lotty_robot2d.setAnimation(R.raw.y_robot);

        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                binding.energy1.setText(String.valueOf(red1.getEnergy_Volume()));
                binding.lasers2.setText(String.valueOf(git add .git initred1.getLasers()));
                binding.energy2.setText(String.valueOf(yellow2.getEnergy_Volume()));
                binding.lasers2.setText(String.valueOf(yellow2.getLasers()));
                binding.btnStart.setVisibility(View.INVISIBLE);
                binding.imagesRobotsLayout.setVisibility(View.GONE);
                binding.lottyDriver1.setVisibility(View.INVISIBLE);
                binding.lottyDriver2.setVisibility(View.INVISIBLE);


                binding.redFightUp.setVisibility(View.VISIBLE);
                binding.redFightDawn.setVisibility(View.VISIBLE);
                binding.yellowFightUp.setVisibility(View.VISIBLE);
                binding.yellowFightDawn.setVisibility(View.VISIBLE);
                binding.btnTransperent.setVisibility(View.VISIBLE);
            }
        });

        binding.redFightUp.setOnClickListener(v2 -> {
            b_fight = 1;
            if (lotty_robot2.getVisibility() == View.INVISIBLE) {
                // Дополнительные действия
            }
        });
    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }
}
