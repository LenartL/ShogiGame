package hu.lenartl.shogi.game.pieces.normal;

import hu.lenartl.shogi.game.Position;
import hu.lenartl.shogi.game.pieces.Color;
import hu.lenartl.shogi.game.pieces.Piece;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LanceTest {

    @ParameterizedTest
    @MethodSource(value = {
            "blackLanceMoves",
            "whiteLanceMoves"})
    void pt_lanceMoves(Piece piece, int fromRow, int fromColumn, int toRow, int toColumn, boolean expected) {
        Position from = new Position(fromRow, fromColumn);
        Position to = new Position(toRow, toColumn);

        boolean actual = piece.isMoveValid(from, to);
        assertEquals(expected, actual);
    }

    static Stream<Arguments> blackLanceMoves() {
        Piece piece = new Lance(Color.BLACK);
        return Stream.of(
                Arguments.of(piece, 0, 0, 1, 0, true),
                Arguments.of(piece, 0, 0, 8, 0, true),
                Arguments.of(piece, 0, 0, 2, 2, false),
                Arguments.of(piece, 1, 1, 2, 2, false),
                Arguments.of(piece, 1, 1, 0, 2, false),
                Arguments.of(piece, 1, 1, 2, 0, false),
                Arguments.of(piece, 1, 1, 0, 0, false)
        );
    }

    static Stream<Arguments> whiteLanceMoves() {
        Piece piece = new Lance(Color.WHITE);
        return Stream.of(
                Arguments.of(piece, 8, 8, 7, 8, true),
                Arguments.of(piece, 8, 8, 0, 8, true),
                Arguments.of(piece, 8, 8, 7, 7, false),
                Arguments.of(piece, 7, 7, 8, 7, false),
                Arguments.of(piece, 1, 1, 2, 2, false),
                Arguments.of(piece, 1, 1, 0, 2, false),
                Arguments.of(piece, 1, 1, 2, 0, false),
                Arguments.of(piece, 1, 1, 0, 0, false)
        );
    }


}