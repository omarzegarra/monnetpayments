package pe.pagos.core.pagosplazos.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransaccionDto {

    @NotBlank
    private String codigoTransaccion;

    @NotBlank
    private String nuevoCodigoTransaccion;

    @NotNull
    private LocalDateTime fechaTransaccion;

    @NotNull
    @DecimalMin("0.0")
    private Double monto;

    @NotNull
    private Integer codigoEstado;

    @NotBlank
    private String estadoDescripcion;

    @NotNull
    @Min(1)
    private Integer cuotas;

    @NotNull
    @DecimalMin("0.0")
    private Double valorCuota;

    @NotNull
    @DecimalMin("0.0")
    private Double tasa;

    @NotNull
    private Integer diferido;

    @NotBlank
    private String codigoServicio;

    @NotBlank
    private String codigoAutorizacion;
}
