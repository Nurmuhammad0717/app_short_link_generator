package uz.pdp.appshortlink.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult<T> {

    //muvaffaqiyatlimi
    private boolean success;

    private T data;//List<STring> data: ["Salom"], {}

    private String errorMsg;

    private List<FieldErrorDTO> fieldErrors;

    public static<T> ApiResult<T> success(T data){
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setSuccess(true);
        apiResult.setData(data);
        return apiResult;
    }

    public static<T> ApiResult<T> error(String errorMsg){
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setSuccess(false);
        apiResult.setErrorMsg(errorMsg);
        return apiResult;
    }

    public static<T> ApiResult<T> error(List<FieldErrorDTO> fieldErrors){
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setSuccess(false);
        apiResult.setFieldErrors(fieldErrors);
        return apiResult;
    }


}
