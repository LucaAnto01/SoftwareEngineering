import java.time.LocalDateTime;

/**
 * @author Luca Antognarelli
 *
 */
public record Purchase(int id, String productName, String productManufacturer, double amount, LocalDateTime  purchaseDate) 
{}
