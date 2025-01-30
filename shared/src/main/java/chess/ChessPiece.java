package chess;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private final ChessGame.TeamColor pieceColor;
    private final ChessPiece.PieceType type;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.type = type;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return type;
    }

    @Override
    public String toString() {
        return "ChessPiece{" +
                "pieceColor=" + pieceColor +
                ", type=" + type +
                '}';
    }


    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ChessPiece piece = board.getPiece(myPosition);
        ArrayList<ChessMove> validMoves = new ArrayList<>();
        int row = myPosition.getRow();
        int col = myPosition.getColumn();
        int newrows;
        int newcols;

        switch (piece.getPieceType()){
            case BISHOP:
                 newrows = row + 1;
                 newcols = col + 1;
                while(newrows <= 8 && newcols <= 8){
                    //row+ col+
                    if(board.getPiece((new ChessPosition(newrows,newcols))) != null){
                        if(board.getPiece((new ChessPosition(newrows,newcols))).getTeamColor() != piece.getTeamColor()){
                            validMoves.add(new ChessMove(myPosition,new ChessPosition(newrows,newcols),null));
                        }
                        break;
                    }
                    validMoves.add(new ChessMove(myPosition,new ChessPosition(newrows,newcols),null));
                    newrows++;
                    newcols++;
                }

                newrows = row - 1;
                newcols = col - 1;
                while(newrows > 0 && newcols > 0){
                    //row- col-
                    if(board.getPiece((new ChessPosition(newrows,newcols))) != null){
                        if(board.getPiece((new ChessPosition(newrows,newcols))).getTeamColor() != piece.getTeamColor()){
                            validMoves.add(new ChessMove(myPosition,new ChessPosition(newrows,newcols),null));
                        }
                        break;
                    }

                    validMoves.add(new ChessMove(myPosition,new ChessPosition(newrows,newcols),null));
                    newrows--;
                    newcols--;
                }

                newrows = row + 1;
                newcols = col - 1;
                while(newrows <= 8 && newcols > 0){
                    //row+ col-
                    //System.out.println("row: " + newrows + " col: " +newcols);
                    if(board.getPiece((new ChessPosition(newrows,newcols))) != null) {
                        if(board.getPiece((new ChessPosition(newrows,newcols))).getTeamColor() != piece.getTeamColor()){
                            validMoves.add(new ChessMove(myPosition,new ChessPosition(newrows,newcols),null));
                        }
                        break;
                    }
                    validMoves.add(new ChessMove(myPosition,new ChessPosition(newrows,newcols),null));
                    newrows++;
                    newcols--;
                }

                newrows = row - 1;
                newcols = col + 1;
                while(newrows > 0 && newcols <= 8){
                    //row- col-
                    if(board.getPiece((new ChessPosition(newrows,newcols))) != null){
                        if(board.getPiece((new ChessPosition(newrows,newcols))).getTeamColor() != piece.getTeamColor()){
                            validMoves.add(new ChessMove(myPosition,new ChessPosition(newrows,newcols),null));
                        }
                        break;
                    }
                    validMoves.add(new ChessMove(myPosition,new ChessPosition(newrows,newcols),null));
                    newrows--;
                    newcols++;
                }
            case ROOK:
                newrows = row +1;
                newcols = col;
                while(newrows <= 8){
                    if(board.getPiece((new ChessPosition(newrows,newcols))) != null){
                        if(board.getPiece((new ChessPosition(newrows,newcols))).getTeamColor() != piece.getTeamColor()){
                            validMoves.add(new ChessMove(myPosition,new ChessPosition(newrows,newcols),null));
                        }
                        break;
                    }
                    validMoves.add(new ChessMove(myPosition,new ChessPosition(newrows,newcols),null));
                    newrows++;
                }

                newrows = row - 1;
                newcols = col;
                while(newrows > 0){
                    if(board.getPiece((new ChessPosition(newrows,newcols))) != null){
                        if(board.getPiece((new ChessPosition(newrows,newcols))).getTeamColor() != piece.getTeamColor()){
                            validMoves.add(new ChessMove(myPosition,new ChessPosition(newrows,newcols),null));
                        }
                        break;
                    }
                    validMoves.add(new ChessMove(myPosition,new ChessPosition(newrows,newcols),null));
                    newrows--;
                }

                newrows = row;
                newcols = col + 1;
                while(newcols <= 8){
                    if(board.getPiece((new ChessPosition(newrows,newcols))) != null){
                        if(board.getPiece((new ChessPosition(newrows,newcols))).getTeamColor() != piece.getTeamColor()){
                            validMoves.add(new ChessMove(myPosition,new ChessPosition(newrows,newcols),null));
                        }
                        break;
                    }
                    validMoves.add(new ChessMove(myPosition,new ChessPosition(newrows,newcols),null));
                    newcols++;
                }

                newrows = row;
                newcols = col -1;
                while(newcols > 0){
                    if(board.getPiece((new ChessPosition(newrows,newcols))) != null){
                        if(board.getPiece((new ChessPosition(newrows,newcols))).getTeamColor() != piece.getTeamColor()){
                            validMoves.add(new ChessMove(myPosition,new ChessPosition(newrows,newcols),null));
                        }
                        break;
                    }
                    validMoves.add(new ChessMove(myPosition,new ChessPosition(newrows,newcols),null));
                    newcols--;
                }

            case QUEEN:
                newrows = row + 1;
                newcols = col + 1;
                while(newrows <= 8 && newcols <= 8){
                    //row+ col+
                    if(board.getPiece((new ChessPosition(newrows,newcols))) != null){
                        if(board.getPiece((new ChessPosition(newrows,newcols))).getTeamColor() != piece.getTeamColor()){
                            validMoves.add(new ChessMove(myPosition,new ChessPosition(newrows,newcols),null));
                        }
                        break;
                    }
                    validMoves.add(new ChessMove(myPosition,new ChessPosition(newrows,newcols),null));
                    newrows++;
                    newcols++;
                }

                newrows = row - 1;
                newcols = col - 1;
                while(newrows > 0 && newcols > 0){
                    //row- col-
                    if(board.getPiece((new ChessPosition(newrows,newcols))) != null){
                        if(board.getPiece((new ChessPosition(newrows,newcols))).getTeamColor() != piece.getTeamColor()){
                            validMoves.add(new ChessMove(myPosition,new ChessPosition(newrows,newcols),null));
                        }
                        break;
                    }

                    validMoves.add(new ChessMove(myPosition,new ChessPosition(newrows,newcols),null));
                    newrows--;
                    newcols--;
                }

                newrows = row + 1;
                newcols = col - 1;
                while(newrows <= 8 && newcols > 0){
                    //row+ col-
                    //System.out.println("row: " + newrows + " col: " +newcols);
                    if(board.getPiece((new ChessPosition(newrows,newcols))) != null) {
                        if(board.getPiece((new ChessPosition(newrows,newcols))).getTeamColor() != piece.getTeamColor()){
                            validMoves.add(new ChessMove(myPosition,new ChessPosition(newrows,newcols),null));
                        }
                        break;
                    }
                    validMoves.add(new ChessMove(myPosition,new ChessPosition(newrows,newcols),null));
                    newrows++;
                    newcols--;
                }

                newrows = row - 1;
                newcols = col + 1;
                while(newrows > 0 && newcols <= 8){
                    //row- col-
                    if(board.getPiece((new ChessPosition(newrows,newcols))) != null){
                        if(board.getPiece((new ChessPosition(newrows,newcols))).getTeamColor() != piece.getTeamColor()){
                            validMoves.add(new ChessMove(myPosition,new ChessPosition(newrows,newcols),null));
                        }
                        break;
                    }
                    validMoves.add(new ChessMove(myPosition,new ChessPosition(newrows,newcols),null));
                    newrows--;
                    newcols++;
                }

                newrows = row +1;
                newcols = col;
                while(newrows <= 8){
                    if(board.getPiece((new ChessPosition(newrows,newcols))) != null){
                        if(board.getPiece((new ChessPosition(newrows,newcols))).getTeamColor() != piece.getTeamColor()){
                            validMoves.add(new ChessMove(myPosition,new ChessPosition(newrows,newcols),null));
                        }
                        break;
                    }
                    validMoves.add(new ChessMove(myPosition,new ChessPosition(newrows,newcols),null));
                    newrows++;
                }

                newrows = row - 1;
                newcols = col;
                while(newrows > 0){
                    if(board.getPiece((new ChessPosition(newrows,newcols))) != null){
                        if(board.getPiece((new ChessPosition(newrows,newcols))).getTeamColor() != piece.getTeamColor()){
                            validMoves.add(new ChessMove(myPosition,new ChessPosition(newrows,newcols),null));
                        }
                        break;
                    }
                    validMoves.add(new ChessMove(myPosition,new ChessPosition(newrows,newcols),null));
                    newrows--;
                }

                newrows = row;
                newcols = col + 1;
                while(newcols <= 8){
                    if(board.getPiece((new ChessPosition(newrows,newcols))) != null){
                        if(board.getPiece((new ChessPosition(newrows,newcols))).getTeamColor() != piece.getTeamColor()){
                            validMoves.add(new ChessMove(myPosition,new ChessPosition(newrows,newcols),null));
                        }
                        break;
                    }
                    validMoves.add(new ChessMove(myPosition,new ChessPosition(newrows,newcols),null));
                    newcols++;
                }

                newrows = row;
                newcols = col -1;
                while(newcols > 0){
                    if(board.getPiece((new ChessPosition(newrows,newcols))) != null){
                        if(board.getPiece((new ChessPosition(newrows,newcols))).getTeamColor() != piece.getTeamColor()){
                            validMoves.add(new ChessMove(myPosition,new ChessPosition(newrows,newcols),null));
                        }
                        break;
                    }
                    validMoves.add(new ChessMove(myPosition,new ChessPosition(newrows,newcols),null));
                    newcols--;
                }
            case KING:
                for(int i = -1; i <= 1;i++){
                    for(int j = -1; j <=1;j++){
                       if(!((i==0) && (j==0)) && ((row + i) <= 8 && (row + i) > 0) && ((col + j) <= 8 && (col + j) > 0)){
                           if(board.getPiece(new ChessPosition(row + i,col+j)) != null){
                               if(board.getPiece(new ChessPosition(row + i,col+j)).getTeamColor() != piece.getTeamColor()){
                                   validMoves.add(new ChessMove(myPosition,new ChessPosition(row + i,col + j),null));
                               }
                           }
                           else{
                               validMoves.add(new ChessMove(myPosition,new ChessPosition(row + i,col + j),null));
                           }
                       }
                    }
                }
            case PAWN:



        }
        return validMoves;
    }
}
