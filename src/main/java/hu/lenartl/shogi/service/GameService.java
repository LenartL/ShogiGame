package hu.lenartl.shogi.service;

import hu.lenartl.shogi.dto.out.GameWithUUID;
import hu.lenartl.shogi.game.ShogiGameBoard;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class GameService {

    private final ConcurrentMap<UUID, ShogiGameBoard> boards = new ConcurrentHashMap<>();

    public GameWithUUID newGame() {
        UUID uuid = UUID.randomUUID();
        ShogiGameBoard board = new ShogiGameBoard(new HashMap<>());
        boards.put(uuid, board);
        return new GameWithUUID(board, uuid.toString());
    }

    public ShogiGameBoard getBoard(String id) {
        UUID uuid = UUID.fromString(id);
        return boards.get(uuid);
    }
}
