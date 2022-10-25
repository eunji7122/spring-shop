package portfolio.shop.domain.auth;

import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import portfolio.shop.domain.member.Member;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Getter
@ToString
public class OAuth2UserDetails implements UserDetails, OAuth2User {

    private Member member;
    private OAuth2UserInfo oAuth2UserInfo;

    // Form 로그인 시 사용
    public OAuth2UserDetails(Member member) {
        this.member = member;
    }

    // OAuth2 로그인 시 사용
    public OAuth2UserDetails(Member member, OAuth2UserInfo oAuth2UserInfo) {
        this.member = member;
        this.oAuth2UserInfo = oAuth2UserInfo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add((GrantedAuthority) () -> "ROLE_" + member.getRole().toString());
//        collection.add(new SimpleGrantedAuthority("ROLE_" + member.getRole().toString()));
        return collection;
    }

    public String test() {
        return member.getRole().toString();
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
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
        return true;
    }


    @Override
    public String getName() {
        return oAuth2UserInfo.getProviderId();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2UserInfo.getAttributes();
    }
}
