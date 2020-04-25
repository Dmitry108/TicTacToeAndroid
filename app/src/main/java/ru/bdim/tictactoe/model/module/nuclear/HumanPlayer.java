package ru.bdim.tictactoe.model.module.nuclear;

//Класс и его элементы package-private
class HumanPlayer extends Player {

    HumanPlayer(GameXO game, String name, char sing){
        super(game, name, sing);
    }
    @Override
    void step (int x, int y){
        if (!(getBoard().isValid(x,y))){
            getGame().getPanel().showMessage("Некорректный ход!");
            inviteToStep();
        } else {
            super.step(x, y);
        }
    }
}
