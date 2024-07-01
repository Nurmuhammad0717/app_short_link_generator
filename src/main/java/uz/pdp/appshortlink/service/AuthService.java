package uz.pdp.appshortlink.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import uz.pdp.appshortlink.payload.ApiResult;
import uz.pdp.appshortlink.payload.SignInDTO;
import uz.pdp.appshortlink.payload.SignUpDTO;
import uz.pdp.appshortlink.payload.TokenDTO;

import java.util.UUID;

public interface AuthService extends UserDetailsService {
    ApiResult<TokenDTO> signIn(SignInDTO signInDTO);

    ApiResult<String> signUp(SignUpDTO signUpDTO);

    ApiResult<String> emailVerification(UUID codeId, String code);
}
