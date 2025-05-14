package hongik.hospital.domain.hospital;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class Address {

    private String city;
    private String street;

    @Builder
    public Address(String city, String street) {
        this.city = city;
        this.street = street;
    }
}
