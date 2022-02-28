package com.example.thehillreloaded.menu;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import com.example.thehillreloaded.R;
import com.example.thehillreloaded.accesso.ModalitaAccessoActivity;
import com.example.thehillreloaded.animazioni.Animazioni;

public class GiocatoreSingoloActivity extends Animazioni implements View.OnClickListener{

    //Variabili
    public CardView modalitaClassica, modalitaPowerUp;
    public ImageView indietro;
    public static boolean classica, powerUp = false;

    //Chiama l'animazione all'avvio dell'activity
    @Override
    protected void onStart(){
        super.onStart();
        runAnimationSlideIn(modalitaClassica);
        runAnimationSlideIn(modalitaPowerUp);
        SharedPreferences preferenze = getSharedPreferences("salva1",MODE_PRIVATE);
        boolean b = preferenze.getBoolean("musica",true);
        if(b) {
            if (VolumeActivity.flag == 0) {
                MusicPlayer.playMusic(this, R.raw.menu_music);
                ModalitaAccessoActivity.isPlayingAudio = true;
                VolumeActivity.flag = 1;
            }
        }
    }

    //Chiama l'animazione alla pausa dell'activity
    @Override
    protected void onPause(){
        super.onPause();
        runAnimationSlideOut(modalitaClassica);
        runAnimationSlideOut(modalitaPowerUp);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giocatoresingolo);

        //Imposta l'orientamento portrait come obbligatorio
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Trova le view tramite l'id e le assegna alle variabili
        modalitaClassica = findViewById(R.id.modalitaClassica);
        modalitaPowerUp = findViewById(R.id.modalitaPowerUp);
        indietro = findViewById(R.id.indietro);

        //Imposta metodo di callback quando la view viene cliccata
        modalitaClassica.setOnClickListener(this);
        modalitaPowerUp.setOnClickListener(this);
        indietro.setOnClickListener(this);

        //Animazione pulsanti
        clickButtonAnimation(modalitaClassica);
        clickButtonAnimation(modalitaPowerUp);
        clickButtonAnimation(indietro);
    }

    //Crea l'intent per passare all'activity successiva dopo la pressione di un pulsante
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()){
            case R.id.modalitaClassica:
                classica = true;
                i = new Intent(this, DifficoltaActivity.class);
                Bundle b = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
                startActivity(i, b);
                finish();
                break;
            case R.id.modalitaPowerUp:
                powerUp = true;
                i = new Intent(this, DifficoltaActivity.class);
                Bundle b1 = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
                startActivity(i, b1);
                finish();
                break;
            case R.id.indietro:
                i = new Intent(this, MenuActivity.class);
                Bundle b2 = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
                startActivity(i, b2);
                finish();
                break;

        }
    }
}