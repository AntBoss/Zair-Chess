package com.company;

public enum Types {
    BISHOP(0),
    KING(1),
    KNIGHT(2),
    PAWN(3),
    QUEEN(4),
    ROOK(5),
    EMPTY(6),
    VOID(7);

    final int typeToInt;

    Types(int typeToInt){
        this.typeToInt = typeToInt;
    }
}
