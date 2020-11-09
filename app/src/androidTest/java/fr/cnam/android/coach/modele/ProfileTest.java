package fr.cnam.android.coach.modele;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProfileTest {

    //Création profil
    private Profile profile = new Profile(null, 165,165, 35, 1);
    //resultat IMG
    private float img = (float) 32.2;
    //message
    private String message = "trop éleve";

    @Test
    public void getImg() {
        assertEquals(img, profile.getImg(), (float)0.1);
    }

    @Test
    public void getMessage() {
        assertEquals(message, profile.getMessage());
    }
}