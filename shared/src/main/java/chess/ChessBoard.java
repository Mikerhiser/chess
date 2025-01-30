package chess;

import java.util.Arrays;
import java.util.Objects;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {
    private final ChessPiece[][] squares = new ChessPiece[8][8];
    public ChessBoard() {

    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        squares[position.getRow()-1][position.getColumn()-1] = piece;
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        return squares[position.getRow()-1][position.getColumn()-1];
    }

    public void printBoard(){
        for(int i = 0; i<=7; i++){
            for(int j = 0; j<=7; j++){
                System.out.print(squares[i][j] + " ");
            }
            System.out.print("\n");
        }


    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        // Shorthand for my pieces
        ChessPiece p = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.PAWN);
        ChessPiece n = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.KNIGHT);
        ChessPiece b = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.BISHOP);
        ChessPiece r = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.ROOK);
        ChessPiece q = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.QUEEN);
        ChessPiece k = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.KING);

        ChessPiece P = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.PAWN);
        ChessPiece N = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.KNIGHT);
        ChessPiece B = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.BISHOP);
        ChessPiece R = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.ROOK);
        ChessPiece Q = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.QUEEN);
        ChessPiece K = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.KING);

        for(int i=1;i <= 8;i++){
            addPiece(new ChessPosition(2,i),P);
            addPiece(new ChessPosition(7,i),p);
        }

    }



    @Override
    public String toString() {
        return "ChessBoard{" +
                "squares=" + Arrays.toString(squares) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessBoard that = (ChessBoard) o;
        return Objects.deepEquals(squares, that.squares);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(squares);
    }
}
