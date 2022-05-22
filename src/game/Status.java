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
    TELEPORT, // use this status to indicate an actor can teleport
    FIRST_MAP, // use this status to indicate the actor is currently on the first map
    SECOND_MAP, // use this status to indicate the actor is currently on the second map
    HAVE_KEY, // use this status to indicate that player has key or not
    DROP_KEY, // use this status to indicate that the actor has capability to drop the key or not
    CAN_FLY, // use this status to indicate tha the actor has capability of fly or not
    FIRE_ATTACK, // use this status to indicate the actor can use fire attack
    FIRE_DAMAGE, // use this status to indicate a particular ground can do fire damage
    HAS_BOTTLE, // use this status to indicate actor had a bottle in inventory
    POWER_WATER, // use this status to indicate actor drank power water
    IS_FOUNTAIN, // use this status to indicate the ground is a fountain
    INCREASE_HEALTH, // use this status to indicate the ground is a health fountain
    INCREASE_BASE_DAMAGE, //use this status to indicate the ground is a power fountain
    DRANK_BY_ENEMY, // use this status to indicate water in the fountain is drank by enemies
    IS_EMPTY // use this status to indicate fountain is empty
}
