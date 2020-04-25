package ru.bdim.tictactoe.presenter;

//Интерфейс для взаимодействия с UI
//Для реализации модуля игры, нужно его имплементировать.
//Взаимодействие и модулем осуществляется через интерфейс IGameXO,
//для этого нужно получить на него ссылку,
//вызвав метод IGameXO.start(IPanel panel, boolean is1stReal, boolean is2ndReal),
//где panel: данный интерфейс,
//is1stReal, is2ndReal: игрок-человек true, компьютер - false.

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface IPanel {

//    void showStep(int x, int y, char c);
    void clearPanel();
    void showMessage(String msg);
    void stopGame();
    void printField(char[][] field);
}