package chess;

import java.util.ArrayList;
import java.util.Collection;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {
    private ChessBoard gameBoard = new ChessBoard();
    private TeamColor turn = TeamColor.WHITE;
    private ChessBoard trialBoard = new ChessBoard();
    public ChessGame() {
        gameBoard.resetBoard();
    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return turn;
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        turn = team;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition){
        ChessPiece piece = new ChessPiece(getBoard().getPiece(startPosition).getTeamColor(),getBoard().getPiece(startPosition).getPieceType());
        ArrayList<ChessMove> possibleMoves = new ArrayList<>();
        possibleMoves = (ArrayList<ChessMove>) piece.pieceMoves(getBoard(),startPosition);
        ArrayList<ChessMove> invalidMoves = new ArrayList<>();
        trialBoard = gameBoard.clone();

        ChessPosition end = new ChessPosition(0,0); // clearly wrong but will be overwritten
        for(int i = 0; i < possibleMoves.size(); i++){
            end =  possibleMoves.get(i).getEndPosition();
            trialBoard.addPiece(startPosition,null);
            trialBoard.addPiece(end,piece);
            if(isInCheck(piece.getTeamColor())){
                invalidMoves.add(possibleMoves.get(i));
            }
            trialBoard = gameBoard.clone();
        }
        possibleMoves.removeAll(invalidMoves);

        return possibleMoves;
    }

    public void changeTeamTurn(){
        if(getTeamTurn() == TeamColor.BLACK){
            setTeamTurn(TeamColor.WHITE);
        } else {
            setTeamTurn(TeamColor.BLACK);
        }
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        ChessPosition start = move.getStartPosition();
        ChessPosition end = move.getEndPosition();
        ChessPiece piece = gameBoard.getPiece(start);
        ChessPiece.PieceType promoteRank = move.getPromotionPiece();
        if(promoteRank != null){
            piece = new ChessPiece(getTeamTurn(),promoteRank);
        }
        //check if piece is actually a piece
        if(piece != null){
            System.out.println("It's a piece");
            if(getTeamTurn() == piece.getTeamColor()){ //is it the right turn?
                System.out.println("It's YOUR piece");
                if(validMoves(start).contains(move)){ //is this a move the piece can actually make
                    System.out.println("IT'S A REAL MOVE");
                    gameBoard.addPiece(start,null);
                    gameBoard.addPiece(end,piece);
                    trialBoard = gameBoard.clone();
                    if(piece.getTeamColor() == TeamColor.BLACK){
                        if(isInCheck(TeamColor.WHITE)) {
                            isInCheckmate(TeamColor.WHITE);
                        }else{
                            isInStalemate(TeamColor.WHITE);
                        }
                    }else {
                        if(isInCheck(TeamColor.BLACK)) {
                            isInCheckmate(TeamColor.BLACK);
                        }else {
                            isInStalemate(TeamColor.BLACK);
                        }
                    }
                    changeTeamTurn();
                }else{
                    throw new InvalidMoveException("So sorry, not a valid move");
                }
            } else {
                throw new InvalidMoveException("Not your piece");
            }
        } else{
            throw new InvalidMoveException("Not a piece");
        }
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        ArrayList<ChessPosition> enemyPositions = new ArrayList<>();
        ArrayList<ChessMove> enemyMoves = new ArrayList<>();
        ChessPosition myKingPos = new ChessPosition(1,1); //clearly wrong, but will always be overwritten. If not, there's a bug

        for(int i = 1; i<=8; i++){
            for(int j = 1; j<=8; j++){
                if(trialBoard.getPiece(new ChessPosition(i,j)) != null){
                    if(trialBoard.getPiece(new ChessPosition(i,j)).getTeamColor() != teamColor){
                        enemyPositions.add(new ChessPosition(i,j));
                    }
                    if(trialBoard.getPiece(new ChessPosition(i,j)).getPieceType() == ChessPiece.PieceType.KING && trialBoard.getPiece(new ChessPosition(i,j)).getTeamColor() == teamColor){
                        myKingPos = new ChessPosition(i,j);
                    }
                }
            }
        }

        for(int i = 0; i<enemyPositions.size();i++){
            enemyMoves.addAll(trialBoard.getPiece(enemyPositions.get(i)).pieceMoves(trialBoard,enemyPositions.get(i)));
        }
        //System.out.println("Total Enemies: " + enemyPositions.size());
        //System.out.println("Total Moves by the enemy: " + enemyMoves.size());
        ArrayList<ChessPosition> destinations = new ArrayList<>();
        for(int i = 0; i < enemyMoves.size(); i++){
            destinations.add(enemyMoves.get(i).getEndPosition());
        }
        //System.out.println("Defending King position:" + myKingPos.getRow() + ", " + myKingPos.getColumn());
        //System.out.println("Possible Destinations" + destinations);
        return destinations.contains(myKingPos);

    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
       return isInEndgame(teamColor);
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        return isInEndgame(teamColor) && !isInCheck(teamColor);
    }

    public boolean isInEndgame(TeamColor teamColor){
        ArrayList<ChessPosition> teamPos;
        ArrayList<ChessMove> allMoves = new ArrayList<>();
        ArrayList<ChessMove> savingMoves = new ArrayList<>();
        teamPos = (ArrayList<ChessPosition>) teamPos(teamColor);

        for(int i = 0; i< teamPos.size();i++){
            System.out.println("This piece is a: " +trialBoard.getPiece(teamPos.get(i)));
            if(!validMoves(teamPos.get(i)).isEmpty()){
                allMoves.addAll(validMoves(teamPos.get(i)));
            }
        }
        trialBoard = gameBoard.clone();
        ChessPosition start;
        ChessPosition end;
        ChessPiece piece;
        for(int i = 0; i<allMoves.size();i++){
            start = allMoves.get(i).getStartPosition();
            end = allMoves.get(i).getEndPosition();
            piece = trialBoard.getPiece(start);
            trialBoard.addPiece(start,null);
            trialBoard.addPiece(end,piece);
            if(!isInCheck(teamColor)){
                savingMoves.add(allMoves.get(i));
            }
            trialBoard = gameBoard.clone();
        }

        return savingMoves.isEmpty();
    }

    public Collection<ChessPosition> teamPos(TeamColor teamColor){
        ArrayList<ChessPosition> teamPos = new ArrayList<>();
        for(int i = 1; i <= 8; i++){
            for(int j = 1; j <= 8; j++){
                if(trialBoard.getPiece(new ChessPosition(i,j)) != null ) {
                    if (trialBoard.getPiece(new ChessPosition(i, j)).getTeamColor() == teamColor) {
                        teamPos.add(new ChessPosition(i, j));
                    }
                }
            }
        }
        return teamPos;
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        gameBoard = board;
        trialBoard = gameBoard.clone();
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return gameBoard;
    }




}
