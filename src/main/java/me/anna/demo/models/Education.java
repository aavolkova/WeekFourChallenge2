package me.anna.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Education {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min=1, max=50)
    private String degreeTitle;

    @NotNull
    @Size(min=1, max=50)
    private String educationalInstitution;

//    LocalDate today = LocalDate.now();
//    final int now = today.getYear();

    @NotNull @Min(1950) @Max(2023)
    private Integer graduateDate;

//    @NotNull
//    @DateTimeFormat(pattern = "yyyy/MM/dd")
//    private LocalDate graduateDate;


    // setters and getters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDegreeTitle() {
        return degreeTitle;
    }

    public void setDegreeTitle(String degreeTitle) {
        this.degreeTitle = degreeTitle;
    }

    public String getEducationalInstitution() {
        return educationalInstitution;
    }

    public void setEducationalInstitution(String educationalInstitution) {
        this.educationalInstitution = educationalInstitution;
    }

    public Integer getGraduateDate() {
        return graduateDate;
    }

    public void setGraduateDate(Integer graduateDate) {
        this.graduateDate = graduateDate;
    }






//    public String getGraduateDate() {
//        return graduateDate;
//    }
//    public void setGraduateDate(String graduateDate) {
//        this.graduateDate = graduateDate;
//    }




//    public LocalDate getGraduateDate() {
//        return graduateDate;
//    }
//    public void setGraduateDate(LocalDate graduateDate) {
//        this.graduateDate = graduateDate;
//    }




//    public Date getGraduateDate() {
//        return graduateDate;
//    }
//    public void setGraduateDate(Date graduateDate) {
//        this.graduateDate = graduateDate;
//    }


}
