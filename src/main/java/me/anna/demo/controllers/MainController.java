package me.anna.demo.controllers;

import me.anna.demo.EducationRepository;
import me.anna.demo.EmploymentRepository;
import me.anna.demo.PersonRepository;
import me.anna.demo.SkillsRepository;
import me.anna.demo.models.Education;
import me.anna.demo.models.Employment;
import me.anna.demo.models.Person;
import me.anna.demo.models.Skills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    EducationRepository educationRepository;

    @Autowired
    EmploymentRepository employmentRepository;

    @Autowired
    SkillsRepository skillsRepository;



    // Display the home page
    @GetMapping("/")
    public String showIndex(Model model)
    {
        String myMessage = "Welcome to the Robo Resume Application";
        model.addAttribute("message", myMessage);
        return "index";
    }

    // Personal Info:

    // Allow user to enter Person's information
    @GetMapping("/enterPerson")
    public String addPerson(Model model)
    {
        model.addAttribute("newPerson", new Person());
        return "enterPerson";
    }

    // Validate entered information and if it is valid display the result
    @PostMapping("/enterPerson")
    public String postPerson(@Valid @ModelAttribute("newPerson") Person person, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            return "enterPerson";
        }

        personRepository.save(person);
        return "resultPerson";
    }

    // Education

    // Allow user to enter Educational Achievements
    @GetMapping("/enterEducation")
    public String addEducation(Model model)
    {
        System.out.println("******************************** entered /enterEducation *********************");

        model.addAttribute("newEducation", new Education());
//        int counter = 0;
        return "enterEducation";
    }

    // Validate entered Educational Achievements and if it is valid display the result
    @PostMapping("/enterEducation")
    public String postEducation(@Valid @ModelAttribute("newEducation") Education education, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            return "enterEducation";
        }

//        // Allow user to leave the end date empty (assume he/she is still employed)
//        long employedDays;
//        if(user.getEndDate() == null)
//        {
//            user.setEndDate(LocalDate.now());
//            user.setEmployedDays(ChronoUnit.DAYS.between(user.getStartDate(), user.getEndDate()));
//        }
//        // If dates entered, do not accept the end date before the start date
//        else if(user.getEndDate().compareTo(user.getStartDate())< 0)
//        {
//            return "enterperson";
//        }


        educationRepository.save(education);
        return "resultEducation";
    }


    // Employment

    // Allow user to enter Employment
    @GetMapping("/enterEmployment")
    public String addEmployment(Model model)
    {
        model.addAttribute("newEmployment", new Employment());
//        int counter = 0;
        return "enterEmployment";
    }

    // Validate entered Educational Achievements and if it is valid display the result
    @PostMapping("/enterEmployment")
    public String postEmployment(@Valid @ModelAttribute("newEmployment") Employment employment, BindingResult bindingResult)
    {

        if(bindingResult.hasErrors()){
            return "enterEmployment";
        }

        // Allow person to leave the end date empty (assume he/she is still employed)
        if(employment.getEndDate() == null)
        {
            employment.setEndDate(LocalDate.now());
        }
        // If dates entered, do not accept the end date before the start date
        else if(employment.getEndDate().compareTo(employment.getStartDate())< 0)
        {
            return "enterEmployment";
        }

        employmentRepository.save(employment);
        return "resultEmployment";
    }

    // Skills

    // Allow user to enter Skills
    @GetMapping("/enterSkills")
    public String addSkills(Model model)
    {
        model.addAttribute("newSkills", new Skills());
//        int counter = 0;
        return "enterSkills";
    }

    // Validate entered Educational Achievements and if it is valid display the result
    @PostMapping("/enterSkills")
    public String postSkills(@Valid @ModelAttribute("newSkills") Skills skills, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            return "enterSkills";
        }

        skillsRepository.save(skills);
        return "resultSkills";
    }




///////////////////////////////////////////////////////////////////////////////////////////




    // Display Person's info saved to the database
    @GetMapping("/displayPersonAllInfo")
    public String showAllPersonsInfo(Model model)
    {

//Afua's help:
        //Afua's help       Person myPerson = personRepository.findOne(Long.valueOf(1));
        Person myPerson = personRepository.findOne(new Long(1));
        List<Education> eduList = (List<Education>) educationRepository.findAll();
        myPerson.setEducationalAchievements((ArrayList<Education>) eduList);

/////////////////////////////////////////////////////////////////////////////////////////////////

        //Person myPerson = personRepository.findOne(Long.valueOf(1));
        // Person myPerson = personRepository.findOne(new Long(1));
        // List<Education> eduList = (List<Education>) educationRepository.findAll();
        // myPerson.setEducationalAchievements((ArrayList<Education>) eduList);
         /////////////////////
        //      List<Education> eduList =(List<Education>) educationRepository.findAll();
        //       eduList.add(educationRepository.findOne(Long.valueOf(1)));
//////////////////////
//        ArrayList<Education> eduList = (ArrayList<Education>) educationRepository.findAll();
//        myPerson.setEducationalAchievements(eduList);

//        Iterable<Education> eduList = educationRepository.findAll();
//        myPerson.setEducationalAchievements((ArrayList<Education>)eduList);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<Employment> workList = (List<Employment>) employmentRepository.findAll();
        myPerson.setWorkExperience ((ArrayList<Employment>) workList);

        List<Skills> skillsList = (List<Skills>) skillsRepository.findAll();
        myPerson.setSkillsWithRating ((ArrayList<Skills>) skillsList);

        model.addAttribute("person", myPerson );

        // Add another attribute to the model
        // model.addAttribute("edu", myPerson.getEducationalAchievements() );

        // Display results on the console:

//        System.out.println("Name: " + myPerson.getFirstName());
//        System.out.println("Name: " + myPerson.getLastName());
//
//        for(Education e: eduList){
//            System.out.println("DegreeTitle: " + e.getDegreeTitle());
//            System.out.println("DegreeTitle: " + e.getEducationalInstitution());
//            System.out.println("DegreeTitle: " + e.getGraduateDate());
//        }

        return "displayPersonAllInfo";
    }




//============ Update, Delete Skills ===============

    @GetMapping("/updatePerson/{id}")
    public String updatePerson(@PathVariable("id") long id, Model model)
    {
        Person p = personRepository.findOne(id);
        model.addAttribute("newPerson", p);
        return "enterPerson";
    }

//    Cange: remove "Delete Person"
//    @RequestMapping("/deletePerson/{id}")
//    public String delPerson(@PathVariable("id") long id){
//        personRepository.delete(id);
//        return "redirect:/displayPersonAllInfo";
//    }




//============ Update, Delete Employment ===============

    @GetMapping("/updateEmployment/{id}")
    public String updateEmployment(@PathVariable("id") long id, Model model)
    {
//        model.addAttribute("newEmployment", employmentRepository.findOne(id));
        Employment e = employmentRepository.findOne(id);
        model.addAttribute("newEmployment", e);
        return "enterEmployment";
    }

    @RequestMapping("/deleteEmployment/{id}")
    public String delEmployment(@PathVariable("id") long id){
        employmentRepository.delete(id);
        return "redirect:/displayPersonAllInfo";
    }




    //============ Update, Delete Education ===============

    @GetMapping("/updateEducation/{id}")
    public String updateEducation(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("newEducation", educationRepository.findOne(id));
        return "enterEducation";
    }

    @RequestMapping("/deleteEducation/{id}")
    public String delEducation(@PathVariable("id") long id){
        educationRepository.delete(id);
        return "redirect:/displayPersonAllInfo";
    }





    //============ Update, Delete Skills ===============

    @GetMapping("/updateSkills/{id}")
    public String updateSkills(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("newSkills", skillsRepository.findOne(id));
        return "enterSkills";
    }

    @RequestMapping("/deleteSkills/{id}")
    public String delSkills(@PathVariable("id") long id){
        skillsRepository.delete(id);
        return "redirect:/displayPersonAllInfo";
    }
}
