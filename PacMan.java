import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Ergänzen Sie hier eine Beschreibung für die Klasse PacMan.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class PacMan extends Actor
{

    public PacMan() {
        setRotation(0);
    }

    /**
     * Act - tut, was auch immer PacMan tun will. Diese Methode wird aufgerufen, 
     * sobald der 'Act' oder 'Run' Button in der Umgebung angeklickt werden. 
     */
    public void act() 
    {
        if(Greenfoot.isKeyDown("left")) {
            if(getRotation()!=180){
                setRotation(180);
                this.getImage().mirrorVertically();
            }
            move(1);
        }
        if(Greenfoot.isKeyDown("right")) {
            if(getRotation()!=0){
                setRotation(0);
                this.getImage().mirrorVertically();
            }
            move(1);
        }
        if(Greenfoot.isKeyDown("up")) {
            if(getRotation()!=270){
                setRotation(270);
            }
            move(1);
        }
        if(Greenfoot.isKeyDown("down")) {
            if(getRotation()!=90){
                setRotation(90);
            }
            move(1);
        }
    } 

    protected void resize() {
        Labyrinth w = (Labyrinth)(this.getWorld());
        if(w!=null) this.getImage().scale(w.getZellengroesse(), w.getZellengroesse());
    }
}