package portfolio.shop.domain.member;

import lombok.Builder;
import lombok.Data;
import portfolio.shop.domain.Role;

import javax.persistence.*;

@Data
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    private String username;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String provider;   // oauth2를 이용할 경우 어떤 플랫폼을 이용하는지

    private String providerId; // oauth2를 이용할 경우 아이디값

    public Member() {
    }

    public Member(String email, String password, String username, String address, Role role) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.address = address;
        this.role = role;
    }

    @Builder(builderClassName = "OAuth2Register", builderMethodName = "oauth2Register")
    public Member(String email, String password, String username, String address, Role role, String provider, String providerId) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.address = address;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
    }
}
