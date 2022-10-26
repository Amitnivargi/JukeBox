package com.crio.jukebox.Repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.crio.jukebox.entities.Users;


public class UsersRepository implements IUsersRepository {

           private final Map<String,Users> userMap;

           Integer autoIncrement=0;

         public UsersRepository(){
                this.userMap=new HashMap<String,Users>();
                this.autoIncrement = userMap.size();
         }

         public UsersRepository(Map<String,Users> u)
         {
               this.userMap=new HashMap<String,Users>();
               this.autoIncrement=u.size();
         }

         public Users save(Users user)
         {
              if(user.getId()==null)
              {
                  autoIncrement++;
                  Users u=new Users (Integer.toString(autoIncrement), user.getName());
                  userMap.put(u.getId(),u);
                  return u;
              }

              userMap.put(user.getId(),user);
              return user;
         }

         public List<Users> finalAll(){
                         List<Users> u =new ArrayList<Users>();
                         userMap.values().forEach(f-> u.add(f));
                         return u;
         }

         public Optional<Users> findById(String id){
                    
                    return Optional.ofNullable(userMap.get(id));
         }
        public boolean existsById(String id)
        {
                  if(userMap.containsKey(id))
                  {
                        return true;
                  }
                  return false;
        }
         public void delete(Users entity)
         {
                   if(userMap.containsKey(entity.getId()))
                   {
                           userMap.remove(entity.getId());
                   }
         }
         public void deleteById(String id)
         {
            if(userMap.containsKey(id))
                   {
                           userMap.remove(id);
                   }
         }

         public Optional<Users> findByName(String name)
         {
                    
                return Optional.ofNullable(userMap.get(name));
        
         }

       @Override
       public List<Users> findAll() {
              
              return new ArrayList<>(userMap.values());
       }
}
