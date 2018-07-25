package hello;
import java.util.List;
public class Person extends VehicleLifeCycleNetworkRESTCall{

    /**
     * @param args the command line arguments
     */
    private String firstName; //store the first name
    private String lastName;  //store the last name
    private List<String> middleName;
    private List<String> nationalities;
        //Default constructor
        //Initialize firstName and lastName to an empty string.
        //Postcondition: firstName = ""; lastName = "";
    public Person()
    {
        firstName = "";
        lastName = "";
    }

        //Constructor with parameters
        //Set firstName and lastName according to the parameters.
        //Postcondition: firstName = first; lastName = last;
    public Person(String first, String last,List<String> middle)
    {
        setName(first, last);
        setMiddleNames(middle);
    }
        //Method to output the first name and last name
        //in the form firstName lastName.
    public String toString()
    {
        return (firstName + "  "+ lastName+"\nNationalities:"+nationalities);
    }

        //Method to set firstName and lastName according to
        //the parameters.
        //Postcondition: firstName = first; lastName = last;
    protected void setName(String first, String last)
    {
        firstName = first;
        lastName = last;
    }
    protected void setFirstName(String first)
    {
       firstName=first; 
    }
    protected void setMiddleNames(List<String> middle)
    {
        middleName=middle;
    }
    protected void setLastName(String last)
    {
        lastName=last;
    }
    protected void setNationalities(List<String> nations)
    {
      nationalities=nations;
    }
        //Method to return firstName.
        //Postcondition: The value of firstName is returned.
    public String getFirstName()
    {
        return firstName;
    }
    public List<String> getMiddleNames()
    {
      return middleName;
    }

        //Method to return lastName.
        //Postcondition: The value of lastName is returned.
    public String getLastName()
    {
        return lastName;
    }
        //Method to return nationalities
        //Postcondition: The value of nationalities is returned.
    public List<String> getNationalities() 
    {
        return nationalities;
    }
    protected boolean equals(Person newPerson)
    {
        return (newPerson.firstName.equals(firstName)&&newPerson.lastName.equals(lastName));
    }
    protected void makeCopy(Person person1)
    {
        middleName=person1.middleName;
        firstName=person1.firstName;
        lastName=person1.lastName;
    }
    protected void getCopy(Person person2)
    {
        person2.middleName=middleName;
        person2.firstName=firstName;
        person2.lastName=lastName;
    }
    protected boolean checkFirst(String first)
    {
        return(firstName.equals(first));
    }
    protected boolean checkLast(String last)
    {
        return(lastName.equals(last));
    }
    protected boolean checkMiddle(String middle)
    {
        return(middleName.equals(middle));
    }
}
