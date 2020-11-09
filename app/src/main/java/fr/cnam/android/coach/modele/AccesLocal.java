package fr.cnam.android.coach.modele;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

import fr.cnam.android.coach.outils.MySQLiteOpenHelper;

public class AccesLocal {

    //propriétes
    private String nomBase = "dbCoache.sqlite";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase db;

    /**
     * Constructeur
     * @param context
     */
    public AccesLocal(Context context){
        accesBD = new MySQLiteOpenHelper(context, nomBase, null, versionBase);
    }

    /**
     * ajout d'un profile dans la DB
     * @param profile
     */
    public void ajout(Profile profile){
        db = accesBD.getWritableDatabase();
        String req ="insert into profile (dateMesure, poids, taille, age, sexe) values ";
        req += "(\""+profile.getDateMesure()+"\","+profile.getPoids()+","+profile.getTaille()+","+profile.getAge()+","+profile.getSexe()+")";
        db.execSQL(req);
    }

    /**
     * Récuperation du dernier profil de la BD
     * @return
     */
    public Profile recupDernier(){
        db = accesBD.getReadableDatabase();
        Profile profile = null;
        String req ="select * from profile";
        Cursor curseur = db.rawQuery(req, null);
        curseur.moveToLast();
        if(!curseur.isAfterLast()){
            Date date = new Date();
            Integer poids = curseur.getInt(1);
            Integer taille = curseur.getInt(2);
            Integer age = curseur.getInt(3);
            Integer sexe = curseur.getInt(4);
            profile = new Profile(date, poids, taille, age, sexe);
        }
        curseur.close();
        return profile;
    }

}
