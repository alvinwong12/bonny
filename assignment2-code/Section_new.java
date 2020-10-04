import java.util.ArrayList;

public class Section {
    private String sectionName;
    private int maxEnrolment;
    private ArrayList<Student> enrolledStudents;
    //TODO DELETE
    private boolean deleteThisAfter;

    Section(String sectionName, int maxEnrolment) {
        enrolledStudents = new ArrayList<>();
        this.maxEnrolment = maxEnrolment;
        this.sectionName = sectionName;
    }

    boolean enrollStudent(Student student) {
        if (enrolledStudents.size() >= maxEnrolment)
            return false;
        enrolledStudents.add(student);
        return true;
    }

    public boolean unenrollStudent(Student student) {
        return enrolledStudents.remove(student);
    }

    public String getSectionName() {
        return sectionName;
    }

    /* Concept of getters and setters
    *
    * Methods for retreiving and modifying private variables
    * eg. for the variable deleteThisAfter
    * - Setter
    * public void setDeleteThisAfter(boolean deleteThisAfter){
    *   this.deleteThisAfter = deleteThisAfter;
    * }
    * - Getter
    * public boolean getDeleteThisAfter(){
    *   return deleThisAfter;
    * }
    */

    //TODO create getters and setters for enrolledStudents

}
