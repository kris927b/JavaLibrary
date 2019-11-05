package main.java.org.LibrarySystem.publisher;

import org.nypl.journalsystem.core.IPublisher;

public class Publisher implements IPublisher {
    String name;
    String location;

    public Publisher(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", location='" + getLocation() + "'" +
            "}";
    }

}