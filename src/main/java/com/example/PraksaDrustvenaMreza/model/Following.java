package com.example.PraksaDrustvenaMreza.model;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name="following")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Following extends JpaEntity{

    @ManyToOne
    @JoinColumn(name = "followee_id", referencedColumnName = "id", nullable = false)
    private User followee;

    @ManyToOne
    @JoinColumn(name = "follower_id", referencedColumnName = "id", nullable = false)
    private User follower;
}
