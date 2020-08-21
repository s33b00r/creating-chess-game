package model.chesspieces;

/**
 * King = 'K' or 'M' if has moved
 * Queen = 'Q'
 * Rook = 'R' or 'H' if has moved
 * Bishop = 'B'
 * Knight = 'N'
 * Pawn = 'P'
 * Nothing = '-' or 'E' if can do En passant to that square
 */
public enum PieceData {
    KING('K'),
    MOVED_KING('M'),
    QUEEN('Q'),
    ROOK('R'),
    MOVED_ROOK('H'),
    BISHOP('B'),
    KNIGHT('N'),
    PAWN('P'),
    NOTHING('-'),
    EN_PASSANT('E');

    private char value;

    PieceData(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }
}
