import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Ergänzen Sie hier eine Beschreibung für die Klasse MyWorld.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Labyrinth extends World
{
    private static int breite = 30; // Anzahl Zellen in der Breite
    private static int hoehe = 20; // Anzahl Zellen in der Hoehe
    private static int zellenGroesse = 30;

    int rotation;
    int x;
    int y;

    /**
     * Konstruktor für Objekte der Klasse MyWorld
     * 
     */
    public Labyrinth()
    {    
        // Erstellt eine neue Welt mit 600x400 Zellen und einer Zell-Größe von 1x1 Pixeln.
        super(breite, hoehe, zellenGroesse);
        generateBackground();
        digLabyrinth();
        PacMan p = new PacMan();
        addObject(p,breite/2,hoehe-2);
        p.resize();
        Greenfoot.setSpeed(30);
    }

    public void setZellengroesse(int pixel) {
        zellenGroesse = pixel;
        Greenfoot.setWorld(new Labyrinth());
    }

    public void zoomIn() {
        setZellengroesse(zellenGroesse+10);
    }

    public void zoomOut() {
        setZellengroesse(zellenGroesse-10);
    }

    private void generateBackground() {
        removeObjects(getObjects(null));
        for(int x=0; x<breite; x++) {
            for(int y=0; y<hoehe; y++) {
                Wand w = new Wand();
                addObject(w,x,y);
                w.resize();
            }
        }
        digRahmen();
    }

    private void digRahmen() {
        for(int x=1; x<breite-1; x++) {
            removeObjects(getObjectsAt(x, 1, Wand.class));
        }
        for(int y=2; y<hoehe-2; y++) {
            removeObjects(getObjectsAt(breite-2, y, Wand.class));
        }
        for(int x=1; x<breite-1; x++) {
            removeObjects(getObjectsAt(x, hoehe-2, Wand.class));
        }
        for(int y=2; y<hoehe-2; y++) {
            removeObjects(getObjectsAt(1, y, Wand.class));
        }
    }

    public void digLabyrinth() { //von (1,1) bis (BREITE-1,HOEHE-1)
        int anzahlInnenWaende = (hoehe-4)*(breite-4);
        int zufall = anzahlInnenWaende/2+Greenfoot.getRandomNumber(anzahlInnenWaende/4);
        rotation = 1; //Blickrichtung: 0-Osten, 1-Norden, 2-Westen, 3-Sueden
        x = 3+Greenfoot.getRandomNumber(breite-6);
        y = hoehe-3;
        //removeObjects(getObjectsAt(x, y, Wand.class));
        while (zufall!=0) {
            if(dig()) {
                if(dig()){
                    if(dig()) {
                        rotation = Greenfoot.getRandomNumber(4);
                        zufall--;
                    }
                }
            }
            else {
                rotation = Greenfoot.getRandomNumber(4);
                if(x<=1) x=x+1;
                if(x>=breite-2) x=x-1;
                if(y<=1) y=y+1;
                if(y>=hoehe-2) y=y-1;
            }
        }
    }

    private boolean dig() {
        if((x>1 && x<breite-2) && (y>1 && y<hoehe-2)) {
            switch (rotation) {
                case 0: //Blickrichtung: 0-Osten
                {
                    x = x+1;
                    removeObjects(getObjectsAt(x, y, Wand.class));
                    break;
                }
                case 1: //Blickrichtung: 1-Norden
                {
                    y = y-1;
                    removeObjects(getObjectsAt(x, y, Wand.class));
                    break;
                }
                case 2: //Blickrichtung: 2-Westen
                {
                    x = x-1;
                    removeObjects(getObjectsAt(x, y, Wand.class));
                    break;
                }
                case 3: //Blickrichtung: 3-Sueden
                {
                    y = y+1;
                    removeObjects(getObjectsAt(x, y, Wand.class));
                    break;
                }
                default: break;
            }
            return true;
        }
        return false;
    }

    protected int getZellengroesse() {
        return zellenGroesse;
    }

    private void generateLabyrinth(int[][] welt)
    {
        removeObjects(getObjects(null));
        for(int x=0; x<breite; x++) {
            for(int y=0; y<hoehe; y++) {
                if(welt[0].length>x && welt.length>y) {
                    if(welt[y][x]==1) {
                        addObject(new Wand(),x,y);
                    }
                }
            }
        }
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void welt_01()
    {
        int[][] welt = {{0,0,0,1,0,0,0,0,0,0,0,0},
                {0,0,0,1,0,0,0,0,0,0,0,0},
                {0,0,0,1,1,1,1,1,0,0,0,0},
                {0,0,0,0,0,0,0,1,0,0,0,0},
                {0,0,0,0,0,0,0,1,0,0,0,0},
                {0,0,0,0,0,1,1,1,0,0,0,0},
                {0,0,0,0,0,1,0,0,0,0,0,0},
                {0,0,0,0,0,1,1,1,0,0,0,0},
            };
        generateLabyrinth(welt);
    }
}