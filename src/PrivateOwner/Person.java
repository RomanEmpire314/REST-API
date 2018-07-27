package PrivateOwner;
import java.util.List;

import hello.VehicleLifeCycleNetworkRESTCall;
public class Person extends VehicleLifeCycleNetworkRESTCall{

    /**
     * @param args the command line arguments
     */
    private String firstName; //store the first name
    private String lastName;  //store the last name

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
    public Person(String first, String last)
    {
        setName(first, last);
    }
        //Method to output the first name and last name
        //in the form firstName lastName.
    public String toString()
    {
        return (firstName + " "+ lastName);
    }

        //Method to set firstName and lastName according to
        //the parameters.
        //Postcondition: firstName = first; lastName = last;
    public void setName(String first, String last)
    {
        firstName = first;
        lastName = last;
    }
    public void setFirstName(String first)
    {
       firstName=first; 
    }
    protected void setLastName(String last)
    {
        lastName=last;
    }

        //Method to return firstName.
        //Postcondition: The value of firstName is returned.
    public String getFirstName()
    {
        return firstName;
    }

        //Method to return lastName.
        //Postcondition: The value of lastName is returned.
    public String getLastName()
    {
        return lastName;
    }
        //Method to return nationalities
        //Postcondition: The value of nationalities is returned.
    protected boolean equals(Person newPerson)

    {
        return (newPerson.firstName.equals(firstName)&&newPerson.lastName.equals(lastName));
    }
    public void makeCopy(Person person1)
    {
        firstName=person1.firstName;
        lastName=person1.lastName;
    }
    public void getCopy(Person person2)
    {
        person2.firstName=firstName;
        person2.lastName=lastName;
    }
    public boolean checkFirst(String first)
    {
        return(firstName.equals(first));
    }
    public boolean checkLast(String last)
    {
        return(lastName.equals(last));
    }
}
