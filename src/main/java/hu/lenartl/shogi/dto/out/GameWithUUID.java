package hu.lenartl.shogi.dto.out;

import hu.lenartl.shogi.game.ShogiGameBoard;

public record GameWithUUID(ShogiGameBoard shogiGameBoard, String uuid) {

}
