package pl.norbert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class plansza implements ActionListener
{

    private final int rozmiar_planszy;
    private final int ile_wygra;


    private final JFrame ramka = new JFrame();
    private final JLabel tekst = new JLabel();
    private ArrayList<ArrayList<JButton>> przyciski = new ArrayList<>();

    plansza(int rozmiar, int ile_wygrywa) {
        rozmiar_planszy = rozmiar;
        ile_wygra = ile_wygrywa;

        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramka.setSize(600,600);
        ramka.setLayout(new BorderLayout());
        ramka.setVisible(true);
        ramka.setLocationRelativeTo(null);

        tekst.setForeground(new Color(208, 151, 151));
        tekst.setBackground(new Color(114, 48, 48));
        tekst.setFont(new Font("Monospaced",Font.BOLD,90));
        tekst.setHorizontalAlignment(JLabel.CENTER);
        tekst.setText("Kolko i krzyzyk");
        tekst.setOpaque(true);

        JPanel panel_tekstowy = new JPanel();
        panel_tekstowy.setLayout(new BorderLayout());
        JPanel panel_przycisku = new JPanel();
        panel_przycisku.setLayout(new GridLayout(rozmiar_planszy,rozmiar_planszy));

        for(int i=0;i < rozmiar_planszy;i++)
            przyciski.add(new ArrayList<>());

        for(int i=0; i< rozmiar_planszy; i++) {
            for(int j=0;j<rozmiar_planszy;j++) {
                przyciski.get(i).add(new JButton());
                panel_przycisku.add(przyciski.get(i).get(j));
                przyciski.get(i).get(j).setFont(new Font("Dialog", Font.BOLD, 10));
                przyciski.get(i).get(j).setBackground(new Color(182, 241, 148));
                przyciski.get(i).get(j).setFocusable(true);
                przyciski.get(i).get(j).addActionListener(this);

            }
        }

        panel_tekstowy.add(tekst);
        ramka.add(panel_tekstowy,BorderLayout.SOUTH);
        ramka.add(panel_przycisku);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            this.ramka.dispose();
            new GraczVsAi(rozmiar_planszy,ile_wygra);
        }



    public int czy_wygrana() {

        int licznik_x = 0;
        int licznik_o = 0;

        for (int i = 0; i < rozmiar_planszy; i++) {


            for (int j = 0; j < rozmiar_planszy; j++) {
                if (przyciski.get(i).get(j).getText().equals("X")) {
                    licznik_x++;
                    if (licznik_x == ile_wygra) return -10;
                }
                else licznik_x=0;


                if (przyciski.get(i).get(j).getText().equals("O")) {
                    licznik_o++;
                    if (licznik_o == ile_wygra) return 10;
                }
                else licznik_o=0;
            }

            licznik_x = 0;
            licznik_o = 0;


            for (int j = 0; j < rozmiar_planszy; j++) {
                if (przyciski.get(j).get(i).getText().equals("X")) {
                    licznik_x++;
                    if (licznik_x == ile_wygra) return -10;
                }
                else licznik_x=0;

                if (przyciski.get(j).get(i).getText().equals("O")) {
                    licznik_o++;
                    if (licznik_o == ile_wygra) return 10;
                }
                else licznik_o=0;
            }

            licznik_x = 0;
            licznik_o = 0;


            for (int j = 0; j < rozmiar_planszy; j++) {
                if (przyciski.get(j).get(j).getText().equals("X")) {
                    licznik_x++;
                    if (licznik_x == ile_wygra) return -10;
                }
                else licznik_x=0;

                if (przyciski.get(j).get(j).getText().equals("O")) {
                    licznik_o++;
                    if (licznik_o == ile_wygra) return 10;
                }
                else licznik_o=0;
            }

            licznik_x = 0;
            licznik_o = 0;


            int c = 0;
            int d = rozmiar_planszy - 1;
            while (c < rozmiar_planszy) {
                while (d >= 0) {
                    if (przyciski.get(c).get(d).getText().equals("X")) {
                        licznik_x++;
                        if (licznik_x == ile_wygra)
                            return -10;
                    }
                    else licznik_x = 0;

                    if (przyciski.get(c).get(d).getText().equals("O")) {
                        licznik_o++;
                        if (licznik_o == ile_wygra)
                            return 10;
                    }

                    else licznik_o = 0;

                    c++;
                    d--;
                }
            }
            licznik_x = 0;
            licznik_o = 0;
        }

        int licznik_remis = 0;



        for (int i = 0; i < rozmiar_planszy; i++) {
            for (int j = 0; j < rozmiar_planszy; j++) {
                if (przyciski.get(i).get(j).getText().equals("X") || przyciski.get(i).get(j).getText().equals("O"))
                    licznik_remis ++;
            }
        }
        if (licznik_remis  == rozmiar_planszy * rozmiar_planszy) {
            tekst.setText("Remis!");
            return 0;
        }

        return 100000;

    }



    public void wylacz() {

        for (int b = 0; b < rozmiar_planszy; b++) {
            for (int j = 0; j < rozmiar_planszy; j++) {
                przyciski.get(b).get(j).setEnabled(false);
            }
        }
    }

    public int wez_rozmiar_planszy() {
        return rozmiar_planszy;
    }


    public JLabel wez_tekst() {
        return tekst;
    }

    public ArrayList<ArrayList<JButton>> wez_przycisk() {
        return przyciski;
    }

}
