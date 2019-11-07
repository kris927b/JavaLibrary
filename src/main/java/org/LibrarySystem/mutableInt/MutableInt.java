package main.java.org.LibrarySystem.mutableInt;

public class MutableInt {
    private int value = 1;
    
    public void increment() {
        ++value;
    }

    public int getValue() {
        return value;
    }

	public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" + getValue() + "}";
    }

} 