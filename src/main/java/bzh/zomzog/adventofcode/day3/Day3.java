package bzh.zomzog.adventofcode.day3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Day3 {

    public int part1(List<String> values) {
        List<Square> squares = values.stream().map(Square::from).collect(Collectors.toList());

        final List<List<Integer>> pattron = buildPattron(squares);
        //debugPrint(pattron);

        for (Square square : squares) {
            for (int x = square.getX(); x < square.getX() + square.getWidth(); x++) {
                List<Integer> column = pattron.get(x);
                for (int y = square.getY(); y < square.getY() + square.getHeight(); y++) {
                    column.set(y, column.get(y) + 1);
                }
            }
        }
        if (values.size() < 100)
            debugPrint(pattron);

        return (int) pattron.stream() //
                .flatMap(List::stream) //
                .filter(i -> i > 1) //
                .count();
    }

    private void debugPrint(List<List<Integer>> pattron) {
        System.out.println("================ NEW PRINT ================");
        for (List<Integer> row : pattron) {
            StringBuilder sb = new StringBuilder();
            for (Integer col : row) {
                sb.append(col);
            }
            System.out.println(sb);
        }
    }

    private List<List<Integer>> buildPattron(List<Square> squares) {
        int width = 0;
        int height = 0;
        for (Square square : squares) {
            final int squareWidth = square.getX() + square.getWidth();
            final int squareHeight = square.getY() + square.getHeight();
            if (width < squareWidth) {
                width = squareWidth;
            }
            if (height < squareHeight) {
                height = squareHeight;
            }
        }

        return buildPattron(width, height);
    }


    private List<List<Integer>> buildPattron(int width, int height) {
        List<List<Integer>> pattron = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            List<Integer> column = new ArrayList<>();
            for (int y = 0; y < height; y++) {
                column.add(0);
            }
            pattron.add(column);
        }
        return pattron;
    }

}
