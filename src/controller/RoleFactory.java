package controller;

import model.roles.*;

public class RoleFactory {

    public static Role RoleFactory(String role){
        if (role.equalsIgnoreCase("bloqueur")){
            return new BlockerRole();
        }else if (role.equalsIgnoreCase("bombeur")){
            return new Bombeur();
        }else if (role.equalsIgnoreCase("charpentier")){
            return new Charpentier();
        }else if (role.equalsIgnoreCase("foreur")){
            return new Foreur();
        }else if (role.equalsIgnoreCase("grimpeur")){
            return new GrimpeurRole();
//        }else if (role.equalsIgnoreCase("parachutiste")){
//            return new ParachutisteRole();
        }else if (role.equalsIgnoreCase("tunnelier")){
            return new TunnelierRole();
        }
        else {
            return new NormalRole();
        }
    }

}
