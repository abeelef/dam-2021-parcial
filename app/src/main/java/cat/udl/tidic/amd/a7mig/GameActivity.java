package cat.udl.tidic.amd.a7mig;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cat.udl.tidic.amd.a7mig.models.Carta;
import cat.udl.tidic.amd.a7mig.models.Jugador;
import cat.udl.tidic.amd.a7mig.models.Partida;
import cat.udl.tidic.amd.a7mig.preferences.PreferenceProvider;

import static cat.udl.tidic.amd.a7mig.preferences.PreferenceProvider.SHARED_PREFERENCES;
import static cat.udl.tidic.amd.a7mig.preferences.PreferenceProvider.providePreferences;


public class GameActivity extends AppCompatActivity {

    private Partida partida = new Partida();
    private static final String GAME_BEGIN_DIALOG_TAG = "game_dialog_tag";
    private static final String GAME_END_DIALOG_TAG = "game_end_dialog_tag";
    private List<Jugador> jugador = new ArrayList<>();
    private TextView nom;
    private TextView puntuacio;
    private TextView aposta;
    private int jugadorActual = 0;
    private ImageView dibuixCarta;
    private Carta carta;
    private Button buttonPlantar;
    private Button buttonSeguir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        nom = findViewById(R.id.textView);
        puntuacio = findViewById(R.id.textView3);
        aposta = findViewById(R.id.textView2);
        dibuixCarta = findViewById(R.id.UltimaCarta);
        buttonPlantar = findViewById(R.id.button2);
        buttonSeguir = findViewById(R.id.seguirButton);

        buttonPlantar.setOnClickListener(v -> {
            canviJugador();
        });

        buttonSeguir.setOnClickListener(v -> {
            tirada();
            if(jugador.get(jugadorActual).getPuntuacion() >= 7.5){
                canviJugador();
            }
        });
    }

    public void initView(){
        promptForPlayer();
    }

    private void promptForPlayer() {
        GameBeginDialog dialog = GameBeginDialog.newInstance(this); //Amb això ja es pot obtenir el que tnguem a BeginDialog
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(), GAME_BEGIN_DIALOG_TAG);
    }

    public static void Banca(){
        int banca;
        banca = PreferenceProvider.providePreferences().getInt("banca",-1);
        if( banca == -1){
            PreferenceProvider.providePreferences().edit().putInt("banca", 30000).apply();
            Log.d(SHARED_PREFERENCES, "no hi ha cap banca existent");
        }
        Log.d(SHARED_PREFERENCES, "A la banca hi ha " + PreferenceProvider.providePreferences().getInt("banca",-1) + " euros.");
    }

    private void finalPartida(){
        double banca;
        for(int i = 0; i < jugador.size();i++){
            banca = PreferenceProvider.providePreferences().getInt("banca", -1);
            if(jugador.get(i).getPuntuacion() == 7.5){
                banca = banca - jugador.get(i).getApuesta()*2;
            }else if(jugador.get(i).getPuntuacion() < 7.5){
                banca = banca + jugador.get(i).getApuesta()*0.1;
            }else{
                banca = banca + jugador.get(i).getApuesta();
            }
            PreferenceProvider.providePreferences().edit().putInt("banca", (int) banca).apply();
        }
        GameEndDialog dialog = GameEndDialog.newInstance(this, jugador);
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(), GAME_END_DIALOG_TAG);
    }

    public void canviJugador(List<String> noms, List<Integer> apostes){
        for(int i = 0; i < noms.size();i++){
            jugador.add(new Jugador(noms.get(i), apostes.get(i)));
        }
        partida.setJugadores(jugador);
        iniciPartida();
    }

    public void actualitzarPantalla(){
        nom.setText(jugador.get(jugadorActual).getNombre());
        aposta.setText(Integer.toString(jugador.get(jugadorActual).getApuesta()));
        puntuacio.setText(Double.toString(jugador.get(jugadorActual).getPuntuacion()));
        dibuixCarta.setImageResource(carta.getResource());
    }

    public void tirada(){
        carta = partida.cogerCarta();
        double puntuacio = jugador.get(jugadorActual).getPuntuacion();
        jugador.get(jugadorActual).setPuntuacion(puntuacio + carta.getValue()); //setejem el resultat d'aquest jugador
        actualitzarPantalla();
    }

    public void canviJugador(){
        jugadorActual = jugadorActual + 1;
        if (jugadorActual == jugador.size()){
            finalPartida();
        }else{
            tirada();
        }
    }

    public void iniciPartida(){
        tirada();
    }


}