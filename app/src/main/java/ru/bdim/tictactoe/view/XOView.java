package ru.bdim.tictactoe.view;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;
import ru.bdim.tictactoe.presenter.CustomBoard;

public interface XOView extends MvpView {
    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void clearBoard();

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void showMessage(String msg);

//    @StateStrategyType(value = AddToEndSingleStrategy.class)
//    void showStep(int x, int y, int imgId);

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void stop();

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void printField(char[][] field);
}
