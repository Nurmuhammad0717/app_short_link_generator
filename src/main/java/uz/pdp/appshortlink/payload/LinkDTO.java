package uz.pdp.appshortlink.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link uz.pdp.appshortlink.entity.Link}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkDTO implements Serializable {
    private UUID id;
    private String destinationUrl;
    private String shortUrl;
    private String name;
    private String description;
}