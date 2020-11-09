package fr.cnam.android.coach.controleur;

import android.content.Context;

import org.json.JSONArray;

import java.util.Date;

import fr.cnam.android.coach.modele.AccesDistant;
import fr.cnam.android.coach.modele.AccesLocal;
import fr.cnam.android.coach.modele.Profile;
import fr.cnam.android.coach.outils.Serializer;

public final class Controle {

    private static Controle instance = null;
    private static Profile profile;
    private static String nomFic = "saveprofil";
    //private static AccesLocal accesLocal;
    private static AccesDistant accesDistant;

    /**
     * Constructeur private
     */
    private Controle(){
        super();
    }

    /**
     * Creation de l'instance
     * @return instance
     */
    public static final Controle getInstance(Context context){
        if(Controle.instance == null){
            Controle.instance = new  Controle();
            //accesLocal = new AccesLocal(context);
            accesDistant = new AccesDistant();
            //profile  = accesLocal.recupDernier();
            //accesDistant.envoi("dernier", new JSONArray());
            //recupSerialize(context);
        }
        return Controle.instance;
    }

    /**
     * Création du profil
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe 1 pour homme et 0 pour femme
     */
    public  void creerProfile(Integer poids, Integer taille, Integer age, Integer sexe, Context context){
        profile = new Profile(new Date(), poids, taille, age, sexe);
        //accesLocal.ajout(profile);
        accesDistant.envoi("enreg", profile.convertToJsonArray());
        //Serializer.serializer(nomFic,profile, context);
    }

    /**
     * recupration img de profil
     * @return img
     */
    public float getImg(){
        return profile.getImg();
    }

    /**
     * recuperation message de profil
     * @return message
     */
    public String getMessager(){
        return profile.getMessage();
    }

    /**
     * Récupération de l'object sérializé (le profil)
     * @param context
     */
    private static void recupSerialize(Context context){
        profile = (Profile)Serializer.deSerialize(nomFic, context);
    }

    public Integer getPoids(){
        if(profile ==null){
            return null;
        }else{
            return profile.getPoids();
        }
    }
    public Integer getTaille(){
        if(profile ==null){
            return null;
        }else{
            return profile.getTaille();
        }
    }
    public Integer getAge(){
        if(profile ==null){
            return null;
        }else{
            return profile.getAge();
        }
    }
    public Integer getSexe(){
        if(profile ==null){
            return null;
        }else{
            return profile.getSexe();
        }
    }
}
