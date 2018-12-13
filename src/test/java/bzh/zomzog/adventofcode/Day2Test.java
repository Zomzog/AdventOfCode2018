package bzh.zomzog.adventofcode;

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

public class Day2Test {

    private Day2 service;

    @Before
    public void setUp() {
        service = new Day2();
    }

    @Test
    public void part1() {
        assertThat(service.part1(Arrays.asList("abcdef", "bababc", "abbcde", "abcccd","aabcdd", "abcdee", "ababab"))).isEqualTo(12);
        System.out.println(service.part1(readDay2Part(1)));
    }

    private List<String> readDay2Part(int i) {
        final URL resource = this.getClass().getResource("Day2Part" + i);
        try (Stream<String> lines = Files.lines(Paths.get(resource.toURI()))) {
            return lines.collect(Collectors.toList());
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("YOLO");
        }
    }

}
