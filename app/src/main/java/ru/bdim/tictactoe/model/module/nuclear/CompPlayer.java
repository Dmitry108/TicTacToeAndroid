package ru.bdim.tictactoe.model.module.nuclear;

import java.security.SecureRandom;

//Класс и его элементы package-private
class CompPlayer extends Player {

    private int level;
    private SecureRandom rn = new SecureRandom();

    CompPlayer(GameXO game, String name, char c, int level){
        super(game, name, c);
        this.level = level;
    }
    @Override
    void inviteToStep() {
        super.inviteToStep();
        int x = -1;
        int y = -1;
        int size = getBoard().getSize();
        char[][] field = getBoard().getField();
        char c = getSing();
        switch (level) {
            // проверка в методе проходит последовательно от наименее рационального хода к более рациональному, для того, чтобы в конце координаты изменились на самый рациональный ход
            // сначала проверяется алгоритм на основе подсчета очков для пустых клеток,
            // затем проверяется возможность победы человека при следующем ходе,
            // затем проверяется может ли победить компьютер при следующем ходе
            case 2:
                //проверка победит ли противник при следующем ходе, ссли да, тогда нужно помешать
                int[] xy = isSoonWin(getSing()=='X'?'O':'X',field,size);
                if (xy[0] != -1) {
                    x = xy[0];
                    y = xy[1];
                }
                //проверка возможности победить при следующем ходе
                //если - да, сделать ход, иначе - оставить выбор клетки по частоте
                xy = isSoonWin(getSing(),field,size);
                if (xy[0] != -1) {
                    x = xy[0];
                    y = xy[1];
                }
            case 1:
                if (x!=-1) break;
                // алгоритм на основании очков по количеству соседних элементов
                int frequency; //переменная для хранения очков
                int maxFrequency = 0; //максимальное количество очков
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        frequency = 0;
                        if (field[i][j] == '\u0000'){//getBoard().getField()[i][j] == '\u0000') {// проверка соседних клеток по направлениям
                            if (i > 0 && j > 0 && field[i - 1][j - 1] == c) frequency++;                //влево вверх
                            if (i > 0 && field[i - 1][j] == c) frequency++;                             //вверх
                            if (i > 0 && j < size - 1 && field[i - 1][j + 1] == c) frequency++;         //вправо вверх
                            if (j > 0 && field[i][j - 1] == c) frequency++;                             //влево
                            if (j < size - 1 && field[i][j + 1] == c) frequency++;                      //вправо
                            if (i < size - 1 && j > 0 && field[i + 1][j - 1] == c) frequency++;         //влево вниз
                            if (i < size - 1 && field[i + 1][j] == c) frequency++;                      //вниз
                            if (i < size - 1 && j < size - 1 && field[i + 1][j + 1] == c) frequency++;  //вправо вниз
                            if (maxFrequency < frequency) {
                                maxFrequency = frequency;
                                y = i;      //сохранение координат пустой клетки с максимальным количеством очков
                                x = j;
                                //System.out.printf("neib is founded! %d %d частота %d%n", y, x, frequency);
                            }
                        }
                    }
                }
            default:
                if (x!=-1) break;
                do { //если в результате предыдущих действий изменения не произошли, тогда значение х останется -1
                    y = rn.nextInt(size);//getBoard().getSize());
                    x = rn.nextInt(size);//getBoard().getSize());
                }
                while (!getBoard().isValid(x, y));
        }        // если поле закончилось генерировать координаты случайно
        super.step(x, y);
    }
    //оценка возможной выйгрышной ситуации при следующем ходе
    private int[] isSoonWin(char c, char[][] field, int size) {
        //для пустых клеток проверить наличие 2х символов в каждом ряду, столбце и диагоналях
        int [] a={-1,-1};   // массив координат x и y, который вернет метод в результате поиска выйгрышной позиции,
        // если не найдет - вернет начальные значения {-1, -1}
        int fC=0, fE=0;     // переменные для подсчета количество символов в искомой позиции
        int yE=-1, xE=-1;   // переменные для временного хранения координат
        //просмотр каждого ряда на выйгрышную позицию, а она будет выйгрышной, если в ряду есть 2 одинаковых символа и пустая клетка
        for (int i = 0; i < size; i++) {
            fC = fE = 0;
            for (int j = 0; j < size; j++) {
                if (field[i][j] == c) fC++;  //подсчет символов в позиции
                else if (field[i][j] == '\u0000') {
                    fE++;
                    yE = i; //запиминаем координаты пустой клетки на всякий случай
                    xE = j;
                }
                if (fE == 1 && fC == size-1) { //если найдена выйгрышная позиция
                    a[0] = xE;             //пусть a[0] хранит х, a[1] - y
                    a[1] = yE;
                    i = j = size;          //выход из цикла
                }
            }
        }
        //тоже самое по вертикали
        for (int i = 0; i < size; i++) {
            fC = fE = 0;
            for (int j = 0; j < size; j++) {
                if (field[j][i] == c) {
                    fC++;
                }else {
                    if (field[j][i] == '\u0000') {
                        fE++;
                        yE = j;
                        xE = i;
                    }
                }
                if (fE == 1 && fC == size-1) {
                    a[0] = xE;
                    a[1] = yE;
                    i = j = size;
                }
            }
        }
        //по диагонали (для передвижения диагонали использованы линейные функции y=x и y=-x+SIZE
        //диагональ y=x
        fC = fE = 0;
        for (int i = 0; i < size; i++) {
            if (field[i][i] == c) fC++;  //подсчет символов в позиции
            else if (field[i][i] == '\u0000') {
                fE++;
                yE = i;
                xE = i;
            }
        }
        if (fE == 1 && fC == size-1) { //если найдена выйгрышная позиция
            a[0] = xE;
            a[1] = yE;
        }
        //диагональ y=-x+3
        fC = fE = 0;
        for (int i = 0; i < size; i++) {
            if (field[i][-i+size-1] == c) fC++;  //подсчет символов в позиции
            else if (field[i][-i+size-1] == '\u0000') {
                fE++;
                yE = i;
                xE = -i+size-1;
            }
        }
        if (fE == 1 && fC == size-1) { //если найдена выйгрышная позиция
            a[0] = xE;
            a[1] = yE;
        }
        return a;
    }
}