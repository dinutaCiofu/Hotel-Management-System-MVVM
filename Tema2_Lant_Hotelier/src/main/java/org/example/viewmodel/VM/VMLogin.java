package org.example.viewmodel.VM;


import net.sds.mvvm.properties.Property;
import net.sds.mvvm.properties.PropertyFactory;
import org.example.viewmodel.command.CommandLogin;
import org.example.viewmodel.command.ICommand;


public class VMLogin {
    private ICommand loginICommand;
    private Property<String> emailTextField;
    private Property<String> passwordField;

    public VMLogin(){
        loginICommand = new CommandLogin(this);
        emailTextField = PropertyFactory.createProperty("email",this, String.class);
        passwordField = PropertyFactory.createProperty("password", this, String.class);
    }
    public String getEmail(){return emailTextField.get();}
    public String getPassword(){return  passwordField.get();}

    public ICommand getLoginICommand(){return this.loginICommand;}
}
