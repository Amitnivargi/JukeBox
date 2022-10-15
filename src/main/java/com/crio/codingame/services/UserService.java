package com.crio.codingame.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.crio.codingame.dtos.UserRegistrationDto;
import com.crio.codingame.entities.Contest;
import com.crio.codingame.entities.ContestStatus;
import com.crio.codingame.entities.RegisterationStatus;
import com.crio.codingame.entities.ScoreOrder;
import com.crio.codingame.entities.User;
import com.crio.codingame.exceptions.ContestNotFoundException;
import com.crio.codingame.exceptions.InvalidOperationException;
import com.crio.codingame.exceptions.UserNotFoundException;
import com.crio.codingame.repositories.IContestRepository;
import com.crio.codingame.repositories.IUserRepository;

public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final IContestRepository contestRepository;

    public UserService(IUserRepository userRepository, IContestRepository contestRepository) {
        this.userRepository = userRepository;
        this.contestRepository = contestRepository;
    }
    @Override
    public User create(String name) {
        User user = new User(name, 1500);
        return userRepository.save(user);
    }


    @Override
    public List<User> getAllUserScoreOrderWise(ScoreOrder scoreOrder){
        List<User> users = userRepository.findAll();
        List<User> sortedList = new ArrayList<>();

        if (scoreOrder.toString().compareTo("ASC") == 0) {
            //System.out.println("ASC");
            sortedList = users.stream().sorted(Comparator.comparingInt(User::getScore))
                    .collect(Collectors.toList());
            
            return sortedList;
        }
        
        if (scoreOrder.toString().compareTo("DESC") == 0) {
            //System.out.println("DESC");
            sortedList = users.stream().sorted(Comparator.comparingInt(User::getScore).reversed())
                    .collect(Collectors.toList());
            return sortedList;
        }

     return Collections.emptyList();
    }

    @Override
    public UserRegistrationDto attendContest(String contestId, String userName) throws ContestNotFoundException, UserNotFoundException, InvalidOperationException {
        Contest contest = contestRepository.findById(contestId).orElseThrow(() -> new ContestNotFoundException("Cannot Attend Contest. Contest for given id:"+contestId+" not found!"));
        User user = userRepository.findByName(userName).orElseThrow(() -> new UserNotFoundException("Cannot Attend Contest. User for given name:"+ userName+" not found!"));
        if(contest.getContestStatus().equals(ContestStatus.IN_PROGRESS)){
            throw new InvalidOperationException("Cannot Attend Contest. Contest for given id:"+contestId+" is in progress!");
        }
        if(contest.getContestStatus().equals(ContestStatus.ENDED)){
            throw new InvalidOperationException("Cannot Attend Contest. Contest for given id:"+contestId+" is ended!");
        }
        if(user.checkIfContestExists(contest)){
            throw new InvalidOperationException("Cannot Attend Contest. Contest for given id:"+contestId+" is already registered!");
        }
        user.addContest(contest);
        userRepository.save(user);
        return new UserRegistrationDto(contest.getName(), user.getName(),RegisterationStatus.REGISTERED);
    }


    @Override
    public UserRegistrationDto withdrawContest(String contestId, String userName) throws ContestNotFoundException, UserNotFoundException, InvalidOperationException {
        Contest contest = contestRepository.findById(contestId)
                .orElseThrow(() -> new ContestNotFoundException(
                        "Cannot Withdraw Contest. Contest for given id:" + contestId
                                + " not found!"));

        User user = userRepository.findByName(userName).orElseThrow(() -> new UserNotFoundException(
                "Cannot Withdraw Contest. User for given name:"+ userName+" not found!"));

        
        ContestStatus contestStatus = contest.getContestStatus();

        if (contestStatus.equals(ContestStatus.IN_PROGRESS)) {
            throw new InvalidOperationException("Cannot Withdraw Contest. Contest for given id:"
                    + contestId + " is in progress!");
        }
        else if (contestStatus.equals(ContestStatus.ENDED)) {
            throw new InvalidOperationException("Cannot Withdraw Contest. Contest for given id:"
                    + contestId + " is already ended!");
        }
        else if (!user.checkIfContestExists(contest)) {
            throw new InvalidOperationException(
                    "Cannot Withdraw Contest. User for given contest id:" + contestId
                            + " is not registered!");
        }
        else if (userName.equals(contest.getCreator().getName())) {
            throw new InvalidOperationException("Cannot Withdraw Contest. Contest Creator:"
                    + contestId + "not allowed to withdraw contest!");

        }
        
        user.deleteContest(contest);
        userRepository.save(user);

        return new UserRegistrationDto(contest.getName(), userName,
                RegisterationStatus.NOT_REGISTERED);
    }
    
}
