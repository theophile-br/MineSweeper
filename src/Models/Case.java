package Models;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Case {
    boolean jaiUneBombe;
    Plateau plateau;
    int x,y;
    boolean open;

    public Case(Plateau plateau, int x, int y) {
        this.plateau = plateau;
        this.x = x;
        this.y = y;
        open = false;
    }

    public void setJaiUneBombe(boolean jaiUneBombe) {
        this.jaiUneBombe = jaiUneBombe;
    }

    public void openIt(){
        if(jaiUneBombe  || open){

        }else{
            open=true;
            Button btn = (Button)getNodeFromGridPane(x,y);
            byte nbBombe = combienDeBombeDansMonVoisinage(x,y);
            btn.setText(String.valueOf(nbBombe));
            if(nbBombe == 0){
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if ((x+i) == -1 || (y+j) == -1 || (x+i) == plateau.getLength() || (y+j) == plateau.getLength()){
                            continue;
                        }
                            plateau.getCase(x+i,y+j).openIt();
                    }
                }
            }
        }
    }

    public byte combienDeBombeDansMonVoisinage(int x, int y) {
        byte nbBombeDansLeVoisinage = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if ((x+i) == -1 || (y+j) == -1 || (x+i) == plateau.getLength() || (y+j) == plateau.getLength()) {
                    continue;
                }
                if (plateau.jaiUneBombe(x+i,y+j)) {
                    nbBombeDansLeVoisinage++;
                }
            }
        }
        return nbBombeDansLeVoisinage;
    }

    private Node getNodeFromGridPane(int col, int row) {
        for (Node node : plateau.getPlateauGUI().getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }
}
