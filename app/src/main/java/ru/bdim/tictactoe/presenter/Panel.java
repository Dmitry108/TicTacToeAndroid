package ru.bdim.tictactoe.presenter;

import android.util.Log;

import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.bdim.tictactoe.R;
import ru.bdim.tictactoe.model.module.nuclear.IGameXO;
import ru.bdim.tictactoe.view.XOView;

@InjectViewState
public class Panel extends MvpPresenter<XOView> implements IPanel{

    private IGameXO game;
    private static final int SIZE = 3;
    private static final String TAG = "...";

    public void startNewGame() {
        Log.d(TAG, "настааарт, внимааание...");
        game = IGameXO.start(this, true, false);
    }
    public void stepClick(float x, float y, float width, float height) {
        float w = width/SIZE;
        float h = height/SIZE;
        int xx = (int)(x/w);
        int yy = (int)(y/h);
        game.humanStep(xx, yy);
    }

//    @Override
//    public void showStep(int x, int y, char c) {
//        int imgId = c == 'X' ? R.drawable.x : R.drawable.o;
//        getViewState().showStep(x, y, imgId);
//    }
    @Override
    public void printField(char[][] field) {
        getViewState().printField(field);
    }

    @Override
    public void clearPanel() {
        getViewState().clearBoard();
    }

    @Override
    public void showMessage(String msg) {
        getViewState().showMessage(msg);

    }

    @Override
    public void stopGame() {
        getViewState().stop();
    }

//
}