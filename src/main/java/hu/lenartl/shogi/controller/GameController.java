package hu.lenartl.shogi.controller;

import hu.lenartl.shogi.dto.out.GameWithUUID;
import hu.lenartl.shogi.game.ShogiGameBoard;
import hu.lenartl.shogi.service.GameService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
@CrossOrigin
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/new")
    public GameWithUUID newGame() {
        return gameService.newGame();
    }

    @GetMapping("/{uuid}")
    public ShogiGameBoard getGameByUUID(@PathVariable String uuid) {
        return gameService.getBoard(uuid);
    }
}
