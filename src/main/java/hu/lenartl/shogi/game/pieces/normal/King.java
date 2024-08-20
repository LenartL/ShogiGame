package hu.lenartl.shogi.game.pieces.normal;

import hu.lenartl.shogi.game.Position;
import hu.lenartl.shogi.game.pieces.Color;
import hu.lenartl.shogi.game.pieces.Piece;

public class King extends Piece {
    public King(Color color) {
        super(color);
    }

    @Override
    public boolean isMoveValid(Position from, Position to) {
        return isNeighbour(to, from);
    }
}
