package hu.lenartl.shogi.game.pieces.normal;

import hu.lenartl.shogi.game.Position;
import hu.lenartl.shogi.game.pieces.Color;
import hu.lenartl.shogi.game.pieces.Piece;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public boolean isMoveValid(Position from, Position to) {
        return from.row() - from.column() == to.row() - to.column()
                || from.row() + from.column() == to.row() + to.column();
    }

}
