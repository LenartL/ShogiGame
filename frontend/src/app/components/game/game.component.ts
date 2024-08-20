import {Component, OnDestroy, OnInit} from '@angular/core';
import {CdkDrag, CdkDragDrop, CdkDragPlaceholder, CdkDropList, CdkDropListGroup} from "@angular/cdk/drag-drop";
import {NgForOf} from "@angular/common";
import {Cell} from "../../models/cell";
import {displayPieces} from "../../utils/constants";
import {Position} from "../../models/position";
import {createBoard} from "../../utils/functions";
import {RxStompService} from "../../rx-stomp/rx-stomp.service";
import {IMessage} from "@stomp/rx-stomp";
import {Subscription} from "rxjs";
import {Movement} from "../../models/movement";
import {GameService} from "../../services/game.service";

@Component({
  selector: 'app-game',
  standalone: true,
  imports: [
    CdkDrag,
    CdkDropList,
    CdkDropListGroup,
    NgForOf,
    CdkDragPlaceholder
  ],
  templateUrl: './game.component.html',
  styleUrl: './game.component.scss'
})
export class GameComponent implements OnInit, OnDestroy {
  board: Cell[][] = createBoard();
  gameSubscription: Subscription;

  constructor(private stompService: RxStompService,
              private gameService: GameService) {
    this.gameSubscription = this.stompService.watch('/game-status/moves')
      .subscribe({
        next: (message: IMessage) => {
          this.move(JSON.parse(message.body))
        }
      })
  }

  ngOnInit(): void {
    this.gameService.newGame().subscribe({
      next: v => this.board = v.cells
      }
    );
  }


  displayPiece(row: number, col: number): string {
    const p = this.board[row][col].piece;
    return p ? displayPieces[`${p.color}${p.type}`] : '';
  }

  getPieceColor(row: number, col: number) {
    return this.board[row][col].piece?.color;
  }

  initiateMove(movement: Movement) {

    this.stompService.publish(
      {
        destination: '/move/initiate',
        body: JSON.stringify(movement)
      })
  }

  moveMyPiece(event: CdkDragDrop<any, Position, Position>) {
    console.log('Drag: ', event.item.data);
    console.log('Drop: ', event.container.data);
    const piece = this.board[event.item.data.row][event.item.data.col].piece!;
    console.log('Piece: ', piece)
    const movement: Movement = {
      from: event.item.data,
      to: event.container.data,
      piece: piece
    }

    this.initiateMove(movement);
  }

  move(movement: Movement) {
    this.board[movement.from.row][movement.from.col] = {};
    this.board[movement.to.row][movement.to.col].piece = movement.piece;
  }

  ngOnDestroy(): void {
    this.gameSubscription.unsubscribe();
  }

}
