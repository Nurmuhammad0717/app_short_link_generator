package uz.pdp.appshortlink.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appshortlink.payload.ApiResult;
import uz.pdp.appshortlink.payload.SignInDTO;
import uz.pdp.appshortlink.payload.SignUpDTO;
import uz.pdp.appshortlink.payload.TokenDTO;
import uz.pdp.appshortlink.service.AuthService;
import uz.pdp.appshortlink.utils.AppConstants;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(AppConstants.BASE_PATH_V1 + "/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
//    private final Logger log = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/sign-in")
    public ApiResult<TokenDTO> signIn(@RequestBody @Valid SignInDTO signInDTO) {
        log.info("Request -> AuthController -> signIn -> params -> {}", signInDTO);
        ApiResult<TokenDTO> apiResult = authService.signIn(signInDTO);
        log.info("Respons -> AuthController -> signIn -> params -> {}", apiResult.getData());
        return apiResult;
    }

    @PostMapping("/sign-up")
    public ApiResult<String> signUp(@RequestBody @Valid SignUpDTO signUpDTO){
        ApiResult<String> apiResult = authService.signUp(signUpDTO);
        return apiResult;
    }

    @GetMapping("/email-verification")
    public ApiResult<String> emailVerification(@RequestParam UUID codeId, @RequestParam String code){
        return authService.emailVerification(codeId,code);
    }

}
