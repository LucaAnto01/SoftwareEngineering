import java.time.LocalDateTime;

/**
 * @author Luca Antognarelli
 *
 */
public record Purchase(int id, Product product, double amount, LocalDateTime  purchaseDate) 
{}
