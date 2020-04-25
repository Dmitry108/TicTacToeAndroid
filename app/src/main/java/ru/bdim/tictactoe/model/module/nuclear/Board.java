package ru.bdim.tictactoe.model.module.nuclear;

//Класс и его элементы package-private
class Board {
    private char[][] field;
    private int size;
    private static char E = '\u0000';

    Board(int size){
        this.size=size;
        this.field = new char[size][size];
        for (int i=0; i<size*size; field[i/size][i%size]=E, i++);
    }
    //метод проверки допустимости координат
    boolean isValid(int x, int y){
        return x>=0 && x<size && y>=0 && y<size && field [y][x] == E;
    }
    //метод проверки выйгрыша
    boolean isWin(Player player){
        boolean flag = false;
        boolean d1=true, d2=true, h, v;
        char c = player.getSing();
        for (int i=0; i<size; i++){
            d1 &= (field[i][i]==c);
            d2 &= (field[i][size-i-1]==c);
        }
        if (d1||d2) flag=true;
        if (!flag) {
            for (int i=0; i<size; i++){
                h=v=true;
                for (int j=0; j<size; j++){
                    h &= (field[i][j]==c);
                    v &= (field[j][i]==c);
                }
                if (h||v) {flag=true; i=size;}
            }
        }
        return flag;
    }
    //метод проверки заполненности поля
    boolean isFull(){
        boolean flag = true;
        for (int i=0; i<size; i++)
            for (int j=0; j<size; j++)
                if (field[i][j]==E) {
                    flag=false;
                    i=j=size;}                //выход из циклов
        return flag;
    }
    //метод совершения хода
    void doTurn(int x, int y, char c){
        field[y][x]=c;
    }
    //метод передачи поля в интерфейс
    char[][] getField(){
        return field;
    }
    int getSize() {
        return size;
    }
}
