package hu.lenartl.shogi.repository;

import hu.lenartl.shogi.game.ShogiGameBoard;

public interface GameRepository {
    void newGame(ShogiGameBoard shogiGameBoard);
}
