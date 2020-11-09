package fr.cnam.android.coach.modele;

import android.util.Log;

import org.json.JSONArray;

import fr.cnam.android.coach.outils.AccesHTTP;
import fr.cnam.android.coach.outils.AsyncResponse;

public class AccesDistant implements AsyncResponse {

    //constante
    private static final String SERVERADDR ="http://192.168.1.17/coach/serveurcoach.php";

    public AccesDistant(){
        super();
    }

    /**
     * Retour du serveur distant
     * @param output
     */
    @Override
    public void processFinish(String output) {
        Log.d("serveur","******Tu est là********"+output);
        //découpage du message reçu avec %
        String[] message = output.split("%");
        //dans message[0] : "enreg", "dernier", "Erreur !"
        //dans message[1] : reste du message

        //s'il y a 2 cases
        if(message.length>1){
            if(message[0].equals("enreg")){
                Log.d("enreg", "*********"+message[1]);
            }else{
                if(message[0].equals("dernier")){
                    Log.d("dernier", "*********"+message[1]);
                }else{
                    if(message[0].equals("Erreur !")){
                        Log.d("Erreur !", "*********"+message[1]);
                    }
                }
            }
        }
    }

    public void envoi(String operation, JSONArray lesdonneesJSON){
        AccesHTTP accesDonnees = new AccesHTTP();
        //Lien de délegation
        accesDonnees.delegate =this;
        //ajout parametres
        accesDonnees.addParam("operation", operation);
        accesDonnees.addParam("lesdonnees", lesdonneesJSON.toString());
        //appel au serveur
        accesDonnees.execute(SERVERADDR);
    }
}
