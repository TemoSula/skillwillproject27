package com.example.skillwillproject27.Services;

import com.example.skillwillproject27.HandleException.UserException;
import com.example.skillwillproject27.Models.UserModel;
import com.example.skillwillproject27.Repositories.UserRepository;
import com.example.skillwillproject27.Request.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    JwtService jwtService;

    public String register(RegistrationRequest request)
    {
      if(userRepository.findByUsername(request.getUsername()) != null)
      {
          throw new UserException("user with this username is already exsist");
      }
        request.setPassword(encoder.encode(request.getPassword()));
      UserModel userModel = new UserModel();
      userModel.setUsername(request.getUsername());
      userModel.setPassword(request.getPassword());
      userModel.setRole(request.getRole().toString());
      userRepository.save(userModel);
      return "Registration sucessfully";
    }

    public String login(String username, String password)
    {
     UserModel userModel = userRepository.findByUsername(username);
     if(userModel == null)
     {
         throw new UserException("user not found");
     }
     if(!encoder.matches(password,userModel.getPassword()))
     {
         throw new UserException("password might be incorrect");
     }
        return jwtService.generateToken(username);
    }


}
