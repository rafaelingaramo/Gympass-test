package br.com.ingaramo.gympass.pilot;

import java.util.Objects;

public class Pilot {
    private final static String SPLIT_PATTERN = "–";
    private Integer code;
    private String name;
    private String composedCode;

    public Pilot(final String composedCode) {
        final String[] split = composedCode.split(SPLIT_PATTERN);
        this.code = Integer.valueOf(split[0].trim());
        this.name = split[1].trim();
        this.composedCode = composedCode;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComposedCode() {
        return composedCode;
    }

    public void setComposedCode(String composedCode) {
        this.composedCode = composedCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pilot pilot = (Pilot) o;
        return code.equals(pilot.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "Pilot{" +
            "code=" + code +
            ", name='" + name + '\'' +
            '}';
    }
}
