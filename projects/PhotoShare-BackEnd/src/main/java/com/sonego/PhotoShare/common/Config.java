package com.sonego.PhotoShare.common;

import com.sonego.PhotoShare.business.model.*;
import com.sonego.PhotoShare.business.service.IPostService;
import com.sonego.PhotoShare.business.service.IProfileService;
import com.sonego.PhotoShare.persistence.repository.RoleRepository;
import com.sonego.PhotoShare.persistence.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.File;
import java.io.FileInputStream;
import java.util.UUID;

@Configuration
@AllArgsConstructor
public class Config {

    private UserRepository userRepository;

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setUserDetailsService(userDetailsService());
        dao.setPasswordEncoder(passwordEncoder());
        return dao;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User could not be found"));
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public String getImagesURI() {
        return "http://localhost:8080/images/";
    }

//    mocking data for authentication in development stage
    @Bean
    CommandLineRunner usersConfiguration(UserRepository userRepository,
                                         RoleRepository roleRepository,
                                         IProfileService profileService,
                                         UserDetailsService userService,
                                         IPostService postService) {

        return args -> {
            UUID adminRoleId = UUID.randomUUID();
            Role roleAdmin = new Role();
            roleAdmin.setId(adminRoleId);
            roleAdmin.setName(ERole.ROLE_ADMIN);
//            roleRepository.save(roleAdmin);

            UUID userRoleId = UUID.randomUUID();
            Role userRole = new Role();
            userRole.setId(userRoleId);
            userRole.setName(ERole.ROLE_USER);
//            roleRepository.save(userRole);

            User user = new User();
            user.setEmail("pessoa@hotmail.com");
            user.setPassword(new BCryptPasswordEncoder().encode("password123"));
            user.setUsername("defaultUser");
            user.addRole(roleAdmin);
            userRepository.save(user);



//            get logged user

            String userDetails = userService.loadUserByUsername("defaultUser").getUsername();

//            creating profiles
//            1
            File file = new File("D:/photos/image1.png");
            byte[] bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            Image image = new Image();
            image.setBytes(bytes);
            image.setExtension("png");
            image.setContentType("image/png");

            Profile profile = new Profile();
            profile.setProfileImage(image);
            profile.setName("Profile name1!");
            profile.setAbout("This is a test profile");

            UUID id1 = profileService.createProfile(profile, userDetails).getId();

//            2
            file = new File("D:/photos/image2.png");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("png");
            image.setContentType("image/png");

            profile = new Profile();
            profile.setProfileImage(image);
            profile.setName("Profile name2!");
            profile.setAbout("This is a test profile");

            UUID id2 = profileService.createProfile(profile, userDetails).getId();

//            3
            file = new File("D:/photos/image3.png");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("png");
            image.setContentType("image/png");

            profile = new Profile();
            profile.setProfileImage(image);
            profile.setName("Profile name3!");
            profile.setAbout("This is a test profile");

            UUID id3 = profileService.createProfile(profile, userDetails).getId();

//            4
            file = new File("D:/photos/image4.png");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("png");
            image.setContentType("image/png");

            profile = new Profile();
            profile.setProfileImage(image);
            profile.setName("Profile name4!");
            profile.setAbout("This is a test profile");

            UUID id4 = profileService.createProfile(profile, userDetails).getId();

//            5
            file = new File("D:/photos/image5.png");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("png");
            image.setContentType("image/png");

            profile = new Profile();
            profile.setProfileImage(image);
            profile.setName("Profile name5!");
            profile.setAbout("This is a test profile");

            UUID id5 = profileService.createProfile(profile, userDetails).getId();

//            6
            file = new File("D:/photos/image6.png");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("png");
            image.setContentType("image/png");

            profile = new Profile();
            profile.setProfileImage(image);
            profile.setName("Profile name6!");
            profile.setAbout("This is a test profile");

            UUID id6 = profileService.createProfile(profile, userDetails).getId();

//            7
            file = new File("D:/photos/image7.png");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("png");
            image.setContentType("image/png");

            profile = new Profile();
            profile.setProfileImage(image);
            profile.setName("Profile name7!");
            profile.setAbout("This is a test profile");

            UUID id7 = profileService.createProfile(profile, userDetails).getId();

//            8
            file = new File("D:/photos/image8.png");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("png");
            image.setContentType("image/png");

            profile = new Profile();
            profile.setProfileImage(image);
            profile.setName("Profile name8!");
            profile.setAbout("This is a test profile");

            UUID id8 = profileService.createProfile(profile, userDetails).getId();

//            9
            file = new File("D:/photos/image9.png");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("png");
            image.setContentType("image/png");

            profile = new Profile();
            profile.setProfileImage(image);
            profile.setName("Profile name9!");
            profile.setAbout("This is a test profile");

            UUID id9 = profileService.createProfile(profile, userDetails).getId();

//            10
            file = new File("D:/photos/image10.png");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("png");
            image.setContentType("image/png");

            profile = new Profile();
            profile.setProfileImage(image);
            profile.setName("Profile name10!");
            profile.setAbout("This is a test profile");

            UUID id10 = profileService.createProfile(profile, userDetails).getId();

//            11
            file = new File("D:/photos/image11.png");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("png");
            image.setContentType("image/png");

            profile = new Profile();
            profile.setProfileImage(image);
            profile.setName("Profile name11!");
            profile.setAbout("This is a test profile");

            UUID id11 = profileService.createProfile(profile, userDetails).getId();

//            12
            file = new File("D:/photos/image12.png");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("png");
            image.setContentType("image/png");

            profile = new Profile();
            profile.setProfileImage(image);
            profile.setName("Profile name12!");
            profile.setAbout("This is a test profile");

            UUID id12 = profileService.createProfile(profile, userDetails).getId();




//            creating posts
            System.out.println("Está passando no creating posts");
//            1
            file = new File("D:/photos/P1020632.JPG");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("JPG");
            image.setContentType("image/jpeg");

            postService.createPost("description", image, id1, userDetails);

//            2
            file = new File("D:/photos/P1020634.JPG");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("JPG");
            image.setContentType("image/jpeg");

            postService.createPost("description", image, id2, userDetails);

//            3
            file = new File("D:/photos/P1020635.JPG");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("JPG");
            image.setContentType("image/jpeg");

            postService.createPost("description", image, id3, userDetails);

//            4
            file = new File("D:/photos/P1020636.JPG");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("JPG");
            image.setContentType("image/jpeg");

            postService.createPost("description", image, id4, userDetails);

//            5
            file = new File("D:/photos/P1020660.JPG");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("JPG");
            image.setContentType("image/jpeg");

            postService.createPost("description", image, id5, userDetails);

//            6
            file = new File("D:/photos/P1020712.JPG");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("JPG");
            image.setContentType("image/jpeg");

            postService.createPost("description", image, id6, userDetails);

//            7
            file = new File("D:/photos/P1020639.JPG");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("JPG");
            image.setContentType("image/jpeg");

            postService.createPost("description", image, id7, userDetails);

//            8
            file = new File("D:/photos/P1020640.JPG");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("JPG");
            image.setContentType("image/jpeg");

            postService.createPost("description", image, id8, userDetails);

//            9
            file = new File("D:/photos/P1020641.JPG");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("JPG");
            image.setContentType("image/jpeg");

            postService.createPost("description", image, id9, userDetails);

//            10
            file = new File("D:/photos/P1020642.JPG");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("JPG");
            image.setContentType("image/jpeg");

            postService.createPost("description", image, id10, userDetails);

//            11
            file = new File("D:/photos/P1020760.JPG");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("JPG");
            image.setContentType("image/jpeg");

            postService.createPost("description", image, id11, userDetails);

//            12
            file = new File("D:/photos/P1020756.JPG");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("JPG");
            image.setContentType("image/jpeg");

            postService.createPost("description", image, id12, userDetails);

//            13
            file = new File("D:/photos/P1020743.JPG");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("JPG");
            image.setContentType("image/jpeg");

            postService.createPost("description", image, id1, userDetails);

//            14
            file = new File("D:/photos/P1020645.JPG");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("JPG");
            image.setContentType("image/jpeg");

            postService.createPost("description", image, id2, userDetails);

//            15
            file = new File("D:/photos/P1020646.JPG");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("JPG");
            image.setContentType("image/jpeg");

            postService.createPost("description", image, id3, userDetails);

//            16
            file = new File("D:/photos/P1020647.JPG");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("JPG");
            image.setContentType("image/jpeg");

            postService.createPost("description", image, id4, userDetails);

//            17
            file = new File("D:/photos/P1020648.JPG");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("JPG");
            image.setContentType("image/jpeg");

            postService.createPost("description", image, id5, userDetails);

//            18
            file = new File("D:/photos/P1020649.JPG");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("JPG");
            image.setContentType("image/jpeg");

            postService.createPost("description", image, id6, userDetails);

//            19
            file = new File("D:/photos/P1020650.JPG");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("JPG");
            image.setContentType("image/jpeg");

            postService.createPost("description", image, id7, userDetails);

//            20
            file = new File("D:/photos/P1020651.JPG");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("JPG");
            image.setContentType("image/jpeg");

            postService.createPost("description", image, id8, userDetails);

//            21
            file = new File("D:/photos/P1020652.JPG");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("JPG");
            image.setContentType("image/jpeg");

            postService.createPost("description", image, id9, userDetails);

//            22
            file = new File("D:/photos/P1020653.JPG");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("JPG");
            image.setContentType("image/jpeg");

            postService.createPost("description", image, id10, userDetails);

//            23
            file = new File("D:/photos/P1020654.JPG");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("JPG");
            image.setContentType("image/jpeg");

            postService.createPost("description", image, id11, userDetails);

//            24
            file = new File("D:/photos/P1020655.JPG");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("JPG");
            image.setContentType("image/jpeg");

            postService.createPost("description", image, id12, userDetails);

//            25
            file = new File("D:/photos/P1020656.JPG");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("JPG");
            image.setContentType("image/jpeg");

            postService.createPost("description", image, id1, userDetails);

//            26
            file = new File("D:/photos/P1020657.JPG");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("JPG");
            image.setContentType("image/jpeg");

            postService.createPost("description", image, id2, userDetails);

//            27
            file = new File("D:/photos/P1020658.JPG");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("JPG");
            image.setContentType("image/jpeg");

            postService.createPost("description", image, id3, userDetails);

//            28
            file = new File("D:/photos/P1020727.JPG");
            bytes = new byte[(int) file.length()];
            try(FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
            }
            image = new Image();
            image.setBytes(bytes);
            image.setExtension("JPG");
            image.setContentType("image/jpeg");

            Post pst = postService.createPost("description with comment", image, id4, userDetails);

//            create comments

//            1
            postService.commentPost("é um post muito bom", pst.getId(), "defaultUser");

        };
    }
}
