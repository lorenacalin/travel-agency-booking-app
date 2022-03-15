package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vacation_destinations")
public class VacationDestination {
    @Id
    @GeneratedValue
    @Column(name = "destination_id", unique = true, nullable = false)
    private Integer destinationId;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    private List<VacationPackage> packages;

    public VacationDestination() {

    }

    public VacationDestination(String name) {
        this.destinationId = null;
        this.name = name;
        this.packages = new ArrayList<>();
    }

    public Integer getDestinationId() {
        return destinationId;
    }

    public String getName() {
        return name;
    }

    public List<VacationPackage> getPackages() {
        return packages;
    }

    @Override
    public String toString() {
        return  destinationId + ". " + name + '\n' +
                "INCLUDED PACKAGES: " + "\n" + packages + "\n\n";
    }
}
