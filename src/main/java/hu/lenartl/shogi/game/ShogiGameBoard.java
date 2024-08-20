package hu.lenartl.shogi.game;

import hu.lenartl.shogi.game.exception.InvalidMoveException;
import hu.lenartl.shogi.game.move.CurrentGameState;
import hu.lenartl.shogi.game.pieces.Color;
import hu.lenartl.shogi.game.pieces.Piece;
import hu.lenartl.shogi.game.pieces.Promoted;
import hu.lenartl.shogi.game.pieces.normal.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ShogiGameBoard implements GameBoard {
    public static final String PROMOTED_PREFIX = "Promoted";

    private final Piece[][] pieces;
    private final List<Piece> blackHand;
    private final List<Piece> whiteHand;
    private final Map<String, Piece> piecePool;

    public ShogiGameBoard(Map<String, Piece> piecePool) {
        this.piecePool = piecePool;
        this.pieces = new Piece[9][9];
        this.blackHand = new LinkedList<>();
        this.whiteHand = new LinkedList<>();
        this.initialize();
    }

    private void initialize() {
        for (byte i = 0; i < 9; i++) {
            for (byte j = 0; j < 9; j++) {
                Piece piece = generatePiece(i, j);
                pieces[i][j] = piece;
            }
        }
    }

    private Piece generatePiece(int row, int col) {
        String strColor;

        if (row < 3) {
            strColor = Color.BLACK.toString();
        } else if (row > 5) {
            strColor = Color.WHITE.toString();
        } else {
            return null;
        }

        if (row == 2 || row == 6) {
            return piecePool.get(Pawn.class.getSimpleName() + strColor);
        }

        if (row == 0 || row == 8) {
            return switch (col) {
                case 0, 8 -> piecePool.get(Lance.class.getSimpleName() + strColor);
                case 1, 7 -> piecePool.get(Knight.class.getSimpleName() + strColor);
                case 2, 6 -> piecePool.get(SilverGeneral.class.getSimpleName() + strColor);
                case 3, 5 -> piecePool.get(GoldGeneral.class.getSimpleName() + strColor);
                default -> piecePool.get(King.class.getSimpleName() + strColor);
            };
        } else if (row == 1 || row == 7) {
            if (col + row == 8) {
                return piecePool.get(Bishop.class.getSimpleName() + strColor);
            } else if (col == row) {
                return piecePool.get(Rook.class.getSimpleName() + strColor);
            }
        }

        return null;
    }

    public CurrentGameState move(Position from, Position to) throws InvalidMoveException {
        Piece piece = getPiece(from)
                .orElseThrow(InvalidMoveException::new);
        if (piece.isMoveValid(from, to)) {
            var optionalTarget = removePiece(to);
            optionalTarget.ifPresent(this::moveToHand);
            setPiece(to, piece);
            return new CurrentGameState(pieces, blackHand, whiteHand);
        } else {
            throw new InvalidMoveException();
        }
    }

    public CurrentGameState moveAndPromote(Position from, Position to) throws InvalidMoveException {
        move(from, to);
        var piece = getPiece(to)
                .orElseThrow(InvalidMoveException::new);
        if (isPromotable(piece, to)) {
            piece = promote(piece);
            setPiece(to, piece);
        }

        return new CurrentGameState(pieces, blackHand, whiteHand);
    }

    public CurrentGameState moveAndDemote(Position from, Position to) throws InvalidMoveException {
        move(from, to);
        var piece = getPiece(to)
                .orElseThrow(InvalidMoveException::new);
        piece = demote(piece);
        setPiece(to, piece);
        return new CurrentGameState(pieces, blackHand, whiteHand);
    }

    private boolean isPromotable(Piece piece, Position targetPosition) {
        Color color;
        int row = targetPosition.row();
        try {
            color = piece.getColor();
        } catch (NullPointerException e) {
            throw new InvalidMoveException();
        }

        return (color == Color.BLACK && row >= 6) || (color == Color.WHITE && row <= 2);
    }

    private Piece promote(Piece piece) {
        if (!(piece instanceof Promoted)) {
            return piecePool.get(PROMOTED_PREFIX + piece.toString());
        }
        return piece;
    }

    private Piece demote(Piece piece) {
        if (piece instanceof Promoted) {
            return piecePool.get(piece.toString().substring(PROMOTED_PREFIX.length()));
        }
        return piece;
    }

    public void moveToHand(Piece piece) {
        piece = demote(piece);
        if (piece.getColor() == Color.WHITE) {
            blackHand.add(piece);
        } else {
            whiteHand.add(piece);
        }
    }

    public Optional<Piece> getPiece(Position position) {
        return Optional.ofNullable(pieces[position.row()][position.column()]);
    }

    public void setPiece(Position position, Piece piece) {
        pieces[position.row()][position.column()] = piecePool.get(piece.toString());
    }

    public Optional<Piece> removePiece(Position position) {
        var piece = pieces[position.row()][position.column()];
        pieces[position.row()][position.column()] = null;
        return Optional.ofNullable(piece);
    }

    public List<Piece> getBlackHand() {
        return List.copyOf(blackHand);
    }

    public List<Piece> getWhiteHand() {
        return List.copyOf(whiteHand);
    }
}
