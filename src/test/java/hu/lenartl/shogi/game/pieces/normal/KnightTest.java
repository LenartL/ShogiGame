package hu.lenartl.shogi.game.pieces.normal;

import hu.lenartl.shogi.game.Position;
import hu.lenartl.shogi.game.pieces.Color;
import hu.lenartl.shogi.game.pieces.Piece;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KnightTest {

    @ParameterizedTest
    @MethodSource(value = {
            "blackKnightMoves",
            "whiteKnightMoves"})
    void pt_knightMoves(Piece piece, int fromRow, int fromColumn, int toRow, int toColumn, boolean expected) {
        Position from = new Position(fromRow, fromColumn);
        Position to = new Position(toRow, toColumn);

        boolean actual = piece.isMoveValid(from, to);
        assertEquals(expected, actual);
    }

    static Stream<Arguments> blackKnightMoves() {
        Piece piece = new Knight(Color.BLACK);
        return Stream.of(
                Arguments.of(piece, 2, 2, 4, 1, true),
                Arguments.of(piece, 2, 2, 4, 2, false),
                Arguments.of(piece, 2, 2, 4, 3, true),
                Arguments.of(piece, 2, 2, 0, 1, false),
                Arguments.of(piece, 2, 2, 0, 3, false)
        );
    }

    static Stream<Arguments> whiteKnightMoves() {
        Piece piece = new Knight(Color.WHITE);
        return Stream.of(
                Arguments.of(piece, 8, 8, 6, 7, true),
                Arguments.of(piece, 8, 8, 6, 8, false),
                Arguments.of(piece, 7, 7, 5, 8, true),
                Arguments.of(piece, 6, 6, 8, 5, false),
                Arguments.of(piece, 6, 6, 8, 7, false),
                Arguments.of(piece, 8, 8, 8, 7, false)
        );
    }
}