package hu.lenartl.shogi.game.move;

import hu.lenartl.shogi.game.pieces.Piece;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public record CurrentGameState(Piece[][] board, List<Piece> blackHand, List<Piece> whiteHand) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrentGameState that = (CurrentGameState) o;
        return Objects.deepEquals(board, that.board) && Objects.equals(blackHand, that.blackHand) && Objects.equals(whiteHand, that.whiteHand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.deepHashCode(board), blackHand, whiteHand);
    }

    @Override
    public String toString() {
        return "CurrentGameState{" +
                "board=" + Arrays.toString(board) +
                ", blackHand=" + blackHand +
                ", whiteHand=" + whiteHand +
                '}';
    }
}
