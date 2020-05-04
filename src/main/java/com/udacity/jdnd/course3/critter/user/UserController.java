package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.contracts.IPetService;
import com.udacity.jdnd.course3.critter.user.db.CustomerEntity;
import com.udacity.jdnd.course3.critter.user.db.EmployeeEntity;
import com.udacity.jdnd.course3.critter.user.db.contract.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 * <p>
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerEntity entity = userService.saveCustomer(UserMapper.convertDTOToEntity(customerDTO));
        return UserMapper.convertEntityToDTO(entity);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers() {
       return UserMapper.convertEntityToDTO(userService.getCustomers());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId) {
        return UserMapper.convertEntityToDTO(userService.getCustomerByPetId(petId));
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeEntity entity = userService.saveEmployee(UserMapper.convertDTOToEntity(employeeDTO));
        return UserMapper.convertEntityToDTO(entity);
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        if (userService.getEmployeeById(employeeId).isPresent()) {
            return UserMapper.convertEntityToDTO(userService.getEmployeeById(employeeId).get());
        } else {
            return null;
        }
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        if (userService.getEmployeeById(employeeId).isPresent()) {
            EmployeeEntity employee = userService.getEmployeeById(employeeId).get();
            employee.setDaysAvailable(daysAvailable);
            userService.saveEmployee(employee);
        }
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employee) {
        Set<DayOfWeek> daysAvailable = new HashSet<>();
        daysAvailable.add(employee.getDate().getDayOfWeek());
        return UserMapper.convertEmployeeEntityToDTO(userService.getEmployeeForService(employee.getSkills(), daysAvailable));
    }

}
