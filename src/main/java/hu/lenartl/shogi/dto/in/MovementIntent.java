package hu.lenartl.shogi.dto.in;

import hu.lenartl.shogi.game.Position;
import hu.lenartl.shogi.game.pieces.Piece;

public record MovementIntent(Piece piece, Position from, Position to) {
}
