package ro.andreimihalcea.food_app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import ro.andreimihalcea.food_app.enums.AppRole;

import javax.persistence.*;
import java.time.LocalDate;

import static org.springframework.format.annotation.DateTimeFormat.ISO.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private AppRole role;

    @DateTimeFormat(iso = DATE, fallbackPatterns = {  "dd.MM.yyyy" })
    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "address")
    private String address;

    @Column(name = "is_closed")
    private boolean isClosed;
}
