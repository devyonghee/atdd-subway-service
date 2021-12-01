package nextstep.subway.station.domain;

import java.util.Objects;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import nextstep.subway.BaseEntity;
import nextstep.subway.common.domain.Name;
import org.springframework.util.Assert;

@Entity
public class Station extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Name name;

    protected Station() {
    }

    private Station(Name name) {
        Assert.notNull(name, "이름은 필수입니다.");
        this.name = name;
    }

    public static Station from(Name name) {
        return new Station(name);
    }

    public Long id() {
        return id;
    }

    public Name name() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Station station = (Station) o;
        return Objects.equals(name, station.name);
    }

    @Override
    public String toString() {
        return "Station{" +
            "id=" + id +
            ", name=" + name +
            '}';
    }
}
