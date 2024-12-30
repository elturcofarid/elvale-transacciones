package co.onlysystems.transacciones.shared.values;

import java.util.UUID;

public class UuidVale {

    private final UUID uuid;

    public UuidVale(String uuid) {
        if (!isValidUUID(uuid)) throw new IllegalArgumentException("El UUID no es válido.");
        this.uuid = UUID.fromString(uuid);
    }

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return uuid.toString();
    }

    public static boolean isValidUUID(String uuid) {

        if (uuid == null) {
            return false; 
        }
        
        if (uuid.length() != 36) {
            return false; 
        }

        String uuidPattern = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$";
        if (!uuid.matches(uuidPattern)) {
            return false; 
        }

        try {
            UUID.fromString(uuid); 
            return true; 
        } catch (IllegalArgumentException e) {
            return false; 
        }
    }
}
