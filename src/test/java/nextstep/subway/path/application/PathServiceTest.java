package nextstep.subway.path.application;

import static nextstep.subway.line.step.LineStep.line;
import static nextstep.subway.line.step.SectionStep.section;
import static nextstep.subway.station.step.StationStep.station;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import nextstep.subway.line.application.LineService;
import nextstep.subway.line.domain.Line;
import nextstep.subway.line.domain.Lines;
import nextstep.subway.path.dto.PathRequest;
import nextstep.subway.path.dto.PathResponse;
import nextstep.subway.path.dto.PathStationResponse;
import nextstep.subway.station.application.StationService;
import nextstep.subway.station.domain.Station;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

@ExtendWith(MockitoExtension.class)
@DisplayName("경로 서비스")
class PathServiceTest {

    @Mock
    private LineService lineService;
    @Mock
    private StationService stationService;
    @InjectMocks
    private PathService pathService;

    @Test
    @DisplayName("최단 경로 탐색")
    void findShortestPath() {
        // given
        long sourceId = 1;
        long targetId = 2;
        검색된_노선들_제공(Collections.singletonList(신분당선()));
        검색된_지하철_역_제공(sourceId, station("강남역"));
        검색된_지하철_역_제공(targetId, station("양재역"));

        // when
        PathResponse pathResponse = pathService
            .findShortestPath(new PathRequest(sourceId, targetId));

        // then
        최단_경로_검색됨(pathResponse);
    }

    private Line 신분당선() {
        return line("신분당선", "red", section("강남역", "양재역", 10));
    }

    private void 최단_경로_검색됨(PathResponse pathResponse) {
        assertAll(
            () -> assertThat(pathResponse.getDistance()).isEqualTo(10),
            () -> assertThat(pathResponse.getStations())
                .extracting(PathStationResponse::getName)
                .containsExactly("강남역", "양재역")
        );
    }

    private OngoingStubbing<Station> 검색된_지하철_역_제공(long sourceId, Station 강남역) {
        return when(stationService.findById(sourceId))
            .thenReturn(강남역);
    }

    private void 검색된_노선들_제공(List<Line> lineList) {
        when(lineService.findAll())
            .thenReturn(Lines.from(lineList));
    }
}
