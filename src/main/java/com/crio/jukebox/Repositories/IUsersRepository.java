package com.crio.jukebox.Repositories;

import java.util.Optional;
import com.crio.jukebox.entities.Users;


public interface IUsersRepository extends CRUDRepository<Users,String> {
    
         public Optional<Users> findByName(String name);
}
