package uz.pdp.appshortlink.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.appshortlink.entity.templates.AbsUUIDEntity;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "users")
@SQLRestriction("deleted=false")
@SQLDelete(sql = "update users set deleted=true where id=?")
public class User extends AbsUUIDEntity implements UserDetails {

    private String firstName;

    private String lastName;

    //unikal -> faqatgina deleted=false bo'lganlar unikal bo'lsin
    private String email;

    private String phoneNumber;

    private String password;

    @ManyToOne
    private Role role;

    private boolean enabled;



    @Cacheable(cacheNames = "userDetails",cacheManager = "defaultCacheManager", key = "#root.methodName")
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getPermissions()
                .stream()
                .map(permissionEnum -> new SimpleGrantedAuthority(permissionEnum.name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
