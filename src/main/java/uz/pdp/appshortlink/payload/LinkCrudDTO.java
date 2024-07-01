package uz.pdp.appshortlink.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appshortlink.entity.Link;

import java.io.Serializable;

/**
 * DTO for {@link Link}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkCrudDTO implements Serializable {
    @NotNull
    @NotBlank
    private String destinationUrl;
    private String name;
    private String description;
}