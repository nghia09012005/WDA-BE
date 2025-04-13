package com.example.WDA_backend.Controller;


import com.example.WDA_backend.Dtos.Request.SigninRequest;
import com.example.WDA_backend.Dtos.Request.SignupRequest;
import com.example.WDA_backend.Dtos.Response.ApiResponse;
import com.example.WDA_backend.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
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

    @PostMapping("/signin")
    ApiResponse Signin(@RequestBody SigninRequest request){
        if(service.Signin(request)){
            return new ApiResponse("1000");
        }
        return new ApiResponse("1001");
    }




}
