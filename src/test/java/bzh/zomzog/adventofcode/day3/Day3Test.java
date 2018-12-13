package bzh.zomzog.adventofcode.day3;

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

public class Day3Test {

    private Day3 service;

    @Before
    public void setUp() {
        service = new Day3();
    }


    @Test
    public void part1Dumb() {
        List<String> values = Arrays.asList(
                "#1 @ 1,1: 2x4"
        );

        assertThat(service.part1(values)).isEqualTo(0);
    }

    @Test
    public void part1() {
        List<String> values = Arrays.asList(
                "#1 @ 1,3: 4x4",
                "#2 @ 3,1: 4x4",
                "#3 @ 5,5: 2x2"
        );

        assertThat(service.part1(values)).isEqualTo(4);
    }

    @Test
    public void part1OneBig() {
        List<String> values = Arrays.asList(
                "#1 @ 1,1: 10x10",
                "#2 @ 3,1: 4x4",
                "#3 @ 5,5: 2x2"
        );
        assertThat(service.part1(values)).isEqualTo(20);
    }

    @Test
    public void part142() {
        List<String> values = Arrays.asList(
                "#42 @ 978,896: 14x27",
                "#43 @ 978,896: 14x27"
        );
        assertThat(service.part1(values)).isEqualTo(14 * 27);
    }

    @Test
    public void part1FromReddit() {
        List<String> values = Arrays.asList(
                "#1 @ 3,2: 5x4",
                "#2 @ 3,2: 5x4",
                "#3 @ 3,2: 5x4"
        );
        assertThat(service.part1(values)).isEqualTo(20);
    }


    @Test
    public void part1SameOne() {
        List<String> values = Arrays.asList(
                "#1 @ 1,1: 1x1",
                "#2 @ 1,1: 1x1",
                "#3 @ 1,1: 1x1",
                "#4 @ 1,1: 1x1",
                "#5 @ 1,1: 1x1"
        );
        assertThat(service.part1(values)).isEqualTo(1);
    }

    @Test
    public void part1FourWithOne() {
        List<String> values = Arrays.asList(
                "#1 @ 1,1: 2x2",
                "#2 @ 2,1: 2x2",
                "#3 @ 1,2: 2x2",
                "#4 @ 2,2: 2x2"
        );
        assertThat(service.part1(values)).isEqualTo(5);
    }


    @Test
    public void printSolution() {
        System.out.println(service.part1(readDay3()));
    }

    private List<String> readDay3() {
        final URL resource = this.getClass().getResource("Day3");
        try (Stream<String> lines = Files.lines(Paths.get(resource.toURI()))) {
            return lines.collect(Collectors.toList());
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("YOLO");
        }
    }
}
