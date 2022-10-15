package com.crio.codingame.commands;

import java.util.List;

import com.crio.codingame.entities.Contest;
import com.crio.codingame.entities.Level;
import com.crio.codingame.services.IContestService;

public class ListContestCommand implements ICommand{

    private final IContestService contestService;
    
    public ListContestCommand(IContestService contestService) {
        this.contestService = contestService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute getAllContestLevelWise method of IContestService and print the result.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["LIST_CONTEST","HIGH"]
    // or
    // ["LIST_CONTEST"]

    @Override
    public void execute(List<String> tokens) {
        try{
            int tokenSize = tokens.size();
            List<Contest> contest = null;
            if(tokenSize == 2){
            Level level = Level.valueOf(tokens.get(1));
            contest = contestService.getAllContestLevelWise(level);
            }
            else{
                contest = contestService.getAllContestLevelWise(null);
            }
            System.out.println(contest.toString());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
}
