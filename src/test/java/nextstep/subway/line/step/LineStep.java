package nextstep.subway.line.step;

import nextstep.subway.common.domain.Fare;
import nextstep.subway.common.domain.Name;
import nextstep.subway.line.domain.Color;
import nextstep.subway.line.domain.Line;
import nextstep.subway.line.domain.Section;
import nextstep.subway.line.domain.Sections;

public final class LineStep {

    public static Line line(String name, String color, Section section) {
        return Line.of(Name.from(name), Color.from(color), Sections.from(section));
    }

    public static Line line(String name, String color, Section section, int extraFare) {
        return Line.of(Name.from(name),
            Color.from(color),
            Sections.from(section),
            Fare.from(extraFare));
    }
}
