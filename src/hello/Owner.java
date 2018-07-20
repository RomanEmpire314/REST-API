package hello;

public class Owner extends Person
{
  private final String restCall="PrivateOwner/";
  private String email;
  private String title;
  public String Url(){
    return (this.restServer+restCall);
  }
  public Owner(){
  }
  public Owner(String first, String last, String middle,String nations, String mail)
  {
    this.setFirstName(first);
    this.setMiddleName(middle);
    this.setLastName(last);
    this.setNationalities(nations);
    setEmail(mail);
  }
  class ContractDetails{}
  protected void setEmail(String mail)
  {
    email=mail;
  }
  public String getEmail()
  {
    return email;
  }
  public String toString()
  {
    return ("Name: "+this.getFirstName()+" "+this.getMiddleName()+" "+this.getLastName()+"\nNationalities: "+
    this.getNationalities()+"\nEmail: "+email);
  }
  public static void main(String[]args)
  {
    Owner owner= new Owner();
    System.out.println(owner.Url());
    System.out.println(owner.get());
    owner.setFirstName("Trung");
    System.out.println(owner.toString());
  }
} 