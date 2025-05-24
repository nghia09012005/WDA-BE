package com.example.WDA_backend.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/walletkey")
public class PrivatePairController {

    @GetMapping
    public List<Integer> WalletKey() {
        return Arrays.asList(204,181,248,194,159,198,170,179,204,190,36,242,15,187,199,13,210,91,156,253,178,163,1,78,99,218,8,125,144,163,33,208,207,28,120,95,23,118,176,228,163,187,137,49,234,147,126,230,104,56,31,158,43,10,65,15,136,224,85,95,109,146,134,66);
    }
}
