package pe.pagos.core.pagosplazos.domain.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.pagos.core.pagosplazos.dto.TransaccionDto;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaseCuotasRequestDto {

    @NotBlank
    private String numeroCuenta;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String codigoCanal;

    @NotEmpty
    @Valid
    private List<TransaccionDto> transacciones;
}
