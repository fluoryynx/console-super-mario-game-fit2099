package game;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    TALL, // use this status to tell that current instance has "grown".
    CAN_JUMP,
    IS_FERTILE,
    SUPER_MUSHROOM,
    HAVE_WRENCH, // use this status to indicate the player have wrench now
    HIDE_IN_SHELL, // use this status to determine actor is a Koopa
    IS_ENEMY, // use this status to determine all enemies
    ATTACKED_BY_ENEMY,
    INVINCIBLE, // use this status to determine player has consumed the Power star
    CONSUMABLE,
    BUY,
    SPEAK, // use this status to indicate that can speak to toad
    RESET_CALLED,
}
