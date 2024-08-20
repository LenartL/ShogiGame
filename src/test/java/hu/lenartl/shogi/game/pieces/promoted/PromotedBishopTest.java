package hu.lenartl.shogi.game.pieces.promoted;

import hu.lenartl.shogi.game.Position;
import hu.lenartl.shogi.game.pieces.Color;
import hu.lenartl.shogi.game.pieces.Piece;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PromotedBishopTest {

    @ParameterizedTest
    @MethodSource(value = {
            "blackPromotedBishopMoves",
            "whitePromotedBishopMoves"})
    void pt_bishopMoves(Piece piece, int fromRow, int fromColumn, int toRow, int toColumn, boolean expected) {

        Position from = new Position(fromRow, fromColumn);
        Position to = new Position(toRow, toColumn);
        boolean actual = piece.isMoveValid(from, to);

        assertEquals(expected, actual);
    }

    static Stream<Arguments> blackPromotedBishopMoves() {
        Piece piece = new PromotedBishop(Color.BLACK);
        return Stream.of(
                Arguments.of(piece, 5, 4, 1, 0, true),
                Arguments.of(piece, 5, 4, 8, 7, true),
                Arguments.of(piece, 5, 4, 7, 2, true),
                Arguments.of(piece, 5, 4, 4, 5, true),
                Arguments.of(piece, 5, 4, 7, 2, true),
                Arguments.of(piece, 5, 4, 5, 3, true),
                Arguments.of(piece, 5, 4, 6, 4, true),
                Arguments.of(piece, 5, 4, 5, 0, false),
                Arguments.of(piece, 5, 4, 0, 4, false)
        );
    }

    static Stream<Arguments> whitePromotedBishopMoves() {
        Piece piece = new PromotedBishop(Color.WHITE);
        return Stream.of(
                Arguments.of(piece, 5, 4, 1, 0, true),
                Arguments.of(piece, 5, 4, 8, 7, true),
                Arguments.of(piece, 5, 4, 7, 2, true),
                Arguments.of(piece, 5, 4, 4, 5, true),
                Arguments.of(piece, 5, 4, 7, 2, true),
                Arguments.of(piece, 5, 4, 5, 3, true),
                Arguments.of(piece, 5, 4, 6, 4, true),
                Arguments.of(piece, 5, 4, 5, 0, false),
                Arguments.of(piece, 5, 4, 0, 4, false)
        );
    }
}