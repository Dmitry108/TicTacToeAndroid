package ru.bdim.tictactoe.model.module.nuclear;

import ru.bdim.tictactoe.presenter.IPanel;

public interface IGameXO {

    static IGameXO start(IPanel panel, boolean is1stReal, boolean is2ndReal){
        panel.clearPanel();
        return new GameXO(panel, new boolean[]{is1stReal, is2ndReal});
    }
    // Метод для передачи координат клика по игровому полю
    void humanStep(int x, int y);
}
