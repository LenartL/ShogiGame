package hu.lenartl.shogi.game.config;

import hu.lenartl.shogi.game.exception.PieceInitializationException;
import hu.lenartl.shogi.game.pieces.Color;
import hu.lenartl.shogi.game.pieces.Piece;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShogiGameConfig {

    @Bean
    public Map<String, Piece> shogiPieces() {

        Map<String, Piece> map = new HashMap<>();

        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AssignableTypeFilter(Piece.class));

        var pieces = scanner.findCandidateComponents("hu.lenartl.shogi.game.pieces.normal");
        var promotedPieces = scanner.findCandidateComponents("hu.lenartl.shogi.game.pieces.promoted");

        pieces.addAll(promotedPieces);

        pieces.forEach(p -> {
            Piece white;
            Piece black;
            try {
                Class<? extends Piece> cls = Class.forName(p.getBeanClassName()).asSubclass(Piece.class);
                white = cls.getDeclaredConstructor(Color.class).newInstance(Color.WHITE);
                black = cls.getDeclaredConstructor(Color.class).newInstance(Color.BLACK);
            } catch (Exception e) {
                throw new PieceInitializationException(e.toString());
            }
            map.put(white.toString(), white);
            map.put(black.toString(), black);
        });

        return map;
    }
}
