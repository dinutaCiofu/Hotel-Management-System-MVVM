package org.example.model.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "hotel")
@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hotel  implements Comparable<Hotel>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String nume;

    @NotNull
    private String adresa;

    @NotNull
    private Integer nrCamere;

    @OneToMany(mappedBy = "locatie", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Camera> camere;

    @Override
    public int compareTo(Hotel o) {
        return this.nume.compareTo(o.getNume());
    }
}
