package cat.udl.tidic.amd.a7mig.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baraja {

    private List<Carta> cartas;
    private int index;

    public void barajar(){
        cartas = new ArrayList<>();
        crear();
        Collections.shuffle(cartas);
        index=0;
    }
    
    private  void crear(){
        for (int i=1; i<=12; i++){
            if(i!=8 && i!=9) {
                for (Palos palo : Palos.values()) {
                    Carta c = new Carta(i, palo);
                    this.cartas.add(c);
                }
            }
        }
    }

    public Carta siguiente(){
        Carta carta = cartas.get(index);
        index=index+1;
        if(index<=48) {
            return carta;
        }else{
            return null;
        }
    }

}
