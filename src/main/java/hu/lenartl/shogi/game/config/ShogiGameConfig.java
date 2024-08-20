package hu.lenartl.shogi.game.config;

import hu.lenartl.shogi.game.pieces.Color;
import hu.lenartl.shogi.game.pieces.Piece;
import hu.lenartl.shogi.game.pieces.normal.*;
import hu.lenartl.shogi.game.pieces.promoted.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ShogiGameConfig {

    @Bean
    public Map<String, Piece> shogiPieces() {

        List<Piece> pieces = Arrays.asList(
                new Bishop(Color.BLACK),
                new Bishop(Color.WHITE),

                new PromotedBishop(Color.BLACK),
                new PromotedBishop(Color.WHITE),

                new GoldGeneral(Color.BLACK),
                new GoldGeneral(Color.WHITE),

                new King(Color.BLACK),
                new King(Color.WHITE),

                new Knight(Color.BLACK),
                new Knight(Color.WHITE),
                new PromotedKnight(Color.BLACK),
                new PromotedKnight(Color.WHITE),

                new Lance(Color.BLACK),
                new Lance(Color.WHITE),
                new PromotedLance(Color.BLACK),
                new PromotedLance(Color.WHITE),

                new Pawn(Color.BLACK),
                new Pawn(Color.WHITE),
                new PromotedPawn(Color.BLACK),
                new PromotedPawn(Color.WHITE),

                new Rook(Color.BLACK),
                new Rook(Color.WHITE),
                new PromotedRook(Color.BLACK),
                new PromotedRook(Color.WHITE),

                new SilverGeneral(Color.BLACK),
                new SilverGeneral(Color.WHITE),
                new PromotedSilverGeneral(Color.BLACK),
                new PromotedSilverGeneral(Color.WHITE)
                );

        Map<String, Piece> map = new HashMap<>();

        pieces.forEach(piece -> map.put(piece.toString(), piece));
        return map;
    }
}
