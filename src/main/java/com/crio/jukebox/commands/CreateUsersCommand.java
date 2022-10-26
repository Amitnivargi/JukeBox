package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.Services.IUsersService;
import com.crio.jukebox.entities.Users;

public class CreateUsersCommand implements ICommand {

     private final IUsersService usersService;
     public CreateUsersCommand(IUsersService usersService)
     {
         this.usersService=usersService;
     }


    @Override
    public void execute(List<String> tokens) {
        String name=tokens.get(1);
        Users u= usersService.create(name);
        System.out.println(u);
        
    }
    
}
