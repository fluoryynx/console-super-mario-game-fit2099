package game;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    TALL, // use this status to tell that current instance has "grown".
    CAN_JUMP,
    POWER_STAR,
    IS_FERTILE,
    SUPER_MUSHROOM,
    POWER_STAR_BUFF, // use this status to tell toad the player have power star buff now, so don't need the conversation about power star.
    HAVE_WRENCH, // use this status to tell toad the player have wrench now, so don't need the conversation about wrench.
    HIDE_IN_SHELL, // use this status to determine actor is a Koopa
    IS_ENEMY
}
