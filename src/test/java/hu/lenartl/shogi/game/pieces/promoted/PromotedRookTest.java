package hu.lenartl.shogi.game.pieces.promoted;

import hu.lenartl.shogi.game.Position;
import hu.lenartl.shogi.game.pieces.Color;
import hu.lenartl.shogi.game.pieces.Piece;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PromotedRookTest {
    @ParameterizedTest
    @MethodSource(value = {
            "blackPromotedMoves",
            "whitePromotedMoves"})
    void pt_rookMoves(Piece piece, int fromRow, int fromColumn, int toRow, int toColumn, boolean expected) {
        Position from = new Position(fromRow, fromColumn);
        Position to = new Position(toRow, toColumn);

        boolean actual = piece.isMoveValid(from, to);
        assertEquals(expected, actual);
    }

    static Stream<Arguments> blackPromotedMoves() {
        Piece piece = new PromotedRook(Color.BLACK);
        return Stream.of(
                Arguments.of(piece, 2, 2, 2, 6, true),
                Arguments.of(piece, 2, 2, 6, 2, true),
                Arguments.of(piece, 2, 2, 0, 2, true),
                Arguments.of(piece, 2, 2, 2, 0, true),
                Arguments.of(piece, 2, 2, 1, 1, true),
                Arguments.of(piece, 2, 2, 1, 3, true),
                Arguments.of(piece, 2, 2, 3, 1, true),
                Arguments.of(piece, 2, 2, 3, 3, true),
                Arguments.of(piece, 2, 2, 5, 3, false),
                Arguments.of(piece, 2, 2, 3, 4, false)
        );
    }

    static Stream<Arguments> whitePromotedMoves() {
        Piece piece = new PromotedRook(Color.WHITE);
        return Stream.of(
                Arguments.of(piece, 2, 2, 2, 6, true),
                Arguments.of(piece, 2, 2, 6, 2, true),
                Arguments.of(piece, 2, 2, 0, 2, true),
                Arguments.of(piece, 2, 2, 2, 0, true),
                Arguments.of(piece, 2, 2, 1, 1, true),
                Arguments.of(piece, 2, 2, 1, 3, true),
                Arguments.of(piece, 2, 2, 3, 1, true),
                Arguments.of(piece, 2, 2, 3, 3, true),
                Arguments.of(piece, 2, 2, 5, 3, false),
                Arguments.of(piece, 2, 2, 3, 4, false)
        );
    }
}