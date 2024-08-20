package hu.lenartl.shogi.game.pieces.promoted;

import hu.lenartl.shogi.game.pieces.Color;
import hu.lenartl.shogi.game.pieces.Piece;
import hu.lenartl.shogi.game.pieces.Promoted;

public class PromotedPawn extends Piece implements Promoted {
    public PromotedPawn(Color color) {
        super(color);
    }
}
