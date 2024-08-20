import {Piece} from "../models/piece";
import {Cell} from "../models/cell";

export function createBoard(): Cell[][] {
  const board: Cell[][] = [];
  for (let i = 0; i < 8; i++) {
    board.push([]);
    for (let j = 0; j < 8; j++) {
      const cell: Cell = {};
      cell.piece = getStartingPiece(i, j, board);
      board[i].push(cell);
    }
  }

  return board;
}

export function getStartingPiece(row: number, col: number, board: Cell[][]) {
  const color = row <= 1 ? 'black' : 'white';

  if (row === 1 || row === 6) {
    return pawn(color);
  }
  if (row === 0 || row === 7) {
    switch (col) {
      case 0:
      case 7:
        return rook(color);
      case 1:
      case 6:
        return knight(color);
      case 2:
      case 5:
        return bishop(color);
      case 3:
        return queen(color);
      case 4:
        return king(color);
    }
  }

  return undefined;
}

export function pawn(color: 'white' | 'black'): Piece {
  return {
    type: 'pawn',
    color,
  };
}

export function rook(color: 'white' | 'black'): Piece {
  return {
    type: 'rook',
    color,
  };
}

export function king(color: 'white' | 'black'): Piece {
  return {
    type: 'king',
    color,
  };
}

export function queen(color: 'white' | 'black'): Piece {
  return {
    type: 'queen',
    color,
  };
}

function knight(color: 'white' | 'black'): Piece {
  return {
    type: 'knight',
    color,
  };
}

function bishop(color: 'white' | 'black'): Piece {
  return {
    type: 'bishop',
    color,
  };
}
