package chess;

public class ChessScratchPad {
    public static void main(String[] args) {
        ChessBoard board = new ChessBoard();
        ChessPiece bishop = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP);
        ChessPosition pos = new ChessPosition(5, 4);
        board.addPiece(pos,bishop);
        System.out.println(bishop.pieceMoves(board,pos));

        }


    }
