package pe.pagos.core.pagosplazos.entity.transaccion;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pe.pagos.core.pagosplazos.entity.cabecera.CabeceraPaseCuotas;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRX_PASE_CUOTAS", schema = "EPAGO")
@Getter
@Setter
@NoArgsConstructor
public class TrxPaseCuotas {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_trx_pase_cuotas")
    @SequenceGenerator(
            name = "seq_trx_pase_cuotas",
            sequenceName = "EPAGO.SEQ_TRX_PASE_CUOTAS",
            allocationSize = 1
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CABECERA", nullable = false)
    private CabeceraPaseCuotas cabecera;

    @Column(name = "CODIGO_TRANSACCION", nullable = false, length = 50)
    private String codigoTransaccion;

    @Column(name = "NUEVO_CODIGO_TRANSACCION", nullable = false, length = 50)
    private String nuevoCodigoTransaccion;

    @Column(name = "FECHA_TRANSACCION", nullable = false)
    private LocalDateTime fechaTransaccion;

    @Column(name = "MONTO", nullable = false, precision = 8, scale = 2)
    private BigDecimal monto;

    @Column(name = "CODIGO_ESTADO", nullable = false)
    private Integer codigoEstado;

    @Column(name = "ESTADO_DESCRIPCION", nullable = false, length = 100)
    private String estadoDescripcion;

    @Column(name = "CUOTAS", nullable = false)
    private Integer cuotas;

    @Column(name = "VALOR_CUOTA", nullable = false, precision = 8, scale = 2)
    private BigDecimal valorCuota;

    @Column(name = "TASA", nullable = false, precision = 8, scale = 2)
    private BigDecimal tasa;

    @Column(name = "DIFERIDO", nullable = false)
    private Integer diferido;

    @Column(name = "CODIGO_AUTORIZACION", nullable = false, length = 50)
    private String codigoAutorizacion;

    @CreationTimestamp
    @Column(name = "FECHA_REGISTRO", nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    @UpdateTimestamp
    @Column(name = "FECHA_ACTUALIZACION", nullable = false)
    private LocalDateTime fechaActualizacion;

    @Column(name = "USUARIO_REGISTRO", nullable = false, columnDefinition = "TEXT")
    private String usuarioRegistro;

    @Column(name = "USUARIO_ACTUALIZACION", nullable = false, columnDefinition = "TEXT")
    private String usuarioActualizacion;
}
