package pe.pagos.core.pagosplazos.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import pe.pagos.core.pagosplazos.domain.response.ResponseDto;
import pe.pagos.core.pagosplazos.domain.request.PaseCuotasRequestDto;
import pe.pagos.core.pagosplazos.entity.cabecera.CabeceraPaseCuotas;
import pe.pagos.core.pagosplazos.entity.transaccion.TrxPaseCuotas;
import pe.pagos.core.pagosplazos.exception.DatabaseException;
import pe.pagos.core.pagosplazos.mapper.PaseCuotasMapper;
import pe.pagos.core.pagosplazos.repository.CabeceraPaseCuotasRepository;
import pe.pagos.core.pagosplazos.repository.TrxPaseCuotasRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaseCuotasServiceImpl implements PaseCuotasService {

    @Value("${spring.datasource.username}")
    private String userName;

    private final CabeceraPaseCuotasRepository cabeceraRepo;
    private final TrxPaseCuotasRepository trxRepo;
    private final PaseCuotasMapper mapper;
    private final WebClient webClient; // Opcional, para notificaciones externas

    private static final Logger LOGGER = LoggerFactory.getLogger(PaseCuotasServiceImpl.class);

    @Override
    @Transactional
    public ResponseDto<Long> registrarPaseCuotas(PaseCuotasRequestDto requestDto) {
        try {
            // 1. Mapear y guardar la cabecera
            CabeceraPaseCuotas cabeceraOriginal = mapper.toEntity(requestDto,userName);
            CabeceraPaseCuotas cabeceraGuardada = cabeceraRepo.save(cabeceraOriginal);

            Long idCabecera = cabeceraGuardada.getId();
            LOGGER.debug("Cabecera creada con ID={}", idCabecera);

            // 2. Mapear y guardar todas las transacciones
            List<TrxPaseCuotas> listaTrx = mapper.toEntityList(requestDto.getTransacciones(),userName);
            listaTrx.forEach(trx -> trx.setCabecera(cabeceraGuardada));
            trxRepo.saveAll(listaTrx);
            LOGGER.debug("Se guardaron {} transacciones para la cabecera ID={}",
                    listaTrx.size(), idCabecera);

            // 3. Retornar ResponseDto.ok(idCabecera)
            return ResponseDto.ok(idCabecera);

        } catch (WebClientResponseException wcre) {
            LOGGER.error("Error al notificar servicio externo: {}", wcre.getMessage());
            throw new DatabaseException("Error al notificar servicio externo", wcre);
        } catch (Exception ex) {
            LOGGER.error("Error al guardar en BD: {}", ex.getMessage(), ex);
            throw new DatabaseException("Error al registrar pase de cuotas", ex);
        }
    }

}
