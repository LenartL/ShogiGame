package hu.lenartl.shogi.game.pieces.normal;

import hu.lenartl.shogi.game.Position;
import hu.lenartl.shogi.game.pieces.Color;
import hu.lenartl.shogi.game.pieces.Piece;

public class Knight extends Piece {
    public Knight(Color color) {
        super(color);
    }

    @Override
    public boolean isMoveValid(Position from, Position to) {
        if (this.getColor() == Color.WHITE) {
            return to.row() == from.row() - 2
                    && (to.column() == from.column() - 1 || to.column() == from.column() + 1);
        } else {
            return to.row() == from.row() + 2
                    && (to.column() == from.column() - 1 || to.column() == from.column() + 1);
        }
    }
}
