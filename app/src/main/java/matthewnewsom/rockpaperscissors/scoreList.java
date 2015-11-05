package matthewnewsom.rockpaperscissors;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Matthew on 4/1/2015.
 */
public class scoreList extends DialogFragment {
    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        ArrayList<String> spPlayerMoves = getArguments().getStringArrayList("spPlayerMoves");
        ArrayList<String> spCompMoves = getArguments().getStringArrayList("spCompMoves");
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View scoreInflatedView = inflater.inflate(R.layout.score, null);

        if (spPlayerMoves != null) {
            final String pmove = spPlayerMoves.get(0);
            Log.v("SPplayer moves is" , pmove);


        }

        if (spCompMoves != null) {
            final String cmove = spCompMoves.get(0);
        }
        else {
            Log.v("SP comp moves is", "null");
            builder.setView(scoreInflatedView);
        }

        builder.setMessage("Score List")
                .setPositiveButton("O.K.", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Positive Button
//                        Log.v("SP Player move = ", pmove);
//                        Log.v("SP comp move = ", cmove);

                    }
                })
                .setNegativeButton("Clear", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });


        return builder.create();
    }


}
