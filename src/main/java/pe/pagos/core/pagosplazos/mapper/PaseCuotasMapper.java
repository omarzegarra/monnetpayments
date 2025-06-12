package pe.pagos.core.pagosplazos.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pe.pagos.core.pagosplazos.domain.request.PaseCuotasRequestDto;
import pe.pagos.core.pagosplazos.dto.TransaccionDto;
import pe.pagos.core.pagosplazos.entity.cabecera.CabeceraPaseCuotas;
import pe.pagos.core.pagosplazos.entity.transaccion.TrxPaseCuotas;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PaseCuotasMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cantidadTransaccionesProcesadas", constant = "0")
    @Mapping(target = "flagEmailEnviado", constant = "0")
    @Mapping(target = "cantidadTransacciones", expression = "java(requestDto.getTransacciones().size())")
    @Mapping(target = "transacciones", ignore = true)
    @Mapping(target = "usuarioRegistro", expression = "java(userName)")
    @Mapping(target = "usuarioActualizacion", expression = "java(userName)")
    CabeceraPaseCuotas toEntity(PaseCuotasRequestDto requestDto, @Context String userName);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cabecera", ignore = true)
    @Mapping(target = "usuarioRegistro", expression = "java(userName)")
    @Mapping(target = "usuarioActualizacion", expression = "java(userName)")
    @Mapping(target = "fechaRegistro", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    // Aquí indicamos el método de conversión para BigDecimal
    @Mapping(source = "monto", target = "monto", qualifiedByName = "toBigDecimal10")
    @Mapping(source = "valorCuota", target = "valorCuota", qualifiedByName = "toBigDecimal10")
    @Mapping(source = "tasa", target = "tasa", qualifiedByName = "toBigDecimal10")
    TrxPaseCuotas toEntity(TransaccionDto dto, @Context String userName);

    List<TrxPaseCuotas> toEntityList(List<TransaccionDto> dtos, @Context String userName);

    // Retorna el usuario actual; en producción, leer del SecurityContext
    default String getUsuarioActual() {
        return "usuarioSistema";
    }

    // Método auxiliar para MapStruct
    @Named("toBigDecimal10")
    static BigDecimal toBigDecimal10(Double value) {
        if (value == null) {
            return null;
        }
        // Creamos BigDecimal con escala 2 para NUMERIC(8,2)
        return BigDecimal.valueOf(value)
                .setScale(2, RoundingMode.HALF_UP);
    }
}
