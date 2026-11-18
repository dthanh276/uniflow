public enum GradeBand {
    // must be in descending order 
    HD(85), D(75), C(65), P(50), F(0);

    private final int minMark;

    GradeBand(int minMark) {
        this.minMark = minMark;
    }

    public int getMinMark() {
        return minMark;
    }

    public static GradeBand fromMark(int mark){
        if (mark > 100) throw new IllegalArgumentException("mark must be <=100, was " + mark);
        for (GradeBand band: GradeBand.values()){
            if (mark >= band.getMinMark()){
                return band;
            }
        }
        throw new IllegalArgumentException("mark must be >= 0, was " + mark);
    }
}
