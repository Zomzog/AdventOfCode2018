package bzh.zomzog.adventofcode.day3;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SquareTest {


    @Test
    public void from(){
        Square square = Square.from("#1 @ 2,3: 4x5");

        assertThat(square.getId()).isEqualTo(1);
        assertThat(square.getX()).isEqualTo(2);
        assertThat(square.getY()).isEqualTo(3);
        assertThat(square.getHeight()).isEqualTo(4);
        assertThat(square.getWidth()).isEqualTo(5);
    }
}