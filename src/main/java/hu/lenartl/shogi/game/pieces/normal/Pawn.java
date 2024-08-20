package hu.lenartl.shogi.game.pieces.normal;

import hu.lenartl.shogi.game.Position;
import hu.lenartl.shogi.game.pieces.Color;
import hu.lenartl.shogi.game.pieces.Piece;

public class Pawn extends Piece {
    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean isMoveValid(Position from, Position to) {

        if (this.getColor() == Color.WHITE) {
            return to.column() == from.column()
                    && to.row() == from.row() - 1;
        } else {
            return to.column() == from.column()
                    && to.row() == from.row() + 1;
        }
    }
}
