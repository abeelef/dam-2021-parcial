package cat.udl.tidic.amd.a7mig;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.List;

import cat.udl.tidic.amd.a7mig.models.Jugador;
import cat.udl.tidic.amd.a7mig.preferences.PreferenceProvider;


public class GameEndDialog extends DialogFragment {

    private static final String TAG = "GameEndDialog";
    private List<Jugador> jugadors;
    private View rootView;
    private GameActivity activity;

    public static GameEndDialog newInstance(GameActivity activity, List<Jugador> jugadors) {
        GameEndDialog dialog = new GameEndDialog();
        dialog.activity = activity;
        dialog.jugadors = jugadors;
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
                .setPositiveButton(R.string.end, ((dialog, which) -> onNewGame()))
                .create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        return alertDialog;
    }

    @SuppressLint("InflateParams")
    private void initViews() {

        rootView = LayoutInflater.from(getContext())
                .inflate(R.layout.game_end_dialog, null, false);
        LinearLayout gameEndLayout = rootView.findViewById(R.id.gameEndLayout);

        for (int i=0; i<jugadors.size(); i++){
            Jugador j = jugadors.get(i);
            TextView resum = new EditText(rootView.getContext());
            resum.setText(j.toString());
            gameEndLayout.addView(resum);
        }

        TextView resum = new EditText(rootView.getContext());
        String text = "Banca:  " + PreferenceProvider.providePreferences().getInt("banca",-1) + " euros.";
        resum.setText(text);
        gameEndLayout.addView(resum);

    }

    private void onNewGame() {
        dismiss();
        activity.initView();
    }

}
