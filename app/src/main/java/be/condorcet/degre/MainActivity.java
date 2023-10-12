package be.condorcet.degre;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //déclarer zone graphique , mais on va se concentrer sur les zones d'éditions (edittexte)
    EditText eddf, eddc;
    Button btfc, btcf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //attach editText Here to the UI ones
        eddf = findViewById(R.id.idedfahr);
        eddc = findViewById(R.id.idedcel);
        btfc = findViewById(R.id.idbutfc);
        btcf = findViewById(R.id.idbutcf);

        //observateur de typing clavier
        //onKey Listener , classe abstraite
        View.OnKeyListener okl = new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

                /* V1 -> sure method
                if(view.getId() == R.id.idedfahr){
                    eddc.setText("");
                }
                else eddf.setText("");

                //looks like observer, but don't totally act like it
                //fire event -> déclanche enchainement
                return false;
                //return false, si true, coupe message et ne fait plus rien après action

                 */

                //v2
                if(view == eddf){
                    eddc.setText("");
                    btcf.setEnabled(false);
                    btfc.setEnabled(true);
                }
                else {
                    eddf.setText("");
                    btcf.setEnabled(true);
                    btfc.setEnabled(false);
                }

                return false;

                /*
                V3
                if(view.getId() == eddf.getId()){
                //same as above
                }
                 */
            }
        };

        eddf.setOnKeyListener(okl);
        eddc.setOnKeyListener(okl);
    }

    public void fahrToCels(View v){
        //aller chercher la chaine de caractères (ALWAYS STRING) sur la zone idedfahr (où o, inscrit les degré)
        String vs = eddf.getText().toString();
        //getText est un objet qui encapsule la string -> ui+text, need to be extracted with toString
        try{
            double vf = Double.parseDouble(vs);
            double vc = (vf - 32) / 1.8;
            eddc.setText(String.format("%.1f",vc));
        }catch (NumberFormatException e){
            Toast.makeText(this ,"Erreur de conversion",Toast.LENGTH_LONG).show();
        }

    }

    public void celsToFahr(View v){
        //same as above
        String vs = eddc.getText().toString();
        try{
            double vc = Double.parseDouble(vs);
            double vf = (vc*1.8) + 32;
            eddf.setText(String.format("%.1f",vf));

        }catch (NumberFormatException e){
            Toast.makeText(this ,"Erreur de conversion",Toast.LENGTH_LONG).show();
        }
    }

    public void conversion(View v){
        //getId return number, check which id it correspond to
        if(v.getId() == R.id.idbutfc){
            //fahrToCels(v);
            //aller chercher la chaine de caractères (ALWAYS STRING) sur la zone idedfahr (où o, inscrit les degré)
            String vs = eddf.getText().toString();
            //getText est un objet qui encapsule la string -> ui+text, need to be extracted with toString
            try{
                double vf = Double.parseDouble(vs);
                double vc = (vf - 32) / 1.8;
                eddc.setText(String.format("%.1f",vc));
            }catch (NumberFormatException e){
                Toast.makeText(this ,"Erreur de conversion",Toast.LENGTH_LONG).show();
            }
        }
        else {
            //celsToFahr(v);
            String vs = eddc.getText().toString();
            try{
                double vc = Double.parseDouble(vs);
                double vf = (vc*1.8) + 32;
                eddf.setText(String.format("%.1f",vf));

            }catch (NumberFormatException e){
                Toast.makeText(this ,"Erreur de conversion",Toast.LENGTH_LONG).show();
            }
        }
    }


}