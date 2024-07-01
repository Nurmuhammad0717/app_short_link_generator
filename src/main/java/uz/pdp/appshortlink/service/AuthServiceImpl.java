package uz.pdp.appshortlink.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.appshortlink.entity.Code;
import uz.pdp.appshortlink.entity.User;
import uz.pdp.appshortlink.enums.RoleTypeEnum;
import uz.pdp.appshortlink.exception.RestException;
import uz.pdp.appshortlink.payload.ApiResult;
import uz.pdp.appshortlink.payload.SignInDTO;
import uz.pdp.appshortlink.payload.SignUpDTO;
import uz.pdp.appshortlink.payload.TokenDTO;
import uz.pdp.appshortlink.repository.CodeRepository;
import uz.pdp.appshortlink.repository.RoleRepository;
import uz.pdp.appshortlink.repository.UserRepository;
import uz.pdp.appshortlink.security.JwtProvider;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final CodeRepository codeRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final AuthenticationProvider authProvider;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final Random random = new Random();


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> optionalUser = userRepository.findByEmail(username);
        if (optionalUser.isEmpty())
            throw new UsernameNotFoundException("User not found by username %s".formatted(username));

        return optionalUser.get();
    }

    @Override
    public ApiResult<TokenDTO> signIn(SignInDTO signInDTO) {

        try {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(signInDTO.getUsername(),signInDTO.getPassword());
            authProvider.authenticate(authentication);
            String token = jwtProvider.generateToken(signInDTO.getUsername());
            return ApiResult.success(new TokenDTO(token));
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    public ApiResult<String> signUp(SignUpDTO signUpDTO) {

        if (userRepository.existsByEmail(signUpDTO.getEmail()))
            throw RestException.alreadyExist("email");

        User user = new User(
                signUpDTO.getFirstName(),
                signUpDTO.getLastName(),
                signUpDTO.getEmail(),
                signUpDTO.getPhoneNumber(),
                passwordEncoder.encode(signUpDTO.getPassword()),
                roleRepository.findByType(RoleTypeEnum.USER).orElseThrow(() -> RestException.notFound("Role")),
                false
        );
        userRepository.save(user);

        Code code = new Code(
                String.valueOf(random.nextInt(10000,99999)),
                user.getId()
        );
        codeRepository.save(code);
        //https://short-url.uz/email-verification?codeId=&code=12345 -> http://localhost:80/api/v1/auth/email-verification?codeId=&code=12345
        //emailga verification link jo'natamiz uyga vazifa

        return ApiResult.success("Ok");
    }

    @Override
    @Transactional
    public ApiResult<String> emailVerification(UUID codeId, String code) {

        Code codeEntity = codeRepository.findById(codeId).orElseThrow(() -> RestException.notFound("Code"));

        if (!Objects.equals(code, codeEntity.getCode()))
            throw RestException.restThrow("Wrong code");

        User user = userRepository.findById(codeEntity.getUserId()).orElseThrow(() -> RestException.notFound("User"));
        user.setEnabled(true);
        userRepository.save(user);

        codeRepository.delete(codeEntity);

        return ApiResult.success("Ok");
    }
}
