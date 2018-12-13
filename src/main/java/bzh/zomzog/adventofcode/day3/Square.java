package bzh.zomzog.adventofcode.day3;

import lombok.Value;

@Value
public class Square {
    private final int id;
    private final int x;
    private final int y;
    private final int height;
    private final int width;

    public static Square from(final String value) {
        final String[] s = value.split(" ");

        final int id = Integer.parseInt(s[0].substring(1));

        String[] position = s[2].split(",");
        final int x = Integer.parseInt(position[0]);
        final int y = Integer.parseInt(position[1].replace(":",""));


        String[] size = s[3].split("x");
        final int height =  Integer.parseInt(size[0]);
        final int width=  Integer.parseInt(size[1]);

        return new Square(id, x, y, height, width);
    }
}
