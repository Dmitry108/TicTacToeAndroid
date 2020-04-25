package ru.bdim.tictactoe.model.module.nuclear;

import ru.bdim.tictactoe.presenter.IPanel;

//Класс и его элементы package-private,
//кроме тех, которые предоставляются интерфейсом
class GameXO implements IGameXO {
    private Board board;
    private Player[] player;
    private int current;
    private IPanel panel;

    GameXO(IPanel panel, boolean[] isReal){
        this.panel = panel;
        char[] c = {'X', 'O'};
        player = new Player[2];
        for (int i = 0; i < 2; i++){
            player[i] = (isReal[i]) ?
                    new HumanPlayer(this,"Сю", c[i]) : //, listener) :
                    new CompPlayer(this, "Бри", c[i], 2);
        }
        board = new Board(3);
        current = 0;

        System.out.println("Понеслась...");
        player[current].inviteToStep();
    }

    void gameCircle(int x, int y, char sing){
//        panel.showStep(x, y, sing);//
        panel.printField(board.getField());
        if (board.isWin(player[current])){
            panel.showMessage(player[current].getSing() + " победил!");
            panel.stopGame(); return;}
        if (board.isFull()){
            panel.showMessage("Ничья!");
            panel.stopGame(); return;}
        passTurn();
    }
    private void passTurn() {
        current = current == 0 ? 1 : 0;
        player[current].inviteToStep();
    }

    Player getPlayer() {
        return player[current];
    }
    Board getBoard() {
        return board;
    }
    IPanel getPanel(){
        return panel;
    }

    @Override
    public void humanStep(int x, int y){
        getPlayer().step(x, y);
    }
}