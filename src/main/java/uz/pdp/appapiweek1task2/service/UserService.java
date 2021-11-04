package uz.pdp.appapiweek1task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appapiweek1task2.entity.ProgrammingLanguage;
import uz.pdp.appapiweek1task2.entity.User;
import uz.pdp.appapiweek1task2.payload.ApiResponse;
import uz.pdp.appapiweek1task2.repository.ProgrammingLanguageRepository;
import uz.pdp.appapiweek1task2.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;


    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUser(Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty())
            return null;
        return optionalUser.get();
    }

    public ApiResponse addUser(User user){
        if (userRepository.existsByEmail(user.getEmail()))
            return new ApiResponse("Bunday user allaqachon bor", false);

        User user1 = new User();
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        userRepository.save(user1);
        return new ApiResponse("Successfully added", true);
    }

    public ApiResponse editUser(User user, Integer id){
        User editingUser = userRepository.getById(id);
        editingUser.setEmail(user.getEmail());
        editingUser.setPassword(user.getPassword());
        userRepository.save(editingUser);
        return new ApiResponse("Successfully edited",true);
    }

    public ApiResponse deleteUser(Integer id){
        if (!userRepository.existsById(id))
            return new ApiResponse("Bunday answer mavjud amas", false);
        userRepository.deleteById(id);
        return new ApiResponse("Successfully deleted", true );
    }
}
