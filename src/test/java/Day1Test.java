import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Day1Test {

    private Day1 service;

    @Before
    public void setUp() {
        service = new Day1();
    }

    @Test
    public void part1() {
        assertThat(service.part1(Arrays.asList(+1, +1, +1))).isEqualTo(3);
        assertThat(service.part1(Arrays.asList(+1, +1, -2))).isEqualTo(0);
        assertThat(service.part1(Arrays.asList(-1, -2, -3))).isEqualTo(-6);
        System.out.println(service.part1(readDay1Part(1)));
    }

    @Test
    public void part2() {
        assertThat(service.part2(Arrays.asList(1, -1))).isEqualTo(0);
        assertThat(service.part2(Arrays.asList(3, +3, +4, -2, -4))).isEqualTo(10);
        assertThat(service.part2(Arrays.asList(-6, +3, +8, +5, -6))).isEqualTo(5);
        assertThat(service.part2(Arrays.asList(+7, +7, -2, -7, -4))).isEqualTo(14);
        System.out.println(service.part2(readDay1Part(2)));
    }

    private List<Integer> readDay1Part(int i) {
        final URL resource = this.getClass().getResource("Day1Part" + i);
        try (Stream<String> lines = Files.lines(Paths.get(resource.toURI()))) {
            return lines.map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("YOLO");
        }
    }

}
