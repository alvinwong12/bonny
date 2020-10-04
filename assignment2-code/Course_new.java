import java.util.ArrayList;

public class Course {
    private String uniqueName;
    private ArrayList<Section> sections;
    //TODO Create a list of requirements

    Course(String uniqueName) {
        this.uniqueName = uniqueName;
        sections = new ArrayList<>();
        //TODO Initialize requirements list
    }

    //TODO Create function that adds requirement
    boolean addRequirement(Course course){
        //TODO ...
        return true;
    }
    //TODO Create function that checks if a course can be enrolled based on requirements
    boolean reqSatisfied(Student student){
        //TODO Check if student is enrolled in the courses in requirements
        for(Course course: requirements){
            //TODO ...
        }
        return true;
    }
    //TODO Create function that checks if a course can be unenrolled based on requirements
    boolean reqEnrolled(Student student){
        //TODO Check if student is still enrolled in the courses in requirements
        for(Course course: requirements){
            //TODO ...
        }
        return false;
    }


    boolean addSection(Section section) {
        //TODO contains() method will not work here even two Section objects have the same sectionName -> HINT: you are doing OBJECT Comparision of two different objects (with different hashCode), do some research of what I mean
        if (sections.contains(section))
            return false;
        sections.add(section);
        return true;
    }

    boolean enrollStudent(Student student, String sectionName) {
        //TODO Determine if this student can enroll
        // This function needs to find the section in the list then enroll
        int sectionIndex = getSectionIndex(sectionName);
        if (sectionIndex == -1) {
            return false;
        } else {
            return sections.get(sectionIndex).enrollStudent(student);
        }
    }

    boolean unenrollStudent(Student student, String sectionName) {
        //TODO Determine if this student can unenroll
        // This function needs to find the section in the list then unenroll
        int sectionIndex = getSectionIndex(sectionName);
        if (sectionIndex == -1) {
            return false;
        } else {
            return sections.get(sectionIndex).unenrollStudent(student);
        }
    }

    public String getUniqueName() {
        return uniqueName;
    }

    // Different sections are stored in an ArrayList
    // the position of a section in that list is called index
    // this function returns the index of a section, -1 if not found
    private int getSectionIndex(String sectionName) {
        for (int i = 0; i < sections.size(); i++) {
            if (sections.get(i).getSectionName().equals(sectionName)) {
                return i;
            }
        }
        return -1;
    }
}
