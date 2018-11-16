package pl.matsyposz.ox.utils;

public enum MatchResults {

    WIN (3),
    DRAW (1);

    private final int value;

    MatchResults(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
