package App;
import java.io.*;
/**
 * @author Daniel Santo-Tomas daniel.santo-tomas@estudiante.uam.es
 * @author Lucia Rivas Molina lucia.rivas@estudiante.uam.es
 *
 */
public enum UserStates implements Serializable {
	CONNECTED_HOST, CONNECTED_GUEST, DISCONNECTED, BANNED, ADMIN;
}
