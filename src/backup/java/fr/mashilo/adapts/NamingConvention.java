package fr.mashilo.adapts;

public class NamingConvention {
    public NamingConvention(){}
    public String convert(String old_name){
        return(old_name.replace(":", "_"));
    }
}
