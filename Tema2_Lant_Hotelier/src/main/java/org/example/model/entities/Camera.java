package org.example.model.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Camera implements Comparable<Camera>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String numarCamera;

    @NotNull
    private Double pret;

    @NotNull
    private Boolean esteDisponibila;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PozitieCamera pozitie;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<FacilitatiCamera> facilitati;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.DETACH)
    @JoinColumn(name = "locatie")
    private Hotel locatie;

    @OneToMany(mappedBy = "camera", fetch = FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rezervare> rezervari = new ArrayList<>();

    @Override
    public int compareTo(Camera o) {
        return this.locatie.compareTo(o.getLocatie());
    }
}
