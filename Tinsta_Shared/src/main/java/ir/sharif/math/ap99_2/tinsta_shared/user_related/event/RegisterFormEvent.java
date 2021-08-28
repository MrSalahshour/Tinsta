package ir.sharif.math.ap99_2.tinsta_shared.user_related.event;


import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;


public class RegisterFormEvent extends RegisterEvent {
    private String userName;
    private String password;
    private String name;
    private String lastName;
    private String Email;

    public RegisterFormEvent(Object source, String userName, String password,
                             String name, String lastName, String email) {
        super(source);
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        Email = email;
    }

    public RegisterFormEvent() {
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        RegisterEventVisitor registerEventVisitor = (RegisterEventVisitor) eventVisitor;
        return registerEventVisitor.registerUser(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }


    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

}
