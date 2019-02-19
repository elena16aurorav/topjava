package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);

    private Map<Integer, User> users = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public boolean delete(int id) {
        if(this.users.containsKey(id)){
            this.users.remove(id);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public User save(User user) {
        if(user.isNew()){
            user.setId(counter.incrementAndGet());
            this.users.put(user.getId(), user);
            return user;
        }
        else{
            return users.get(user.getId());
        }
    }

    @Override
    public User get(int id) {
        if(this.users.containsKey(id)){
            return this.users.get(id);
        }
        else{
            return null;
        }
    }

    @Override
    public List<User> getAll() {
        return (List<User>)this.users.values();
    }

    @Override
    public User getByEmail(String email) {
        List<User> lists = getAll();
        for(User u: lists){
            if(u.getEmail().equals(email)){
                return u;
            }
        }
        return null;
    }
}
