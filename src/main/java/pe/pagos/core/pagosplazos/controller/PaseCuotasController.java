package pe.pagos.core.pagosplazos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.pagos.core.pagosplazos.domain.response.ResponseDto;
import pe.pagos.core.pagosplazos.domain.request.PaseCuotasRequestDto;
import pe.pagos.core.pagosplazos.service.PaseCuotasService;

@RestController
@RequestMapping("/v1/pase-cuotas")
@RequiredArgsConstructor
@Slf4j
public class PaseCuotasController {

    private final PaseCuotasService paseCuotasService;

    @PostMapping
    public ResponseEntity<ResponseDto<Long>> registrarPaseCuotas(
            @RequestHeader("correlation-id") String correlationId,
            @Valid @RequestBody PaseCuotasRequestDto requestDto) {

        log.info("Iniciando registro de pase de cuotas. correlation-id={}", correlationId);
        ResponseDto<Long> response = paseCuotasService.registrarPaseCuotas(requestDto);
        log.info("Finalizado registro. correlation-id={}, idCabecera={}", correlationId, response.getData());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
