package model.entities;

/**
 * 
 * This enum represent the current entity's status, it can be ALIVE, DEAD or FLOWN_AWAY(if is duck).
 *
 */

public enum EntityStatus {
    DEAD("Dead"),
    
    ALIVE("Alive"),
    
    FLOWN_AWAY("Flown Away");
    
    private final String description;
    
    private EntityStatus(final String description) {
        this.description = description;
    }
    
    public String getStatusDescription() {
        return this.description;
    }
}
