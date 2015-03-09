import org.scalatest.FlatSpec

class AISpec extends FlatSpec {

//  "createGameTree" should "function correctly on empty board" in {
//    var b: Board = new Board();
//    
//    var s: State = new State(RED, b, null)
//    
//    AI.createGameTree(s, 4);
//    
//    s.writeToFile();
//  }
//  
//  "createGameTree" should "instantly return on board with filled columns die to connect 4" in {
//    var b: Board = new Board();
//    Board.fillColumn(b, YELLOW, 0)
//    Board.fillColumn(b, YELLOW, 4)
//
//    
//    var s: State = new State(RED, b, null)
//    
//    AI.createGameTree(s, 3);
//    
//    s.writeToFile();
//  }
  
//  "createGameTree" should "cut tree at depth 4 and when RED has connect 4 (column 0)" in {
//    var b: Board = new Board();
//    
//    b.makeMove(new Move(RED, 0))
//    b.makeMove(new Move(YELLOW, 1))
//    b.makeMove(new Move(RED, 0))
//    b.makeMove(new Move(YELLOW, 4))
//    
//    var s: State = new State(RED, b, null)
//    
//    AI.createGameTree(s, 4);
//    
//    s.writeToFile();
//  }
  
    "minimax" should "assign correct minimax value to base state" in {
    var b: Board = new Board();
    
    b.makeMove(new Move(RED, 0))
    b.makeMove(new Move(YELLOW, 1))
    b.makeMove(new Move(RED, 0))
    b.makeMove(new Move(YELLOW, 4))
    
    var s: State = new State(RED, b, null)
    
    AI.createGameTree(s, 6);
    val ai = new AI(RED, 6);
    
    ai.minimax(s);
    
    s.writeToFile();
  }
  
}