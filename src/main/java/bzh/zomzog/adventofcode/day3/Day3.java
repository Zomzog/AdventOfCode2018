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

        final List<List<List<Integer>>> pattron = buildPattron(squares);
        //debugPrint(pattron);
        int count = 0;

        for (Square square: squares) {
            for (int x = square.getX(); x < square.getX() + square.getWidth(); x++){
                List<List<Integer>> column = pattron.get(x);
                for (int y = square.getY(); y < square.getY() + square.getHeight(); y++) {
                    if (isFirstConflict(column, y)) {
                        count++;
                    }
                    column.get(y).add(square.getId());
                }
            }
        }
        debugPrint(pattron);
        return count;
    }

    private void debugPrint(List<List<List<Integer>>> pattron) {
        System.out.println("================ NEW PRINT ================");
        for(List<List<Integer>> row : pattron) {
            StringBuilder sb = new StringBuilder();
            for (List<Integer> col : row) {
                if (col.isEmpty()) {
                    sb.append("  .  ");
                } else                 if (col.size() > 1) {
                    sb.append("  x  ");
                } else {
                    int value = col.get(0);
                    if (value < 10) {
                        sb.append("  ");
                        sb.append(col.get(0));
                        sb.append("  ");
                    } else if (value < 100) {
                        sb.append("  ");
                        sb.append(col.get(0));
                        sb.append(" ");

                    }else if (value < 1000) {
                        sb.append(" ");
                        sb.append(col.get(0));
                        sb.append(" ");
                    }else if (value < 10000) {
                        sb.append(" ");
                        sb.append(col.get(0));
                    } else {
                        throw new RuntimeException("YOLO need more");
                    }
                }
            }
            System.out.println(sb);
        }
    }

    private boolean isFirstConflict(List<List<Integer>> column, int y) {
        return column.get(y).size() == 1;
    }

    private List<List<List<Integer>>> buildPattron(List<Square> squares) {
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


    public int countPossibleFabric(List<String> values) {

        List<Square> squares = values.stream().map(Square::from).collect(Collectors.toList());

        final List<List<List<Integer>>> pattron = buildPattron(squares);

        Map<Integer, Set<Integer>> conflicts = listConflicts(squares, pattron);

        int count = countNoConflict(conflicts);

        while (!conflicts.isEmpty()) {
            int lowConflict = searchLowerConflict(conflicts);
            removeLowConfilctAndAllWithConflict(conflicts, lowConflict);
            count++;
        }

        return count;
    }


    private Map<Integer, Set<Integer>> listConflicts(List<Square> squares, List<List<List<Integer>>> pattron) {
        Map<Integer, Set<Integer>> conflicts = new HashMap<>();

        for (Square square: squares) {
            final HashSet<Integer> squareConflict = new HashSet<>();
            conflicts.put(square.getId(), squareConflict);
            for (int x = square.getX(); x < square.getX() + square.getWidth(); x++){
                List<List<Integer>> column = pattron.get(x);
                for (int y = square.getY(); y < square.getY() + square.getHeight(); y++) {
                    if (!column.get(y).isEmpty()) {
                        for (Integer i : column.get(y)) {
                            conflicts.get(i).add(square.getId());
                            squareConflict.add(i);
                        }
                    }
                    column.get(y).add(square.getId());
                }
            }
        }
        return conflicts;
    }

    private void removeLowConfilctAndAllWithConflict(Map<Integer, Set<Integer>> conflicts, int lowConflict) {
        conflicts.remove(lowConflict);
        for (Integer i: new HashSet<>(conflicts.keySet())) {
            if (conflicts.get(i).contains(lowConflict)){
                conflicts.remove(i);
            }
        }
    }

    private int countNoConflict(Map<Integer, Set<Integer>> conflicts) {
        int count = 0;

        for (Integer i: new HashSet<>(conflicts.keySet())) {
            if (conflicts.get(i).isEmpty()){
                count++;
                conflicts.remove(i);
            }
        }
        return count;
    }

    private int searchLowerConflict(Map<Integer, Set<Integer>> conflicts) {
        int lowConflict = 0;
        int conflictCount = Integer.MAX_VALUE;
        for (Integer i: conflicts.keySet()) {
            final int challenger = conflicts.get(i).size();
            if (challenger < conflictCount){
                conflictCount = challenger;
                lowConflict = i;
            }
        }
        return lowConflict;
    }


    private List<List<List<Integer>>> buildPattron(int width, int height) {
        List<List<List<Integer>>> pattron = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            List<List<Integer>> column = new ArrayList<>();
            for (int y = 0; y < height; y++) {
                column.add(new ArrayList<>());
            }
            pattron.add(column);
        }
        return pattron;
    }

}
