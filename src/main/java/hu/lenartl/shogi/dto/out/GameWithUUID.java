package hu.lenartl.shogi.dto.out;

import hu.lenartl.shogi.game.move.CurrentGameState;

public record GameWithUUID(CurrentGameState currentGameState, String uuid) {

}
