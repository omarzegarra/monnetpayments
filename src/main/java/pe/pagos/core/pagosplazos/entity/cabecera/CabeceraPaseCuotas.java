package pe.pagos.core.pagosplazos.entity.cabecera;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pe.pagos.core.pagosplazos.entity.transaccion.TrxPaseCuotas;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "CABECERA_PASE_CUOTAS", schema = "EPAGO")
@Getter
@Setter
@NoArgsConstructor
public class CabeceraPaseCuotas {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cabecera_pase_cuotas")
    @SequenceGenerator(
            name = "seq_cabecera_pase_cuotas",
            sequenceName = "EPAGO.SEQ_CABECERA_PASE_CUOTAS",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "NUMERO_CUENTA", nullable = false, length = 50)
    private String numeroCuenta;

    @Column(name = "CANTIDAD_TRANSACCIONES", nullable = false)
    private Integer cantidadTransacciones;

    @Column(name = "CANTIDAD_TRANSACCIONES_PROCESADAS", nullable = false)
    private Integer cantidadTransaccionesProcesadas = 0;

    @Column(name = "FLAG_EMAIL_ENVIADO", nullable = false)
    private Integer flagEmailEnviado = 0;

    @Column(name = "EMAIL", nullable = false, length = 100)
    private String email;

    @Column(name = "CODIGO_CANAL", nullable = false, length = 50)
    private String codigoCanal;

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

    @OneToMany(mappedBy = "cabecera", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TrxPaseCuotas> transacciones;
}
