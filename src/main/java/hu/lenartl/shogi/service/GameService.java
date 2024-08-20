package hu.lenartl.shogi.service;

import hu.lenartl.shogi.dto.out.GameWithUUID;
import hu.lenartl.shogi.game.ShogiGameBoard;
import hu.lenartl.shogi.game.pieces.Piece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class GameService {

    private final ConcurrentMap<UUID, ShogiGameBoard> boards = new ConcurrentHashMap<>();
    private final Map<String, Piece> shogiPieces;

    @Autowired
    public GameService(Map<String, Piece> shogiPieces) {
        this.shogiPieces = shogiPieces;
    }

    public GameWithUUID newGame() {
        UUID uuid = UUID.randomUUID();
        ShogiGameBoard board = new ShogiGameBoard(shogiPieces);
        boards.put(uuid, board);
        return new GameWithUUID(board.initialize(), uuid.toString());
    }

    public ShogiGameBoard getBoard(String id) {
        UUID uuid = UUID.fromString(id);
        return boards.get(uuid);
    }
}
