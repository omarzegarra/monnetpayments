package pe.pagos.core.pagosplazos.domain.response;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseDto<T> extends BaseResponse {
    private T data;

    public static <T> ResponseDto<T> ok(T data) {
        ResponseDto<T> response = new ResponseDto<>();
        response.setCodigo(0);
        response.setMensaje("OK");
        response.setData(data);
        return response;
    }

    public static <T> ResponseDto<T> error(Integer codigo, String mensaje) {
        ResponseDto<T> response = new ResponseDto<>();
        response.setCodigo(codigo);
        response.setMensaje(mensaje);
        response.setData(null);
        return response;
    }
}
