package hu.lenartl.shogi.game.pieces.normal;

import hu.lenartl.shogi.game.Position;
import hu.lenartl.shogi.game.pieces.Color;
import hu.lenartl.shogi.game.pieces.Piece;

public class SilverGeneral extends Piece {
    public SilverGeneral(Color color) {
        super(color);
    }

    @Override
    public boolean isMoveValid(Position from, Position to) {
        boolean isValid = isNeighbour(from, to);

        if (this.getColor() == Color.WHITE && isValid) {
            isValid = !((to.column() != from.column() && to.row() == from.row())
                    || (to.column() == from.column() && to.row() > from.row()));
        } else if (this.getColor() == Color.BLACK && isValid) {
            isValid = !((to.column() != from.column() && to.row() == from.row())
                    || (to.column() == from.column() && to.row() < from.row()));
        }
        return isValid;
    }
}
