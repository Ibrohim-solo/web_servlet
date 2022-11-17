package servletProject.dto;

public class ResponseDto<NGA> {
    private Integer code;
    private String message;
    private Boolean success;
    private NGA data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public NGA getData() {
        return data;
    }

    public void setData(NGA data) {
        this.data = data;
    }

    public ResponseDto(Integer code, String message, Boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    public ResponseDto(Integer code, String message, Boolean success, NGA data) {
        this.code = code;
        this.message = message;
        this.success = success;
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" +
                " code: " + code + ",\n" +
                " message='" + message + ",\n" +
                " success=" + success + "\n" +
                "}";
    }
}
