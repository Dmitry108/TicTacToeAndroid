package ru.bdim.tictactoe.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import ru.bdim.tictactoe.R;
import ru.bdim.tictactoe.presenter.CustomBoard;
import ru.bdim.tictactoe.presenter.Panel;

public class XOActivity extends MvpAppCompatActivity implements XOView {

    @InjectPresenter
    Panel presenter;

    @BindView(R.id.btn_new_game)
    Button btnNewGame;
    @BindView(R.id.cmv_board)
    CustomBoard cmvBoard;
    @BindView(R.id.tvw_message)
    TextView tvwMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xo);

        ButterKnife.bind(this);

        btnNewGame.setOnClickListener(v -> {
            presenter.startNewGame();
            cmvBoard.setOnTouchListener((view, event) -> {
                presenter.stepClick(event.getX(), event.getY(), view.getWidth(), view.getHeight());
                return view.performClick();
            });
        });

    }

    @Override
    public void clearBoard() {
        cmvBoard.clear();
    }

    @Override
    public void showMessage(String msg) {
        tvwMessage.setText(msg);
    }

    @Override
    public void printField(char[][] field) {
        cmvBoard.printField(field);
    }

//    @Override
//    public void showStep(int x, int y, int imgId) {
//        cmvBoard.step(x, y, imgId);
//    }

    @Override
    public void stop() {
        cmvBoard.setOnTouchListener(null);
    }
}
