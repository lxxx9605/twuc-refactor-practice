package com.twu.refactoring;

public class Direction {
    private final char[] directionList = {'N', 'E', 'S', 'W'};
    private int directionIndex;

    public Direction(char direction) {
        for (int i = 0; i < directionList.length; i++) {
            if (directionList[i] == direction) {
                directionIndex = i;
                break;
            }
        }
    }

    public Direction turnRight() {
        directionIndex = (directionIndex + 1) % 4;
        return new Direction(directionList[directionIndex]);
    }

    public Direction turnLeft() {
        directionIndex = (directionIndex + 3) % 4;
        return new Direction(directionList[directionIndex]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Direction direction1 = (Direction) o;

        if (directionIndex != direction1.directionIndex) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) directionIndex;
    }

    @Override
    public String toString() {
        return "Direction{direction=" + directionList[directionIndex] + '}';
    }
}
