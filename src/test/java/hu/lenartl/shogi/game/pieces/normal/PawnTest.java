package hu.lenartl.shogi.game.pieces.normal;

import hu.lenartl.shogi.game.Position;
import hu.lenartl.shogi.game.pieces.Color;
import hu.lenartl.shogi.game.pieces.Piece;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {

    @ParameterizedTest
    @MethodSource(value = {
            "blackPawnMoves",
            "whitePawnMoves"
            })
    void pt_pawnMoves(Piece piece, int fromRow, int fromColumn, int toRow, int toColumn, boolean expected) {
        Position from = new Position(fromRow, fromColumn);
        Position to = new Position(toRow, toColumn);

        boolean actual = piece.isMoveValid(from, to);
        assertEquals(expected, actual);
    }

    static Stream<Arguments> blackPawnMoves() {
        Piece piece = new Pawn(Color.BLACK);
        return Stream.of(
                Arguments.of(piece, 0, 0, 1, 0, true),
                Arguments.of(piece, 0, 0, 0, 1, false),
                Arguments.of(piece, 1, 1, 0, 1, false),
                Arguments.of(piece, 1, 1, 0, 0, false),
                Arguments.of(piece, 1, 1, 0, 2, false),
                Arguments.of(piece, 1, 1, 2, 0, false),
                Arguments.of(piece, 1, 1, 2, 2, false)
        );
    }

    static Stream<Arguments> whitePawnMoves() {
        Piece piece = new Pawn(Color.WHITE);
        return Stream.of(
                Arguments.of(piece, 7, 7, 6, 7, true),
                Arguments.of(piece, 7, 7, 8, 7, false),
                Arguments.of(piece, 7, 7, 7, 8, false),
                Arguments.of(piece, 7, 7, 8, 8, false),
                Arguments.of(piece, 7, 7, 6, 8, false),
                Arguments.of(piece, 7, 7, 8, 6, false),
                Arguments.of(piece, 7, 7, 8, 7, false)
        );
    }
}