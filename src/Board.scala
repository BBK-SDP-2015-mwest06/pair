import scala.collection.mutable.ListBuffer

class Board {

  private val FOUR = 4

  private val deltas = Array(Array(1, 0), Array(0, 1), Array(-1, 1), Array(1, 1))

  private val board = Array.ofDim[Player](Board.NUM_ROWS, Board.NUM_COLS)

  def this(b: Board) {
    this()
    for (r <- 0 until Board.NUM_ROWS; c <- 0 until Board.NUM_COLS)
      board(r)(c) = b.board(r)(c)
  }

  def getPlayer(r: Int, c: Int): Player = {
    assert(0 <= r && r < Board.NUM_ROWS && 0 <= c && c < Board.NUM_COLS)
    board(r)(c)
  }

  def this(b: Board, nextMove: Move) {
    this(b)
    makeMove(nextMove)
  }

  /**
   * Apply Move move to this Board by placing a piece from move's
   * player into move's column on this Board.
   * Throw an IllegalArgumentException if move's column is full on this Board.
   */
  def makeMove(move: Move): Unit = {
    val row: Option[Int] = this.getAvailableMoveForColumn( move.column);
    row match {
      case Some(x) => board(x)(move.column) = move.player
      case _ => throw new IllegalArgumentException("No available move for this column")
    }
  }

  def getTile(row: Int, col: Int): Player = board(row)(col)

  def getPossibleMoves(p: Player): Array[Move] = {
    board(0).zipWithIndex.collect({case (hole, col) if hole == null => new Move(p, col)})
  }

  override def toString(): String = ???

  def toString(prefix: String): String = {
    val str = new StringBuilder("")
    for (row <- board) {
      str.append(prefix + "|")
      for (spot <- row) {
        if (spot == null) {
          str.append(" |")
        } else if (spot == RED) {
          str.append("R|")
        } else {
          str.append("Y|")
        }
      }
      str.append("\n")
    }
    str.toString
  }

  def hasConnectFour(): Option[Player] = {
    winLocations().find(loc => loc(0) != null && loc(0) == loc(1) && loc(0) == loc(2) &&
      loc(0) == loc(3))
      .map(_(0))
      .orElse(None)
  }

  def winLocations(): List[Array[Player]] = {
    val locations = ListBuffer[Array[Player]]()
    for (delta <- deltas; r <- 0 until Board.NUM_ROWS; c <- 0 until Board.NUM_COLS) {
      val loc = possibleWin(r, c, delta)
      if (loc != null) {
        locations += loc
      }
    }
    locations.toList
  }

  def possibleWin(r: Int, c: Int, delta: Array[Int]): Array[Player] = {
    val location = Array.ofDim[Player](FOUR)
    for (i <- 0 until FOUR) {
      val newR = r + i * delta(0)
      val newC = c + i * delta(1)
      if (0 <= newR && newR < Board.NUM_ROWS && 0 <= newC && newC < Board.NUM_COLS) {
        location(i) = board(newR)(newC)
      }
    }
    location
  }
  
  private def getAvailableMoveForColumn(column: Int): Option[Int] = {
    board.lastIndexWhere { arr => arr(column) == null } match {
      case x if (x >= 0) => Some(x)
      case _ => None
    }
  }
}

object Board {
  val NUM_ROWS = 6
  val NUM_COLS = 7

  def apply(b: Board): Board =
    new Board(b)

  def apply(): Board =
    new Board()

  def fillColumn(b: Board, p: Player, col: Int): Unit = {
//    for (r <- 0 until Board.NUM_ROWS) {
//      if (b(r)(col) == null) b(r)(col) == p
//    }
    try {
      while(true) {
        b.makeMove(new Move(p, col))
      }
    } catch {
      case e@(_: IllegalArgumentException) => println(s"Column $col filled!")
    }
  }
}
