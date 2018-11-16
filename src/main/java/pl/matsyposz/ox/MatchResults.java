package pl.matsyposz.ox;

public enum MatchResults {

    WIN (3),
    DRAW (1),
    LOSE (0);

    private final int value;

    MatchResults(int value) {
        this.value = value;
    }
}
