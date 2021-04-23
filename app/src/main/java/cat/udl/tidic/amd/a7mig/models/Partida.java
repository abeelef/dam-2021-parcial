package cat.udl.tidic.amd.a7mig.models;
import java.util.List;

public class Partida {

    private static final String TAG = "Partida";
    private List<Jugador> jugadores;
    private Baraja baraja;



    public Partida(){
        this.baraja = new Baraja();
        baraja.barajar();
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public Carta cogerCarta(){
        return baraja.siguiente();
    }


}


