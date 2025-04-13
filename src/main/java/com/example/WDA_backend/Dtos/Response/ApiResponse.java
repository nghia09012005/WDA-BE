package com.example.WDA_backend.Dtos.Response;

public class ApiResponse<T> {
    private String code ;
    private T result;

    public ApiResponse(String code, T result) {
        this.code = code;
        this.result = result;
    }

    public ApiResponse(String code) {
        this.code = code;
        this.result = null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
