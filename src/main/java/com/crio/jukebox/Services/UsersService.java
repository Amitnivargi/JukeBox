package com.crio.jukebox.Services;

import com.crio.jukebox.Repositories.IUsersRepository;
import com.crio.jukebox.entities.Users;

public class UsersService implements IUsersService {

    private final IUsersRepository _iUsersRepository;
    public UsersService(IUsersRepository iUsersRepository)
    {
           _iUsersRepository=iUsersRepository;
    }
    @Override
    public Users create(String name) {
        Users users =new Users(name);

        return _iUsersRepository.save(users);
    }
    
}
