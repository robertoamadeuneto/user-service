package br.com.maxplorer.userservice.core.domain.user;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Data
@Accessors(fluent = true)
@Entity
@Table(name = "password")
@IdClass(Password.PasswordId.class)
public class Password implements Serializable {

    @EqualsAndHashCode.Exclude
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private Boolean active;

    public Password(String password, Boolean active) {
        this.password = password;
        this.active = active;
    }

    public static Password newActivePassword(String password) {
        return new Password(encryptPassword(password), true);
    }

    public static String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    static class PasswordId implements Serializable {
        private UUID user;
        private String password;
    }
}
