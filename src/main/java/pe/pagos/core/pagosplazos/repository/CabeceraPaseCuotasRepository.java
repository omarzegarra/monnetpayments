package pe.pagos.core.pagosplazos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.pagos.core.pagosplazos.entity.cabecera.CabeceraPaseCuotas;

@Repository
public interface CabeceraPaseCuotasRepository extends JpaRepository<CabeceraPaseCuotas, Long> {
}
