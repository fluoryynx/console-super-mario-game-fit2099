package game;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 *
 * @author Huang GuoYueYang, Kuah Jia Chen, Lim Fluoryynx
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    TALL, // use this status to tell that current instance has "grown".
    CAN_JUMP, // use this status to indicate the actor can jump to a particular high ground
    IS_FERTILE, // use this status to indicate that a particular ground is a fertile ground
    HAVE_WRENCH, // use this status to indicate the player have wrench now
    HIDE_IN_SHELL, // use this status to determine actor is a Koopa
    IS_ENEMY, // use this status to determine all enemies
    ATTACKED_BY_ENEMY, // use this status to indicate actor is attacked by enemy
    INVINCIBLE, // use this status to determine player has consumed the Power star
    CONSUMABLE, // use this status to indicate that the item can be consumed by player
    BUY, // use this status to indicate that the actor is able to buy
    SPEAK, // use this status to indicate that can speak to toad
    RESET_CALLED, // use this status to indicate that player enters the reset hotkey
    TELEPORT,
    FIRST_MAP,
    SECOND_MAP,
    HAVE_KEY, // use this status to indicate that player has key or not
    DROP_KEY, // use this status to indicate that the actor is Bowser or not
    CAN_FLY, // use this status to indicate tha the actor is Flying Koopa or not
    FIRE_ATTACK,
    FIRE_DAMAGE,
    HAS_BOTTLE,
    POWER_WATER,
    IS_FOUNTAIN,
    INCREASE_HEALTH,
    INCREASE_BASE_DAMAGE,
    DRANK_BY_ENEMY,
    IS_EMPTY
}
