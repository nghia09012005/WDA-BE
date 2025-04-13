package com.example.WDA_backend.Controller;


import com.example.WDA_backend.Dtos.Request.SigninRequest;
import com.example.WDA_backend.Dtos.Request.SignupRequest;
import com.example.WDA_backend.Dtos.Response.ApiResponse;
import com.example.WDA_backend.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class AuthenticationController {
    @Autowired
    private AuthenticationService service;

    @PostMapping("/signup")
    ApiResponse Signup(@RequestBody SignupRequest request){
        if(service.Signup(request)){
            return new ApiResponse("1000");
        }
        return new ApiResponse("1001");
    }

    @GetMapping("/signin")
    ApiResponse Signup(SigninRequest request){
        if(service.Signin(request)){
            return new ApiResponse("1000");
        }
        return new ApiResponse("1001");
    }




}
