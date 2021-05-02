package cat.udl.tidic.amd.a7mig;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.List;


public class GameBeginDialog extends DialogFragment {

    private static final String TAG = "GameBeginDialog";
    private LinearLayout gameSettingLayout;
    private EditText players;
    private int jugadores;
    private View rootView;
    private GameActivity activity;

    public static GameBeginDialog newInstance(GameActivity activity) { //dialog pot accedir als publics de gameactivity
        GameBeginDialog dialog = new GameBeginDialog();
        dialog.activity = activity;
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        initViews();
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setView(rootView)
                .setTitle(R.string.game_dialog_title)
                .setCancelable(false)
                .setPositiveButton(R.string.start, null)
                .create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        alertDialog.setOnShowListener(dialog -> {
            onDialogShow(alertDialog);
        });
        return alertDialog;
    }

    @SuppressLint("InflateParams")
    private void initViews() {

        rootView = LayoutInflater.from(getContext())
                .inflate(R.layout.game_begin_dialog, null, false);

        gameSettingLayout = rootView.findViewById(R.id.gameEndLayout);
        players = rootView.findViewById(R.id.numeroJugadorsET);
        addTextWatchers();
    }

    private void onDialogShow(AlertDialog dialog) {
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(v -> {
            onDoneClicked();
        });
    }
    
    private void onDoneClicked() { //També podria fer-se el metode de resetejar amb un break
        //faltaria implementar logica de quan els EditTexts estiguin buits que el botó "simular" no petes.
        List<String> noms = new ArrayList<>(); //com resetejem al fer click larraylist, si un valor d'entrada de nom;aposta no es vàlid, es sobreescriura en cada posicio.
        List<Integer> apostes = new ArrayList<>();
        boolean controlador = true;

        for (int i = 0; i < jugadores; i++) {
            EditText editText = gameSettingLayout.findViewById(20000 + i);
            String value = editText.getText().toString();
            String nom = value.split(";")[0];
            //comprovem requisit 2
            noms.add(i, nom);
            if (!nom.matches("^[a-z]{3,7}$")) {
            editText.setError("No cumpleix els requisits mínims pel nom");
            controlador = false;
            }
            int aposta = Integer.parseInt(value.split(";")[1]);
            apostes.add(i,aposta);
            if(aposta < 5 && aposta > 1000){
                editText.setError("No cumpleix els requisits mínims d'aposta");
                controlador = false;
            }

            Log.d(TAG, "noms:"+ noms.toString());
            Log.d(TAG, "apostes:"+ apostes.toString());
        }
        if(controlador){
        dismiss();
        activity.canviJugador(noms,apostes);
        }
    }




    private void addTextWatchers() {
        players.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    jugadores = Integer.parseInt(s.toString());
                    for (int i = 0; i < jugadores; i++) {
                        EditText nomET = new EditText(rootView.getContext());
                        nomET.setHint(R.string.player_hint);
                        nomET.setId(20000+i);
                        gameSettingLayout.addView(nomET);
                    }
                }
                catch(Exception e) {
                    gameSettingLayout.removeAllViews();
                    gameSettingLayout.addView(players);
                }
            }
        });

}
}
