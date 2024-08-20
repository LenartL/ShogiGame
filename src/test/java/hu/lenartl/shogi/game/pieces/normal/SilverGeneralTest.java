package hu.lenartl.shogi.game.pieces.normal;

import hu.lenartl.shogi.game.Position;
import hu.lenartl.shogi.game.pieces.Color;
import hu.lenartl.shogi.game.pieces.Piece;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SilverGeneralTest {
    @ParameterizedTest
    @MethodSource(value = {
            "blackSilverGeneralMoves",
            "whiteSilverGeneralMoves"
            })
    void pt_silverGeneralMoves(Piece piece, int fromRow, int fromColumn, int toRow, int toColumn, boolean expected) {
        Position from = new Position(fromRow, fromColumn);
        Position to = new Position(toRow, toColumn);

        boolean actual = piece.isMoveValid(from, to);
        assertEquals(expected, actual);
    }

    static Stream<Arguments> blackSilverGeneralMoves() {
        Piece piece = new SilverGeneral(Color.BLACK);
        return Stream.of(
                Arguments.of(piece, 2, 2, 1, 1, true),
                Arguments.of(piece, 2, 2, 1, 3, true),
                Arguments.of(piece, 2, 2, 3, 1, true),
                Arguments.of(piece, 2, 2, 3, 2, true),
                Arguments.of(piece, 2, 2, 3, 3, true),
                Arguments.of(piece, 2, 2, 1, 2, false),
                Arguments.of(piece, 2, 2, 2, 1, false),
                Arguments.of(piece, 2, 2, 2, 3, false),
                Arguments.of(piece, 2, 2, 2, 4, false)
        );
    }

    static Stream<Arguments> whiteSilverGeneralMoves() {
        Piece piece = new SilverGeneral(Color.WHITE);
        return Stream.of(
                Arguments.of(piece, 2, 2, 1, 1, true),
                Arguments.of(piece, 2, 2, 1, 2, true),
                Arguments.of(piece, 2, 2, 1, 3, true),
                Arguments.of(piece, 2, 2, 3, 1, true),
                Arguments.of(piece, 2, 2, 3, 3, true),
                Arguments.of(piece, 2, 2, 2, 1, false),
                Arguments.of(piece, 2, 2, 2, 3, false),
                Arguments.of(piece, 2, 2, 3, 2, false),
                Arguments.of(piece, 2, 2, 2, 4, false),
                Arguments.of(piece, 2, 2, 4, 2, false)
        );
    }
}