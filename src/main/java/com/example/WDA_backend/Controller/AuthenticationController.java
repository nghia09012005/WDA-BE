package com.example.WDA_backend.Controller;


import com.example.WDA_backend.Dtos.Request.SigninRequest;
import com.example.WDA_backend.Dtos.Request.SignupRequest;
import com.example.WDA_backend.Dtos.Response.ApiResponse;
import com.example.WDA_backend.Dtos.Response.SigninResponse;
import com.example.WDA_backend.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AuthenticationController {
    @Autowired
    private AuthenticationService service;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<String>> Signup(@RequestBody SignupRequest request) {
        // Kiểm tra nếu đăng ký thành công
        if (service.Signup(request)) {
            // Trả về status 201 (Created) khi user được tạo thành công
            return new ResponseEntity<>(new ApiResponse<>("1000", "User created successfully"), HttpStatus.CREATED);
        }
        // Trả về status 400 (Bad Request) nếu username đã tồn tại
        return new ResponseEntity<>(new ApiResponse<>("1001", "Username has already been existed"), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResponse<SigninResponse>> Signin(@RequestBody SigninRequest request) {
        // Kiểm tra nếu đăng nhập thành công
        return service.Signin(request)
                .map(result -> new ResponseEntity<>(new ApiResponse<>("1000", result), HttpStatus.OK)) // Trả về token với status 200 (OK)
                .orElseGet(() -> new ResponseEntity<>(new ApiResponse<>("1002", new SigninResponse()), HttpStatus.UNAUTHORIZED)); // Trả về status 401 (Unauthorized) nếu sai credentials
    }
}
