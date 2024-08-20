package hu.lenartl.shogi.game;

import hu.lenartl.shogi.game.config.ShogiGameConfig;
import hu.lenartl.shogi.game.pieces.Color;
import hu.lenartl.shogi.game.pieces.Piece;
import hu.lenartl.shogi.game.pieces.normal.*;
import hu.lenartl.shogi.game.pieces.promoted.PromotedPawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShogiGameBoardTest {

    Map<String, Piece> piecePool = new ShogiGameConfig().shogiPieces();
    ShogiGameBoard board = new ShogiGameBoard(piecePool);

    @BeforeEach
    void setUp() {
        board.initialize();
    }

    @ParameterizedTest
    @MethodSource(value = {
            "blackPieces_defaultInit",
            "whitePieces_defaultInit",
            "noPieces_defaultInit"})
    void pt_defaultPieceInitialization(int row, int column, Piece expected) {
        Piece actual = board.getPiece(new Position(row, column)).orElse(null);
        assertEquals(expected, actual);
    }

    @Test
    void test_movePawn_toNullField() {
        Position from = new Position(2, 0);
        Position to = new Position(3, 0);

        board.move(from, to);
        Piece expected = new Pawn(Color.BLACK);
        Piece actual = board.getPiece(to).orElse(null);

        assertEquals(expected, actual);
    }

    @Test
    void test_moveAndPromote() {
        Piece pieceToPromote = new Pawn(Color.WHITE);
        Position from = new Position(3, 0);
        Position to = new Position(2, 0);
        board.setPiece(from, pieceToPromote);
        board.moveAndPromote(from, to);

        Piece expected = new PromotedPawn(Color.WHITE);
        Piece actual = board.getPiece(to).orElse(null);

        assertEquals(expected, actual);
    }

    @Test
    void moveAndDemote() {
        Piece pieceToDemote = new PromotedPawn(Color.WHITE);
        Position from = new Position(3, 0);
        Position to = new Position(2, 0);
        board.setPiece(from, pieceToDemote);
        board.moveAndDemote(from, to);

        Piece expected = new Pawn(Color.WHITE);
        Piece actual = board.getPiece(to).orElse(null);
        assertEquals(expected, actual);
    }

    static Stream<Arguments> blackPieces_defaultInit() {
        return Stream.of(
                Arguments.of(0, 0, new Lance(Color.BLACK)),
                Arguments.of(0, 1, new Knight(Color.BLACK)),
                Arguments.of(0, 2, new SilverGeneral(Color.BLACK)),
                Arguments.of(0, 3, new GoldGeneral(Color.BLACK)),
                Arguments.of(0, 4, new King(Color.BLACK)),
                Arguments.of(0, 5, new GoldGeneral(Color.BLACK)),
                Arguments.of(0, 6, new SilverGeneral(Color.BLACK)),
                Arguments.of(0, 7, new Knight(Color.BLACK)),
                Arguments.of(0, 8, new Lance(Color.BLACK)),

                Arguments.of(1, 1, new Rook(Color.BLACK)),
                Arguments.of(1, 7, new Bishop(Color.BLACK)),

                Arguments.of(2, 0, new Pawn(Color.BLACK)),
                Arguments.of(2, 1, new Pawn(Color.BLACK)),
                Arguments.of(2, 2, new Pawn(Color.BLACK)),
                Arguments.of(2, 3, new Pawn(Color.BLACK)),
                Arguments.of(2, 4, new Pawn(Color.BLACK)),
                Arguments.of(2, 5, new Pawn(Color.BLACK)),
                Arguments.of(2, 6, new Pawn(Color.BLACK)),
                Arguments.of(2, 7, new Pawn(Color.BLACK)),
                Arguments.of(2, 8, new Pawn(Color.BLACK))
        );
    }

    static Stream<Arguments> whitePieces_defaultInit() {
        return Stream.of(
                Arguments.of(8, 0, new Lance(Color.WHITE)),
                Arguments.of(8, 1, new Knight(Color.WHITE)),
                Arguments.of(8, 2, new SilverGeneral(Color.WHITE)),
                Arguments.of(8, 3, new GoldGeneral(Color.WHITE)),
                Arguments.of(8, 4, new King(Color.WHITE)),
                Arguments.of(8, 5, new GoldGeneral(Color.WHITE)),
                Arguments.of(8, 6, new SilverGeneral(Color.WHITE)),
                Arguments.of(8, 7, new Knight(Color.WHITE)),
                Arguments.of(8, 8, new Lance(Color.WHITE)),

                Arguments.of(7, 1, new Bishop(Color.WHITE)),
                Arguments.of(7, 7, new Rook(Color.WHITE)),

                Arguments.of(6, 0, new Pawn(Color.WHITE)),
                Arguments.of(6, 1, new Pawn(Color.WHITE)),
                Arguments.of(6, 2, new Pawn(Color.WHITE)),
                Arguments.of(6, 3, new Pawn(Color.WHITE)),
                Arguments.of(6, 4, new Pawn(Color.WHITE)),
                Arguments.of(6, 5, new Pawn(Color.WHITE)),
                Arguments.of(6, 6, new Pawn(Color.WHITE)),
                Arguments.of(6, 7, new Pawn(Color.WHITE)),
                Arguments.of(6, 8, new Pawn(Color.WHITE))
        );
    }

    static Stream<Arguments> noPieces_defaultInit() {
        return Stream.of(
                Arguments.of(7, 0, null),
                Arguments.of(7, 2, null),
                Arguments.of(7, 3, null),
                Arguments.of(7, 4, null),
                Arguments.of(7, 5, null),
                Arguments.of(7, 6, null),
                Arguments.of(7, 8, null),
                Arguments.of(1, 0, null),
                Arguments.of(1, 2, null),
                Arguments.of(1, 3, null),
                Arguments.of(1, 4, null),
                Arguments.of(1, 5, null),
                Arguments.of(1, 6, null),
                Arguments.of(1, 8, null),
                Arguments.of(3, 3, null),
                Arguments.of(4, 4, null),
                Arguments.of(5, 5, null)

        );
    }

}