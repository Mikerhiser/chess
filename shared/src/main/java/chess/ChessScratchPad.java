package chess;

public class ChessScratchPad {
    public static void main(String[] args) {
        ChessBoard board = new ChessBoard();
        board.printBoard();
        System.out.print("\n");
        board.resetBoard();
        board.printBoard();
    }
}
