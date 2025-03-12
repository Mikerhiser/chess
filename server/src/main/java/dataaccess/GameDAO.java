package dataaccess;

import chess.ChessGame;

public interface GameDAO {

    void createGame();
    void readGame();
    void updateGame();
    void deleteGame();
    void clearGames();
}
