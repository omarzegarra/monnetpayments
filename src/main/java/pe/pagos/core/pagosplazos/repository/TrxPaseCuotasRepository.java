package pe.pagos.core.pagosplazos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.pagos.core.pagosplazos.entity.transaccion.TrxPaseCuotas;

@Repository
public interface TrxPaseCuotasRepository extends JpaRepository<TrxPaseCuotas, Long> {
}
