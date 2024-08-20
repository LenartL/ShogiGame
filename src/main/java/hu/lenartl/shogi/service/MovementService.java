package hu.lenartl.shogi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovementService {
    private final GameService gameService;

    @Autowired
    public MovementService(GameService gameService) {
        this.gameService = gameService;
    }


}
