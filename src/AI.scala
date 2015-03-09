class AI(private var player: Player, private var depth: Int) extends Solver {

  override def getMoves(b: Board): Array[Move] = ???

  def minimax(s: State): Unit = {
    if (s.children.isEmpty) {
      s.value = evaluateBoard(s.board)
    } else {
      
      
      val f: (Int, Int) => Int = 
          s.player match {
            case p if p == player => {(a,b) => math.max(a, b)}
            case _ => {(a,b) => math.min(a, b)}
          }
      
      s.children.foreach { child => this.minimax(child) }
      
      s.value = s.children.map { child => child.value }
                          .reduceLeft{ (acc, e) => f(acc, e) }
    }
  }

  def evaluateBoard(b: Board): Int = {
    val winner = b.hasConnectFour()
    var value = 0
    if (!winner.isDefined) {
      val locs = b.winLocations()
      for (loc <- locs; p <- loc) {
        value += (if (p == player) 1 else if (p != null) -1 else 0)
      }
    } else {
      var numEmpty = 0
      var r = 0
      while (r < Board.NUM_ROWS) {
        var c = 0
        while (c < Board.NUM_COLS) {
          if (b.getTile(r, c) == null) numEmpty += 1
          c = c + 1
        }
        r = r + 1
      }
      value = (if (winner.get == player) 1 else -1) * 10000 * numEmpty
    }
    value
  }
}

object AI {

  def createGameTree(s: State, d: Int): Unit = {
    if (d == 0)
      return;
    else if (s.board.hasConnectFour().isDefined) 
      return;
    else {
      s.initializeChildren().foreach { child => AI.createGameTree(child, d-1) };
    }
  }

  def minimax(ai: AI, s: State) {
    ai.minimax(s)
  }
}

