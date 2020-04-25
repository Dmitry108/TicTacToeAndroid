package ru.bdim.tictactoe.model.module.nuclear;

//Класс и его элементы package-private
abstract class Player {
    private String name;
    private char sing;
    private GameXO game;

    Player(GameXO game, String name, char sing){
        this.name = name;
        this.sing = sing;
        this.game = game;
    }

    String getName() {
        return name;
    }
    Board getBoard(){
        return game.getBoard();
    }
    char getSing() {
        return sing;
    }
    GameXO getGame(){
        return game;
    }
    void inviteToStep(){
        System.out.println(name);
    }
    void step (int x, int y){
        getBoard().doTurn(x, y, sing);
        game.gameCircle(x, y, sing);
    }
}
