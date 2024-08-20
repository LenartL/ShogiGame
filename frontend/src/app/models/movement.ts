import {Position} from "./position";
import {Piece} from "./piece";

export interface Movement {
  from: Position,
  to: Position,
  piece: Piece
}
