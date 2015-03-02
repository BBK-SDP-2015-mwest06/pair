

import org.scalatest.FlatSpec


class BoardSpec extends FlatSpec {

  "possibleMoves" should "not return filled columns" in {
    var b: Board = new Board();
    Board.fillColumn(b, RED, 0);  // fill column 0
    Board.fillColumn(b, RED, 5);  // fill column 0

    val moves: Array[Move]= b.getPossibleMoves(RED)
    moves.foreach { move => println(move) }
    assert(!(moves.exists { move => move.column == 0 }))
    assert(!(moves.exists { move => move.column == 5 }))
  }
  
  
}