package pl.norbert;


import java.awt.*;
import java.awt.event.ActionEvent;

public class GraczVsAi extends plansza {

    private static final int INF = 99999;

    GraczVsAi(int size, int win_num) {
        super(size, win_num);
    }

    public void actionPerformed(ActionEvent o) {

                for (int i = 0; i < wez_rozmiar_planszy(); i++) {
                    for (int j = 0; j < wez_rozmiar_planszy(); j++) {
                        if (o.getSource() == wez_przycisk().get(i).get(j)) {
                            if (wez_przycisk().get(i).get(j).getText().equals("")) {
                                    wez_przycisk().get(i).get(j).setFont(new Font("Monospaced", Font.BOLD, 100));
                                    wez_przycisk().get(i).get(j).setText("X");
                                    wez_tekst().setText("Kolej Ai");
                                    if(czy_wygrana()==-10){
                                        wez_tekst().setText("Wygrywasz!");
                                        wylacz();
                                        break;

                                    }
                                    else if(czy_wygrana()==0){
                                        wez_tekst().setText("Remis!");
                                        wylacz();
                                        break;
                                    }
                                    ruch_minmax();
                                    wez_tekst().setText("Twoja kolej");
                                    if(czy_wygrana()==10){
                                        wez_tekst().setText("Ai wygrywa!");
                                        wylacz();
                                        break;
                                    }
                            }
                        }
                    }
                }
    }


    public int minimax(int glebokosc, boolean czy_maksymalizujesz) {

        int wynik = czy_wygrana();
       if (wynik != 100000) {

            if(wynik==10)
                return 10-glebokosc;

            if (wynik==-10)
                return -10+glebokosc;
        }

        if(glebokosc == 0)
            return 0;


        int najlepszy_wynik;

        if (czy_maksymalizujesz) {
            najlepszy_wynik = -INF;
            for (int i = 0; i < wez_rozmiar_planszy(); i++) {
                for (int j = 0; j < wez_rozmiar_planszy(); j++) {
                    if (wez_przycisk().get(i).get(j).getText().equals("")) {
                        wez_przycisk().get(i).get(j).setText("O");
                        int wynikMinmax = minimax(glebokosc - 1, false);
                        wez_przycisk().get(i).get(j).setText("");
                        najlepszy_wynik = Math.max(wynikMinmax, najlepszy_wynik);
                    }
                }
            }
        } else {
            najlepszy_wynik = INF;
            for (int i = 0; i < wez_rozmiar_planszy(); i++) {
                for (int j = 0; j < wez_rozmiar_planszy(); j++) {
                    if (wez_przycisk().get(i).get(j).getText().equals("")) {
                        wez_przycisk().get(i).get(j).setText("X");
                        int wynikMinmax = minimax(glebokosc -1, true);
                        wez_przycisk().get(i).get(j).setText("");
                        najlepszy_wynik = Math.min(wynikMinmax, najlepszy_wynik);

                    }
                }
            }
        }
        return najlepszy_wynik;
    }


    public void ruch_minmax() {
        int nalepszy_wynik = -INF;
        int jeden=0;
        int dwa=0;

        for (int i = 0; i < wez_rozmiar_planszy(); i++) {
            for (int j = 0; j < wez_rozmiar_planszy(); j++) {
                if (wez_przycisk().get(i).get(j).getText().equals("")) {
                    wez_przycisk().get(i).get(j).setText("O");
                    int wynik = minimax(1, false);
                    wez_przycisk().get(i).get(j).setText("");
                    if ( wynik >nalepszy_wynik) {
                        nalepszy_wynik = wynik;
                        jeden = i;
                        dwa = j;

                    }
                }
            }
        }
        wez_przycisk().get(jeden).get(dwa).setText("O");
        wez_przycisk().get(jeden).get(dwa).setFont(new Font("Monospaced", Font.BOLD, 100));
    }
}




































