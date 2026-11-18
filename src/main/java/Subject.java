public record Subject(String code, String name) {
    public Subject{
        if (code == null) throw new IllegalArgumentException("code is null");
        if (code.isBlank()) throw new IllegalArgumentException("code is blank");
    }
}
