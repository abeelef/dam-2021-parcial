package cat.udl.tidic.amd.a7mig.models;


import androidx.annotation.NonNull;

import cat.udl.tidic.amd.a7mig.R;

public class Carta {

    private final int numero;
    private final Palos palo;

    public Carta(int numero, Palos palo) {
        this.numero = numero;
        this.palo = palo;
    }

    @Override
    public boolean equals(Object o) {
        Carta c = (Carta) o;
        return this.numero == c.getNumero() && this.palo == c.getPalo();
    }

    public int getNumero() {
        return numero;
    }

    public Palos getPalo() {
        return palo;
    }

    @NonNull
    @Override
    public String toString() {
        return "numero: "+this.numero + " - " + "palo: "+this.palo.name();
    }

    public double getValue(){
        if (this.numero>7){
            return  0.5;
        } else{
            return this.numero;
        }
    }

    public int getResource() {
        switch (this.palo) {
            case oros:
                switch (this.numero) {
                    case 1:
                        return R.drawable.oros1;
                    case 2:
                        return R.drawable.oros2;
                    case 3:
                        return R.drawable.oros3;
                    case 4:
                        return R.drawable.oros4;
                    case 5:
                        return R.drawable.oros5;
                    case 6:
                        return R.drawable.oros6;
                    case 7:
                        return R.drawable.oros7;
                    case 10:
                        return R.drawable.oros10;
                    case 11:
                        return R.drawable.oros11;
                    case 12:
                        return R.drawable.oros12;
                }
            case copas:
                switch (this.numero) {
                    case 1:
                        return R.drawable.copas1;
                    case 2:
                        return R.drawable.copas2;
                    case 3:
                        return R.drawable.copas3;
                    case 4:
                        return R.drawable.copas4;
                    case 5:
                        return R.drawable.copas5;
                    case 6:
                        return R.drawable.copas6;
                    case 7:
                        return R.drawable.copas7;
                    case 10:
                        return R.drawable.copas10;
                    case 11:
                        return R.drawable.copas11;
                    case 12:
                        return R.drawable.copas12;
                }
            case espadas:
                switch (this.numero) {
                    case 1:
                        return R.drawable.espadas1;
                    case 2:
                        return R.drawable.espadas2;
                    case 3:
                        return R.drawable.espadas3;
                    case 4:
                        return R.drawable.espadas4;
                    case 5:
                        return R.drawable.espadas5;
                    case 6:
                        return R.drawable.espadas6;
                    case 7:
                        return R.drawable.espadas7;
                    case 10:
                        return R.drawable.espadas10;
                    case 11:
                        return R.drawable.espadas11;
                    case 12:
                        return R.drawable.espadas12;
                }
            case bastos:
                switch (this.numero) {
                    case 1:
                        return R.drawable.bastos1;
                    case 2:
                        return R.drawable.bastos2;
                    case 3:
                        return R.drawable.bastos3;
                    case 4:
                        return R.drawable.bastos4;
                    case 5:
                        return R.drawable.bastos5;
                    case 6:
                        return R.drawable.bastos6;
                    case 7:
                        return R.drawable.bastos7;
                    case 10:
                        return R.drawable.bastos10;
                    case 11:
                        return R.drawable.bastos11;
                    case 12:
                        return R.drawable.bastos12;
                }
            default:
                return -1;
        }
    }




}


