package hu.lenartl.shogi.game.pieces.promoted;

import hu.lenartl.shogi.game.Position;
import hu.lenartl.shogi.game.pieces.Color;
import hu.lenartl.shogi.game.pieces.Promoted;
import hu.lenartl.shogi.game.pieces.normal.Bishop;

public class PromotedBishop extends Bishop implements Promoted {

    public PromotedBishop(Color color) {
        super(color);
    }

    @Override
    public boolean isMoveValid(Position from, Position to) {
        return super.isMoveValid(from, to) || isNeighbour(from, to);
    }

}
