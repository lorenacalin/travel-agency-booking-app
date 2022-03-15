package model;

import model.enums.Status;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vacation_packages")
public class VacationPackage {
    @Id
    @GeneratedValue
    @Column(name = "package_id", unique = true, nullable = false)
    private Integer packageId;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(name = "details", length = 45)
    private String details;

    @Column(name="nr_of_available_seats")
    private Integer nrOfAvailableSeats;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private VacationDestination destination;

    @ManyToMany(mappedBy = "vacationPackages")
    private Set<User> users;

    public VacationPackage(String name, Double price, Date startDate, Date endDate, String details, Integer nrOfAvailableSeats, Status status, VacationDestination destination) {
        this.packageId = null;
        this.name = name;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.details = details;
        this.nrOfAvailableSeats = nrOfAvailableSeats;
        this.status = status;
        this.destination = destination;
        this.users = new HashSet<>();
    }

    public VacationPackage() {

    }

    public Integer getPackageId() {
        return packageId;
    }

    public String getName() {
        return name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getDetails() {
        return details;
    }

    public Status getStatus() {
        return status;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getNrOfAvailableSeats() {
        return nrOfAvailableSeats;
    }

    public VacationDestination getDestination() {
        return destination;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setNrOfAvailableSeats(Integer nrOfAvailableSeats) {
        this.nrOfAvailableSeats = nrOfAvailableSeats;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return  Integer.toString(this.packageId) + ". " + name + "\n"
                + '\t' + "Price: " + price + "$" + "\n"
                + '\t' + "Period: " + startDate + " - " + endDate + "\n"
                + '\t' + "Available seats: " + nrOfAvailableSeats + "\n"
                + '\t' + "Destination: " + getDestination().getName() + "\n"
                + '\t' + "Status: " + getStatus().toString() + "\n"
                + '\t' + "More details: " + details + "\n\n";
    }
}
