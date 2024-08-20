package hu.lenartl.shogi.game.pieces.normal;

import hu.lenartl.shogi.game.Position;
import hu.lenartl.shogi.game.pieces.Color;
import hu.lenartl.shogi.game.pieces.Piece;

public class Lance extends Piece {
    public Lance(Color color) {
        super(color);
    }

    @Override
    public boolean isMoveValid(Position from, Position to) {
        if (to.column() != from.column()) {
            return false;
        }

        if (this.getColor() == Color.WHITE) {
            return to.row() < from.row();
        } else {
            return to.row() > from.row();
        }
    }
}
