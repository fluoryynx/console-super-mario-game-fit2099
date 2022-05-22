package game;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 *
 * @author Huang GuoYueYang, Kuah Jia Chen, Lim Fluoryynx
 */
public enum Status {
    /**
     * use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
     */
    HOSTILE_TO_ENEMY,

    /**
     * use this status to tell that current instance has "grown".
     */
    TALL,

    /**
     * use this status to indicate the actor can jump to a particular high ground
     */
    CAN_JUMP,

    /**
     * use this status to indicate that a particular ground is a fertile ground
     */
    IS_FERTILE,

    /**
     * use this status to indicate the player have wrench now
     */
    HAVE_WRENCH,

    /**
     * use this status to determine actor is a Koopa
     */
    HIDE_IN_SHELL,

    /**
     * use this status to determine all enemies
     */
    IS_ENEMY,

    /**
     * use this status to indicate actor is attacked by enemy
     */
    ATTACKED_BY_ENEMY,

    /**
     * use this status to determine player has consumed the Power star
     */
    INVINCIBLE,

    /**
     * use this status to indicate that the item can be consumed by player
     */
    CONSUMABLE,

    /**
     * use this status to indicate that the actor is able to buy
     */
    BUY,

    /**
     * use this status to indicate that can speak to toad
     */
    SPEAK,

    /**
     * use this status to indicate that player enters the reset hotkey
     */
    RESET_CALLED,

    /**
     * use this status to indicate an actor can teleport
     */
    TELEPORT,

    /**
     * use this status to indicate the actor is currently on the first map
     */
    FIRST_MAP,

    /**
     * use this status to indicate the actor is currently on the second map
     */
    SECOND_MAP,

    /**
     * use this status to indicate that player has key or not
     */
    HAVE_KEY,

    /**
     * use this status to indicate that the actor has capability to drop the key or not
     */
    DROP_KEY,

    /**
     * use this status to indicate tha the actor has capability of fly or not
     */
    CAN_FLY,

    /**
     * use this status to indicate the actor can use fire attack
     */
    FIRE_ATTACK,

    /**
     * use this status to indicate a particular ground can do fire damage
     */
    FIRE_DAMAGE,

    /**
     * use this status to indicate actor had a bottle in inventory
     */
    HAS_BOTTLE,

    /**
     * use this status to indicate actor drank power water
     */
    POWER_WATER,

    /**
     * use this status to indicate the ground is a fountain
     */
    IS_FOUNTAIN,

    /**
     * use this status to indicate the ground is a health fountain
     */
    INCREASE_HEALTH,

    /**
     * use this status to indicate the ground is a power fountain
     */
    INCREASE_BASE_DAMAGE,

    /**
     * use this status to indicate water in the fountain is drank by enemies
     */
    DRANK_BY_ENEMY,

    /**
     * use this status to indicate fountain is empty
     */
    IS_EMPTY
}
