package com.guilhermebury.agalaxyfaraway.data;

/**
 * Created by Guilherme Bury on 21/07/17.
 */

public enum EnumFilmsPosition {
    A_NEW_HOPE(0),
    THE_EMPIRE_STRIKES_BACK(1),
    RETURN_OF_THE_JEDI(2),
    THE_PHANTOM_MENACE(3),
    ATTACK_OF_THE_CLONES(4),
    REVENGE_OF_THE_SITH(5);

    public int filmPosition;

    EnumFilmsPosition(int filmPosition) {
        this.filmPosition = filmPosition;
    }

    public int getPosition() {
        return filmPosition;
    }
}
