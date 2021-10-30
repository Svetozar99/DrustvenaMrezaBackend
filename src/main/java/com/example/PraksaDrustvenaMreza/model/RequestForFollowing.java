package com.example.PraksaDrustvenaMreza.model;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name="requests")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestForFollowing extends JpaEntity{

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "id", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "id", nullable = false)
    private User receiver;
}
