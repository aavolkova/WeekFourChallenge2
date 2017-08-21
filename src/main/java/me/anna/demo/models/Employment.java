package me.anna.demo.models;

import com.sun.istack.internal.Nullable;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Work Experience
 */

@Entity
public class Employment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min=1, max=50)
    private String positionTitle;

    @NotNull
    @Size(min=1, max=50)
    private String organisation;

    // Start date (employment with the organisation)
    @NotNull
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate startDate;

    // End date (employment with the organisation)

    @Nullable
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate endDate;

    // If person has just one duty for his position:
    @NotNull
    @Size(min=1, max=50)
    private String duty;




    // In case if person has more than one duty for his position:
//    private ArrayList<String> duties;





    // setters and getters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPositionTitle() {
        return positionTitle;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    // For processing single duty per job position:
    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    // For processing multiple duties:
//    public ArrayList<String> getDuties() {
//        return duties;
//    }
//
//    public void setDuties(ArrayList<String> duties) {
//        this.duties = duties;
//    }


}
