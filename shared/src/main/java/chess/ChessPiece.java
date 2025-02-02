package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessPiece that = (ChessPiece) o;
        return pieceColor == that.pieceColor && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, type);
    }

    public boolean inBounds(int row, int col){
        return row >= 1 && row <= 8 && col >= 1 && col <= 8;
    }

    public boolean moveInBounds(ChessMove move){
        ChessPosition pos = move.getEndPosition();
        return inBounds(pos.getRow(), pos.getColumn());
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

        System.out.println("we will be moving a "+ piece.getPieceType());
        switch (piece.getPieceType()){
            case PieceType.BISHOP:
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
                break;
            case PieceType.ROOK:
                System.out.println("Rook time");
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
                break;
            case PieceType.QUEEN:
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
                break;
            case PieceType.KING:
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
                break;
            case PieceType.PAWN:
                if(piece.getTeamColor() == ChessGame.TeamColor.BLACK){
                    System.out.println("Black Pawn moving");
                    if(row == 7){
                        if(board.getPiece(new ChessPosition(row-2,col)) == null && board.getPiece(new ChessPosition(row-1,col)) == null){
                            validMoves.add(new ChessMove(myPosition,new ChessPosition(row-2,col),null));
                        }
                    }
                    if(row-1 != 1) {
                        if (piece.inBounds(row - 1,col) && board.getPiece(new ChessPosition(row - 1, col)) == null) {
                            validMoves.add(new ChessMove(myPosition, new ChessPosition(row - 1, col), null));
                        }
                        if (piece.inBounds(row - 1,col +1) && board.getPiece(new ChessPosition(row - 1, col + 1)) != null) {
                            if (board.getPiece(new ChessPosition(row - 1, col + 1)).getTeamColor() != piece.getTeamColor()) {
                                validMoves.add(new ChessMove(myPosition, new ChessPosition(row - 1, col + 1), null));
                            }
                        }
                        if (piece.inBounds(row - 1,col - 1) && board.getPiece(new ChessPosition(row - 1, col - 1)) != null) {
                            if (board.getPiece(new ChessPosition(row - 1, col - 1)).getTeamColor() != piece.getTeamColor()) {
                                validMoves.add(new ChessMove(myPosition, new ChessPosition(row - 1, col - 1), null));
                            }
                        }
                    }

                    if((row - 1) == 1){
                        if(piece.inBounds(row - 1,col) && board.getPiece(new ChessPosition(row-1,col)) == null) {
                            validMoves.add(new ChessMove(myPosition, new ChessPosition(row - 1, col), PieceType.QUEEN));
                            validMoves.add(new ChessMove(myPosition, new ChessPosition(row - 1, col), PieceType.BISHOP));
                            validMoves.add(new ChessMove(myPosition, new ChessPosition(row - 1, col), PieceType.ROOK));
                            validMoves.add(new ChessMove(myPosition, new ChessPosition(row - 1, col), PieceType.KNIGHT));
                        }
                        if(piece.inBounds(row - 1,col +1) && board.getPiece(new ChessPosition(row-1,col + 1)) != null) {
                            if(board.getPiece(new ChessPosition(row-1,col + 1)).getTeamColor() != piece.getTeamColor()) {
                                validMoves.add(new ChessMove(myPosition, new ChessPosition(row - 1, col+1), PieceType.QUEEN));
                                validMoves.add(new ChessMove(myPosition, new ChessPosition(row - 1, col+1), PieceType.BISHOP));
                                validMoves.add(new ChessMove(myPosition, new ChessPosition(row - 1, col+1), PieceType.ROOK));
                                validMoves.add(new ChessMove(myPosition, new ChessPosition(row - 1, col+1), PieceType.KNIGHT));
                            }
                        }
                        if(piece.inBounds(row - 1,col -1) && board.getPiece(new ChessPosition(row-1,col - 1)) != null) {
                            if(board.getPiece(new ChessPosition(row-1,col - 1)).getTeamColor() != piece.getTeamColor()) {
                                validMoves.add(new ChessMove(myPosition, new ChessPosition(row - 1, col-1), PieceType.QUEEN));
                                validMoves.add(new ChessMove(myPosition, new ChessPosition(row - 1, col-1), PieceType.BISHOP));
                                validMoves.add(new ChessMove(myPosition, new ChessPosition(row - 1, col-1), PieceType.ROOK));
                                validMoves.add(new ChessMove(myPosition, new ChessPosition(row - 1, col-1), PieceType.KNIGHT));
                            }
                        }
                    }
                }

                else{
                    System.out.println("White Pawn moving from: " + row + " " + col);
                    if(row == 2) {
                        if (board.getPiece(new ChessPosition(row + 2, col)) == null && board.getPiece(new ChessPosition(row+1,col)) == null) {
                            validMoves.add(new ChessMove(myPosition, new ChessPosition(row + 2, col), null));
                        }
                    }
                    if(row+1 != 8) {
                        if (piece.inBounds(row + 1,col) && board.getPiece(new ChessPosition(row + 1, col)) == null) {
                            validMoves.add(new ChessMove(myPosition, new ChessPosition(row + 1, col), null));
                        }
                        if (piece.inBounds(row + 1,col +1) && board.getPiece(new ChessPosition(row + 1, col + 1)) != null) {
                            if (board.getPiece(new ChessPosition(row + 1, col + 1)).getTeamColor() != piece.getTeamColor()) {
                                validMoves.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
                            }
                        }
                        if (piece.inBounds(row + 1,col - 1) && board.getPiece(new ChessPosition(row + 1, col - 1)) != null) {
                            if (board.getPiece(new ChessPosition(row + 1, col - 1)).getTeamColor() != piece.getTeamColor()) {
                                validMoves.add(new ChessMove(myPosition, new ChessPosition(row + 1, col - 1), null));
                            }
                        }
                    }

                    if((row + 1) == 8){
                        System.out.println("Moving in to promotion");
                        if(piece.inBounds(row + 1,col) && board.getPiece(new ChessPosition(row + 1,col)) == null) {
                            validMoves.add(new ChessMove(myPosition, new ChessPosition(row + 1, col), PieceType.QUEEN));
                            validMoves.add(new ChessMove(myPosition, new ChessPosition(row + 1, col), PieceType.BISHOP));
                            validMoves.add(new ChessMove(myPosition, new ChessPosition(row + 1, col), PieceType.ROOK));
                            validMoves.add(new ChessMove(myPosition, new ChessPosition(row + 1, col), PieceType.KNIGHT));
                        }
                        if(piece.inBounds(row + 1,col +1) && board.getPiece(new ChessPosition(row + 1,col + 1)) != null) {
                            System.out.println("Taking on the right");
                            if(board.getPiece(new ChessPosition(row-1,col + 1)).getTeamColor() != piece.getTeamColor()) {
                                validMoves.add(new ChessMove(myPosition, new ChessPosition(row + 1, col+1), PieceType.QUEEN));
                                validMoves.add(new ChessMove(myPosition, new ChessPosition(row + 1, col+1), PieceType.BISHOP));
                                validMoves.add(new ChessMove(myPosition, new ChessPosition(row + 1, col+1), PieceType.ROOK));
                                validMoves.add(new ChessMove(myPosition, new ChessPosition(row + 1, col+1), PieceType.KNIGHT));
                            }
                        }
                        if(piece.inBounds(row + 1,col -1) && board.getPiece(new ChessPosition(row + 1,col - 1)) != null) {
                            System.out.println("Taking on the left");
                            if(board.getPiece(new ChessPosition(row + 1,col - 1)).getTeamColor() != piece.getTeamColor()) {
                                validMoves.add(new ChessMove(myPosition, new ChessPosition(row + 1, col-1), PieceType.QUEEN));
                                validMoves.add(new ChessMove(myPosition, new ChessPosition(row + 1, col-1), PieceType.BISHOP));
                                validMoves.add(new ChessMove(myPosition, new ChessPosition(row + 1, col-1), PieceType.ROOK));
                                validMoves.add(new ChessMove(myPosition, new ChessPosition(row + 1, col-1), PieceType.KNIGHT));
                            }
                        }
                    }
                }
                System.out.println("no more pawn moves");
                break;
            case PieceType.KNIGHT:
                System.out.println("Ah eto, bleh");
                // up left, up right, left up, left down, down left, down right, right down, right up
                ArrayList<ChessMove> knightMoves = new ArrayList<>();
                ChessMove knightUL = new ChessMove(myPosition, new ChessPosition(row + 2, col -1), null);
                ChessMove knightUR = new ChessMove(myPosition, new ChessPosition(row + 2, col +1), null);
                ChessMove knightLU = new ChessMove(myPosition, new ChessPosition(row + 1, col -2), null);
                ChessMove knightLD = new ChessMove(myPosition, new ChessPosition(row - 1, col -2), null);
                ChessMove knightDL = new ChessMove(myPosition, new ChessPosition(row - 2, col -1), null);
                ChessMove knightDR = new ChessMove(myPosition, new ChessPosition(row - 2, col +1), null);
                ChessMove knightRU = new ChessMove(myPosition, new ChessPosition(row + 1, col +2), null);
                ChessMove knightRD = new ChessMove(myPosition, new ChessPosition(row - 1, col +2), null);
                knightMoves.add(knightUL);
                knightMoves.add(knightUR);
                knightMoves.add(knightLU);
                knightMoves.add(knightLD);
                knightMoves.add(knightDL);
                knightMoves.add(knightDR);
                knightMoves.add(knightRU);
                knightMoves.add(knightRD);

                for (ChessMove knightMove : knightMoves) {
                    if (moveInBounds(knightMove) && board.getPiece(knightMove.getEndPosition()) != null){
                        if(piece.getTeamColor() != board.getPiece(knightMove.getEndPosition()).getTeamColor()){
                        validMoves.add(knightMove);
                        }
                    } else if(moveInBounds(knightMove) && board.getPiece(knightMove.getEndPosition()) == null) {
                        validMoves.add(knightMove);
                    }
                }
                break;

        }
        return validMoves;
    }
}
