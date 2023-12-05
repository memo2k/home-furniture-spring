package bg.softuni.homefurniture.config;

import bg.softuni.homefurniture.model.dto.binding.AddProductBindingModel;
import bg.softuni.homefurniture.model.dto.binding.UserRegisterBindingModel;
import bg.softuni.homefurniture.model.entity.Category;
import bg.softuni.homefurniture.model.entity.Product;
import bg.softuni.homefurniture.model.entity.User;
import bg.softuni.homefurniture.model.enums.CategoryName;
import bg.softuni.homefurniture.repository.CategoryRepository;
import bg.softuni.homefurniture.repository.RoleRepository;
import bg.softuni.homefurniture.repository.UserRepository;
import bg.softuni.homefurniture.service.CategoryService;
import bg.softuni.homefurniture.service.RoleService;
import bg.softuni.homefurniture.service.UserService;
import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Set;

@Configuration
public class AppConfig {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public AppConfig (CategoryRepository categoryRepository, UserRepository userRepository, RoleRepository roleRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Bean
    public ModelMapper modelMapper () {
//
//        final ModelMapper modelMapper = new ModelMapper();
//
//        //AddRouteBindingModel -> Route
//        Provider<User> loggedUserProvider = req -> userService.getAuth();
//
//        Converter<Set<CategoryName>, Set<Category>> toEntitySet
//                = ctx -> (ctx.getSource() == null)
//                ? null
//                : categoryService.getAllByNameIn(ctx.getSource());
//
////        modelMapper
////                .createTypeMap(AddProductBindingModel.class, Product.class)
////                .addMappings(mapper -> mapper
////                        .using(toEntitySet)
////                        .map(AddProductBindingModel::getCategoryName, Product::setCategory));
//
//        // UserRegisterBindingModel -> User
//        Provider<User> newUserProvider = req -> {
//            User user = new User();
//            user.setRoles(Set.of(roleService.getRoleByName("USER")));
//            user.setCreatedOn(LocalDateTime.now());
//            return user;
//        };
//
//        Converter<String, String> passwordConverter
//                = ctx -> (ctx.getSource() == null)
//                ? null
//                : passwordEncoder().encode(ctx.getSource());
//
//        modelMapper
//                .createTypeMap(UserRegisterBindingModel.class, User.class)
//                .setProvider(newUserProvider)
//                .addMappings(mapper -> mapper
//                        .using(passwordConverter)
//                        .map(UserRegisterBindingModel::getPassword, User::setPassword));
//
//        return modelMapper;

        return new ModelMapper();
    }
    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }
}
