package pe.pagos.core.pagosplazos.service;

import pe.pagos.core.pagosplazos.domain.response.ResponseDto;
import pe.pagos.core.pagosplazos.domain.request.PaseCuotasRequestDto;

public interface PaseCuotasService {
    /**
     * Registra una cabecera y su lista de transacciones asociadas.
     *
     * @param requestDto DTO con datos de cabecera y lista de transacciones.
     * @return ResponseDto con el ID de la cabecera creada.
     */
    ResponseDto<Long> registrarPaseCuotas(PaseCuotasRequestDto requestDto);
}
