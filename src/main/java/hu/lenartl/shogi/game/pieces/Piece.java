package hu.lenartl.shogi.game.pieces;

import hu.lenartl.shogi.game.Position;

import java.util.Objects;

public abstract class Piece {
    private final Color color;

    protected Piece(Color color) {
        this.color = color;
    }

    public boolean isMoveValid(Position from, Position to) {
        boolean isValid = isNeighbour(from, to);
        if (this.getColor() == Color.WHITE && isValid) {
            isValid = !(to.row() > from.row() && to.column() != from.column());
        } else if (this.getColor() == Color.BLACK && isValid) {
            isValid = !(to.row() < from.row() && to.column() != from.column());
        }

        return isValid;
    }

    protected boolean isNeighbour(Position from, Position to) {
        int rowDistance = Math.abs(from.row() - to.row());
        int columnDistance = Math.abs(from.column() - to.column());

        return rowDistance < 2 && columnDistance < 2;
    }

    public Color getColor() {
        return color;
    }

    public String getType() {
        return this.getClass().getSimpleName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }

    @Override
    public final String toString() {
        return this.getClass().getSimpleName() + color;
    }
}
