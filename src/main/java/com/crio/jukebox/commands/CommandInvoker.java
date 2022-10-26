package com.crio.jukebox.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.crio.jukebox.exception.NoSuchCommandException;

public class CommandInvoker {

       private static final Map<String,ICommand> commandMap = new HashMap<String,ICommand>();
       private ICommand command;
       public void register(String commandName,ICommand command)
       {
                 commandMap.put(commandName,command);
                this.command=command;
       }

       private ICommand get(String commandName){ return commandMap.get(commandName);}

       public void executeCommand(String commandName, List<String> tokens) throws NoSuchCommandException {
                             
                              if(commandName==null)
                              {
                                   throw new NoSuchCommandException();
                              }  
                              command.execute(tokens);
       }
    
}
