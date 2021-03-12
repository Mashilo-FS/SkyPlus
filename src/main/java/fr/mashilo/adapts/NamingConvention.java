package fr.mashilo.adapts;

public class NamingConvention {
    public NamingConvention(){}
    public String convert(String old_name){
        return(old_name.replace(":", "_"));
    }
    public String deconvert(String new_name){
        char underscore = '_';

        if (new_name.charAt(new_name.length() - 2) == underscore){
            try{
                Integer.valueOf( String.valueOf(new_name.charAt(new_name.length() - 1)) );
                return new_name.charAt()
            }catch (NumberFormatException ex){

            }
        }
    }
}
