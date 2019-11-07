import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Ergänzen Sie hier eine Beschreibung für die Klasse Wand.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Wand extends Actor
{
    public Wand () {

    }

    public void resize() {
        Labyrinth w = (Labyrinth)(this.getWorld());
        if(w!=null) this.getImage().scale(w.getZellengroesse(), w.getZellengroesse());
    }
}