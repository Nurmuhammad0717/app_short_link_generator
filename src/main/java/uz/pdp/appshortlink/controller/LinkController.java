package uz.pdp.appshortlink.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appshortlink.entity.User;
import uz.pdp.appshortlink.utils.AppConstants;

@RestController
@RequestMapping(AppConstants.BASE_PATH_V1 + "/link")
public class LinkController {

    @PreAuthorize("hasAuthority(T(uz.pdp.appshortlink.enums.PermissionEnum).CREATE_URL.name())")
    @GetMapping("/test")
    public String test() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(currentUser);
        return "Bu test edi";
    }

}
