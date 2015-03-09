import java.io.{FileNotFoundException, PrintWriter, UnsupportedEncodingException}

import State.length0

import scala.beans.BeanProperty

class State(@BeanProperty var player: Player, @BeanProperty var board: Board, @BeanProperty var lastMove: Move)
  extends Comparable[Any] {

  @BeanProperty
  var children: Array[State] = length0

  @BeanProperty
  var value: Int = 0

  def initializeChildren(): Array[State] = {
    children = board.getPossibleMoves(player).map { move => new State(player.opponent, new Board(board, move), move) }
    children
  }

  def writeToFile() {
    var writerOpt: Option[PrintWriter] = None;
    try {
      writerOpt = Some(new PrintWriter("output.txt", "UTF-8"))
      writerOpt.get.println(this)
    } catch {
      case e@(_: FileNotFoundException | _: UnsupportedEncodingException) => e.printStackTrace()
    } finally {
      writerOpt.get.close();
    }
  }

  override def toString(): String = {
    println("State.toString printing")
    toStringHelper(0, "")
  }

  override def compareTo(o: Any): Int = ???

  private def toStringHelper(d: Int, ind: String): String = {
    var str = ind + player + " to play\n"
    str = str + ind + "Value: " + value + "\n"
    str = str + board.toString(ind) + "\n"
    if (children != null && children.length > 0) {
      str = str + ind + "Children at depth " + (d + 1) + ":\n" + ind +
        "----------------\n"
      for (s <- children) {
        str = str + s.toStringHelper(d + 1, ind + "   ")
      }
    }
    str
  }
}

object State {

  val length0 = Array[State]()
}

